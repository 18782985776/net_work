package com.jobnew.farm.module.farm.activity.farmActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.LoveDonationEntity;
import com.jobnew.farm.module.farm.activity.PlantingPlanAcitivity;
import com.jobnew.farm.module.farm.adapter.farmAdapter.LoveDonationAdapter;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoveDonationActivity extends BaseActivity {
    public LoveDonationAdapter loveAdapter;
    public ArrayList<LoveDonationEntity> arrayList;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.recycleView_love_donation)
    RecyclerView recycleViewLoveDonation;
    Unbinder unbinder;

    @Override
    protected int getLayout() {
        return R.layout.activity_love_donation;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        int requestCode = getIntent().getIntExtra(Constant.ADDRESS_MANAGE, -1);
        initArray();
        justForDate();
        loveAdapter = new LoveDonationAdapter(this, arrayList);
        unbinder= ButterKnife.bind(this);
        recycleViewLoveDonation.setLayoutManager(new LinearLayoutManager(this));
        recycleViewLoveDonation.setAdapter(loveAdapter);
        loveAdapter.setMyItemListener(new LoveDonationAdapter.MyItemListener() {//点击item监听
            @Override
            public void Chilced(int position) {
                LoveDonationEntity entity= arrayList.get(position);
                if (requestCode == 105){
                    String adressDetails=entity.getArea().getMergerName()+entity.getArea().getName()+entity.getAddress();
                    Intent intent = new Intent();
                    intent.putExtra(Constant.ADDRESS_ID, entity.getId());
                    intent.putExtra(Constant.ADDRESS_MANAGE,adressDetails.replace(",",""));
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private void justForDate() {
        HashMap<String ,String> map=new HashMap<>();
        MyFarmRepository.getIns().queryLoveAddress(map).subscribe(new DefaultSubscriber<BaseEntity<List<LoveDonationEntity>>>(this,"加载中",false) {
            @Override
            public void _onNext(BaseEntity<List<LoveDonationEntity>> entity) {
                Log.d(TAG, "_onNext: 天龙八部"+entity.data.toString());
                arrayList.addAll(entity.data);
                loveAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initArray() {
        arrayList = new ArrayList();
//        for (int i = 0; i < 20; i++) {
//            arrayList.add(i);
//        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }

    public void setTitleBar(TitleBarHelper titleBar) {
        titleBar.setTitleMainText("爱心捐赠");
       titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
