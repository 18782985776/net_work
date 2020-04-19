package com.jobnew.farm.module.home.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.home.adapter.LaunchedEntertainmentAdapter;
import com.jobnew.farm.utils.TimePickerViewUtils;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaunchedActivity extends BaseActivity {

    @BindView(R.id.activity_name_tv)
    EditText activityNameTv;
    @BindView(R.id.startTime_ll)
    LinearLayout startTimeLl;
    @BindView(R.id.endTime_ll)
    LinearLayout endTimeLl;
    @BindView(R.id.address_tv)
    EditText addressTv;
    @BindView(R.id.recuce_tv)
    TextView recuceTv;
    @BindView(R.id.increase_tv)
    TextView increaseTv;
    @BindView(R.id.price_tv)
    EditText priceTv;
    @BindView(R.id.set_phone_tv)
    EditText setPhoneTv;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.myRecycle_view)
    RecyclerView myRecycleView;
    @BindView(R.id.startTime_tv)
    TextView startTimeTv;
    @BindView(R.id.endTime_tv)
    TextView endTimeTv;
LaunchedEntertainmentAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_launched;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("发起活动",true);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("1");
        arrayList.add("2");
        adapter=new LaunchedEntertainmentAdapter(arrayList,this);
        myRecycleView.setLayoutManager(new GridLayoutManager(this,4));
        myRecycleView.setAdapter(adapter);
    }

    @OnClick({R.id.startTime_ll,R.id.endTime_ll})
    public void dealClick(View view){
        switch (view.getId()){
            case R.id.startTime_ll:
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(new Date());
                Calendar endDate = Calendar.getInstance();
                endDate.set(2030, 12, 12);
                TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                       // entity.date = format.format(date);发起
                        startTimeTv.setText(format.format(date));
                    }
                }).setRangDate(startDate, endDate).build().show();
                break;
            case R.id.endTime_ll:
                Calendar startDate1 = Calendar.getInstance();
                startDate1.setTime(new Date());
                Calendar endDate1 = Calendar.getInstance();
                endDate1.set(2030, 12, 12);
                TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        // entity.date = format.format(date);发起
                        endTimeTv.setText(format.format(date));
                    }
                }).setRangDate(startDate1, endDate1).build().show();
                break;
        }
    }
}
