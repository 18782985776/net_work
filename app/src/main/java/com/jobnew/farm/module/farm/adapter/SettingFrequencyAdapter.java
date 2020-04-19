package com.jobnew.farm.module.farm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.bigkoo.pickerview.TimePickerView;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.CommitOrderEntity;
import com.jobnew.farm.entity.plan.CountItemEntity;
import com.jobnew.farm.utils.TimePickerViewUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class SettingFrequencyAdapter extends RecyclerView.Adapter<SettingFrequencyAdapter.ViewHold> {

    public List<CountItemEntity> mList;
    private Context mContext;
    private SimpleDateFormat format;
    private long date;

    public SettingFrequencyAdapter(Context context, List<CountItemEntity> list, long date) {
        format = new SimpleDateFormat("yyyy-MM-dd");
        mContext = context;
        this.date = date;
        this.mList = list;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_scheme_action_plan, parent, false);
        return new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        if (holder.itemView instanceof SuperTextView) {
            ((SuperTextView) holder.itemView).setLeftString(mList.get(position).title);
            ((SuperTextView) holder.itemView).setRightString(format.format(mList.get(position).executeDate));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHold extends RecyclerView.ViewHolder {

        public ViewHold(View View) {
            super(View);
            if (itemView instanceof SuperTextView) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar startDate = Calendar.getInstance();
                        startDate.setTimeInMillis(date);
                        Calendar endDate = Calendar.getInstance();
                        endDate.set(2030, 12, 12);
                        TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date, View v) {
                                mList.get(getLayoutPosition()).executeDate = date.getTime();
                                ((SuperTextView) itemView).setRightString(format.format(date));
                            }
                        }).setRangDate(startDate, endDate).build().show();

                    }
                });
            }
        }
    }

}
