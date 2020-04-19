package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.module.farm.adapter.SchemeNestAdapter;
import com.jobnew.farm.utils.ScreenToolsUtils;
import com.jobnew.farm.utils.TimePickerViewUtils;
import com.jobnew.farm.widget.QuantityHelper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wufengqiao on 2017/7/14.
 * function：
 * desc：
 */

public class AddNewTaskAdapter extends RecyclerView.Adapter {

    private List<SchemeEntity> mList;
    private Context mContext;
    private int number = -1;
    private final DecimalFormat mDecimalFormat;
    private OnPriceChangeListener onPriceChangeListener;
    private SimpleDateFormat dateFormat;

    public AddNewTaskAdapter(Context context, List<SchemeEntity> list, int num) {
        super();
        mContext = context;
        mList = list;
        number = num;
        mDecimalFormat = new DecimalFormat("0.00");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_custom_scheme_main_type, parent, false);
                viewHolder = new MainViewHolder(view, 0);
                break;
            case 0:
            case 2:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_scheme_date_type, parent, false);
                viewHolder = new DateViewHolder(view);
                break;
            case 4:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_scheme_photo_date_type, parent, false);
                viewHolder = new PhotoViewHolder(view);
                break;
            case 6:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_custom_scheme_main_type, parent, false);
                viewHolder = new MainViewHolder(view, 6);
            default:

                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SchemeEntity entity = mList.get(position);
        switch (holder.getItemViewType()) {
            case 1: //施肥item
                MainViewHolder viewHolder = (MainViewHolder) holder;
                setMainDate(entity, viewHolder);
                setDate(entity, viewHolder);
                viewHolder.mAdapter.setNewData(entity.entities);
                viewHolder.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.isSelected()) {
                            return;
                        }
                        for (int i = 0; i < entity.entities.size(); i++) {
                            if (i != position) {
                                entity.entities.get(i).checked = false;
                            } else {
                                entity.entities.get(i).checked = true;
                            }
                        }
                        viewHolder.mAdapter.notifyDataSetChanged();
                        viewHolder.stvName.setRightString("¥" + mDecimalFormat.format(entity.entities.get(position).price * entity.count * number));
                        notifyPriceChange();
                    }
                });
                break;
            case 0: // 浇水item
            case 2: //播种item
                DateViewHolder dateViewHolder = (DateViewHolder) holder;
                dateViewHolder.tvName.setText(entity.farmArtName);
                dateViewHolder.tvUnitPrice.setText("（¥" + mDecimalFormat.format(entity.price) + "/" + entity.unitName + "）");
                dateViewHolder.stvName.setRightString("¥" + mDecimalFormat.format(entity.price * number));
                dateViewHolder.stvDate.setLeftString("执行日期");
                setDate(entity, dateViewHolder);
                dateViewHolder.stvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        entity.checked = v.isSelected();
                        notifyPriceChange();
                    }
                });
                dateViewHolder.stvName.setSelected(entity.checked);
                break;
            case 4:  //照片
                PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
                photoViewHolder.tvName.setText(entity.farmArtName);
                photoViewHolder.tvPhotoPrice.setText("（¥" + mDecimalFormat.format(entity.price) + "/" + entity.unitName + "）");
                photoViewHolder.stvPhoto.setRightString("¥" + mDecimalFormat.format(entity.price * entity.count));
                if (entity.duration < 1) {
                    entity.duration = 1;
                }
                if (entity.interval < 1) {
                    entity.interval = 1;
                }
                photoViewHolder.stvPhoto.setSelected(entity.checked);
                photoViewHolder.stvPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        entity.checked = v.isSelected();
                        notifyPriceChange();
                    }
                });
                setDate(entity, photoViewHolder);
                setListener(entity, photoViewHolder);

                break;
            default:
                break;
        }

    }

    /**
     * 设置施肥数据
     *
     * @param entity
     * @param viewHolder
     */
    private void setMainDate(final SchemeEntity entity, MainViewHolder viewHolder) {
        viewHolder.stvName.setLeftString(entity.categoryName);
        viewHolder.stvName.setSelected(entity.checked);
        viewHolder.stvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                entity.checked = v.isSelected();
                viewHolder.frequencyName.setVisibility(v.isSelected() ? View.VISIBLE : View.GONE);
                viewHolder.rvContent.setVisibility(v.isSelected() ? View.VISIBLE : View.GONE);
                notifyPriceChange();
            }
        });
        if (!entity.checked) {
            return;
        }
        viewHolder.rvContent.setVisibility(View.VISIBLE);
        viewHolder.frequencyName.setVisibility(View.VISIBLE);
        for (int i = 0; i < entity.entities.size(); i++) {
            if (entity.entities.get(i).checked) {
                viewHolder.frequencyName.setLeftString("执行日期");
                viewHolder.stvName.setRightString("¥" + mDecimalFormat.format(entity.entities.get(i).price * number));
            }
        }

    }

    /**
     * 设置照相点击事件
     *
     * @param entity
     * @param photoViewHolder
     */
    private void setListener(SchemeEntity entity, PhotoViewHolder photoViewHolder) {
        photoViewHolder.countHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                entity.count = count;
                photoViewHolder.stvPhoto.setRightString("¥" + mDecimalFormat.format(entity.price * entity.count));
                if (photoViewHolder.stvPhoto.isSelected()) {
                    notifyPriceChange();
                }
            }
        });
    }


    /**
     * 设置浇水、播种日期
     *
     * @param entity
     * @param dateViewHolder
     */
    private void setDate(SchemeEntity entity, DateViewHolder dateViewHolder) {
        if (TextUtils.isEmpty(entity.date)) {
            long timeMillis = (System.currentTimeMillis() + 864000000) / 86400000 * 86400000;
            entity.date = dateFormat.format(timeMillis);
        }
        dateViewHolder.stvDate.setRightString(entity.date);

        dateViewHolder.stvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(new Date());
                Calendar endDate = Calendar.getInstance();
                endDate.set(2030, 12, 12);
                TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        entity.date = dateFormat.format(date);
                        dateViewHolder.stvDate.setRightString(entity.date);
                    }
                }).setRangDate(startDate, endDate).build().show();
            }
        });
    }

    /**
     * 设置拍照日期
     *
     * @param entity
     * @param viewHolder
     */
    private void setDate(SchemeEntity entity, PhotoViewHolder viewHolder) {
        viewHolder.stvDate.setRightString(entity.date);
        viewHolder.stvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(new Date());
                Calendar endDate = Calendar.getInstance();
                endDate.set(2030, 12, 12);
                TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        entity.date = dateFormat.format(date);
                        viewHolder.stvDate.setRightString(entity.date);
                    }
                }).setRangDate(startDate, endDate).build().show();
            }
        });
    }

    /**
     * 设置施肥日期
     *
     * @param entity
     * @param mainViewHolder
     */
    private void setDate(final SchemeEntity entity, final MainViewHolder mainViewHolder) {
        if (TextUtils.isEmpty(entity.date)) {
            long timeMillis = (System.currentTimeMillis() + 86400000) / 86400000 * 86400000;
            entity.date = dateFormat.format(timeMillis);
        }
        mainViewHolder.frequencyName.setRightString(entity.date);

        mainViewHolder.frequencyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(new Date());
                Calendar endDate = Calendar.getInstance();
                endDate.set(2030, 12, 12);
                TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        entity.date = dateFormat.format(date);
                        mainViewHolder.frequencyName.setRightString(entity.date);
                    }
                }).setRangDate(startDate, endDate).build().show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).ctype;
    }


    /**
     * 计算总价
     *
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < mList.size(); i++) {
            SchemeEntity entity = mList.get(i);
            switch (entity.ctype) {
                case 0:
                case 2:
                    if (entity.checked) {
                        totalPrice += entity.price * number;
                    }
                    break;
                case 1:
                    if (entity.checked) {
                        for (int j = 0; j < entity.entities.size(); j++) {
                            if (entity.entities.get(j).checked) {
                                totalPrice += entity.entities.get(j).price * number;
                            }
                        }
                    }
                    break;
                case 4:
                    if (entity.checked) {
                        totalPrice += entity.count * entity.price;
                    }
                    break;
            }
        }
        return totalPrice;
    }

    /**
     * 通知总价改变
     */
    public void notifyPriceChange() {
        if (onPriceChangeListener != null) {
            onPriceChangeListener.OnPriceChange(getTotalPrice());
        }
    }

    /**
     * 设置总价改变监听
     *
     * @param listener
     */
    public void setOnPriceChangeListener(OnPriceChangeListener listener) {
        onPriceChangeListener = listener;
    }

    public interface OnPriceChangeListener {
        void OnPriceChange(double price);
    }

    //集合单选item -- （施肥）
    class MainViewHolder extends RecyclerView.ViewHolder {

        SchemeNestAdapter mAdapter;
        @BindView(R.id.stv_name)
        SuperTextView stvName;

        @BindView(R.id.frequency_name)
        SuperTextView frequencyName;
        @BindView(R.id.rv_content)
        RecyclerView rvContent;

        MainViewHolder(View view, int type) {
            super(view);
            ButterKnife.bind(this, view);


            initView(type);
            setRecycleView();
        }

        private void initView(int type) {
            if (type == 6) {
                frequencyName.setVisibility(View.GONE);
            } else {
                frequencyName.setRightString("", mContext.getResources().getDrawable(R.mipmap.farm_icon_more3), ScreenToolsUtils.dp2px(10));
            }
        }

        private void setRecycleView() {
            rvContent.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter = new SchemeNestAdapter(R.layout.item_sheme_main_type_item, mList);
            rvContent.setAdapter(mAdapter);

        }
    }

    //浇水 、播种item 带日期
    static class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.stv_name)
        SuperTextView stvName;
        @BindView(R.id.stv_date)
        SuperTextView stvDate;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit_price)
        TextView tvUnitPrice;

        DateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_unit_price)
        TextView tvPhotoPrice;
        @BindView(R.id.stv_photo)
        SuperTextView stvPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.stv_date)
        SuperTextView stvDate;
        @BindView(R.id.layout_photo_count)
        View countView;

        QuantityHelper countHelper;

        PhotoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            countHelper = new QuantityHelper(countView, 1, 999);
        }
    }

}
