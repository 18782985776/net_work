package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FeedBackActivity extends BaseActivity {
    Unbinder unbinder;
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
    @BindView(R.id.feed_editTextView)
    EditText feedEditTextView;

    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        feedEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(feedEditTextView.getText().toString().length()>=140){
                    showMsg("您已达到最大字数限制140");
                }
            }
        });
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.setTitleMainText("意见反馈");
        titleBar.setRightText("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showMsg("提交");
                if(feedEditTextView.getText().toString().isEmpty()){
                    showMsg("反馈意见不能为空！");
                }else {
                    loading();
                    hideSoftInput();
                    HashMap<String,String> map=new HashMap<String, String>();
                    map.put("content",feedEditTextView.getText().toString());
                    MyFarmRepository.getIns().feedBackSubmit(map).subscribe(new DefaultSubscriber<BaseEntity>(FeedBackActivity.this,"加载中",true) {
                        @Override
                        public void _onNext(BaseEntity entity) {
                            if(entity.code==500){
                                showMsg("提交成功");
                                feedEditTextView.setText("");

                                content();
                            }
                            finish();
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
