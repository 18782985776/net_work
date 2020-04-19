package com.jobnew.farm.module.home.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.matchFeature.MatchDetailsEntity;
import com.jobnew.farm.module.home.adapter.MatchDetailsAdapter;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class MatchInfoActivity extends BaseActivity {

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
    @BindView(R.id.match_name_tv)
    TextView matchNameTv;
    @BindView(R.id.openner_tv)
    TextView opennerTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.join_time_tv)
    TextView joinTimeTv;
    @BindView(R.id.match_time_tv)
    TextView matchTimeTv;
    @BindView(R.id.match_number_tv)
    TextView matchNumberTv;
    @BindView(R.id.message_img)
    ImageView messageImg;
    @BindView(R.id.phone_img)
    ImageView phoneImg;
    @BindView(R.id.award_tv)
    TextView awardTv;
    @BindView(R.id.match_details_tv)
    TextView matchDetailsTv;
    @BindView(R.id.match_recycleView)
    RecyclerView matchRecycleView;
    @BindView(R.id.join)
    TextView join;
    int matchId;//比赛id
    String phone=null;//电话号码
    String[] mPermissionList={Manifest.permission.CALL_PHONE,};
    Intent dialIntent;
    MatchDetailsAdapter adapter;
    long signUpEndDate;//避免报名结束，再提交报名

    @Override
    protected int getLayout() {
        return R.layout.activity_match_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("比赛信息", true);
        matchId = getIntent().getIntExtra("matchId", 0);;
        initData();
    }
    /***初始化比赛详情页****/
    private void initData() {
        MyFarmRepository.getIns().queryMatchDetails(matchId+"",new HashMap<>())
                .subscribe(new DefaultSubscriber<BaseEntity<MatchDetailsEntity>>(this,false) {
                    @Override
                    public void _onNext(BaseEntity<MatchDetailsEntity> entity) {
                       try {
                            setViewByData(entity.data);
                        }catch (NullPointerException e){
                            showMsg("空数据");
                        }
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        error(msg);
                    }
                });
    }
    /***根据网络返回数据初始化界面***/
    private void setViewByData(MatchDetailsEntity data) {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM--dd");
        matchNameTv.setText(data.getName());//比赛名称
        opennerTv.setText(data.getFarm().getName());//举办方
        if(data.getArea()!=null){
            addressTv.setText(data.getAddress());
        }else {
            addressTv.setText(data.getAddress());
        }

        signUpEndDate = data.getSignUpEndDate();
        joinTimeTv.setText(sf.format(new Date(data.getSignUpStartDate()))+"至"+sf.format(new Date(data.getSignUpEndDate())));
        matchTimeTv.setText(sf.format(new Date(data.getStartDate()))+"至"+ sf.format(new Date(data.getEndDate())));//活动日期
        matchNumberTv.setText(data.getJoinNumber()+"/"+data.getNumber());
        phone = data.getPhone();
        awardTv.setText(data.getAwardsDesc());
        matchDetailsTv.setText(data.getDetails());
        matchRecycleView.setLayoutManager(new GridLayoutManager(MatchInfoActivity.this,3));
        List<MatchDetailsEntity.ImagesBean> images = data.getImages();
        if(!images.isEmpty()){
            ArrayList<MatchDetailsEntity.ImagesBean> array=new ArrayList<>();
            array.addAll(images);
            adapter=new MatchDetailsAdapter(array);
            matchRecycleView.setAdapter(adapter);
        }
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);
        titleBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("收藏");
            }
        });
    }
    @OnClick({R.id.phone_img,R.id.message_img,R.id.join})
    public void dealClick(View view){
        switch (view.getId()){
            case R.id.phone_img:
                if(phone!=null){
                    callPhoneNumber();
                }
                break;
            case R.id.message_img:


                break;
            case  R.id.join:
             if( Calendar.getInstance().getTimeInMillis()>signUpEndDate|Calendar.getInstance().getTimeInMillis()==signUpEndDate){
                 showMsg("报名已结束");
             }else{
                 joinMatch();
             };
                break;
        }
    }
    /***参加比赛***/
    private void joinMatch() {
        MyFarmRepository.getIns().signupMatch(matchId+"").subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("报名成功");
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                showMsg(msg);
            }
        });

    }

    private void callPhoneNumber() {
        dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if(EasyPermissions.hasPermissions(MatchInfoActivity.this,mPermissionList)){//有打电话权限
            startActivity(dialIntent);
        }else {
            EasyPermissions.requestPermissions(MatchInfoActivity.this,"必要权限",927,mPermissionList);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        if(requestCode==927){
            startActivity(dialIntent);
        }
    }
}
