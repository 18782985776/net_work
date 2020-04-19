package com.jobnew.farm.module.home.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.entity.SearchEntity;
import com.jobnew.farm.module.home.adapter.SearchListAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页搜索界面
 **/
public class SearchActivityForHome extends Activity{

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.indicator_search_img)
    ImageView indicatorSearchImg;
    @BindView(R.id.img_delete)
    RelativeLayout imgDelete;
    @BindView(R.id.cancle_search_tv)
    TextView cancleSearchTv;
    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.TagFlowLayout)
    com.zhy.view.flowlayout.TagFlowLayout TagFlowLayout;
    @BindView(R.id.before_search_layout)
    LinearLayout beforeSearchLayout;
    @BindView(R.id.listView_search)
    ListView listViewSearch;
    @BindView(R.id.search_main_linearLayout)
     LinearLayout searchMainLinearLayout;
    Unbinder unbinder;
    List<String> data = new ArrayList<>();
    ArrayList<SearchEntity> arrayList=new ArrayList<SearchEntity>();
    SearchListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // StatusBarUtil.setColor(this, Color.parseColor("#729C35"),0);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        setContentView(R.layout.activity_search_for_home);
        unbinder= ButterKnife.bind(this);
        FarmToastUtils.show(getStatusBarHeight()+"");;
      // searchMainLinearLayout.setPadding(0,getStatusBarHeight(),0,0);
        adapter=new SearchListAdapter(this,arrayList);
        listViewSearch.setAdapter(adapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!editText.getText().toString().isEmpty()){
                    imgDelete.setVisibility(View.VISIBLE);
                    indicatorSearchImg.setVisibility(View.GONE);
                    cancleSearchTv.setVisibility(View.GONE);
                    searchImg.setVisibility(View.VISIBLE);
                }else{
                    imgDelete.setVisibility(View.GONE);
                    indicatorSearchImg.setVisibility(View.VISIBLE);
                    cancleSearchTv.setVisibility(View.VISIBLE);
                    searchImg.setVisibility(View.GONE);
                }
            }
        });
        initFlow();
    }
    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initFlow() {
        initData();
        final LayoutInflater mInflater = LayoutInflater.from(this);
        TagFlowLayout.setAdapter(new TagAdapter<String>(data) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tabflowfayout_textview,
                        TagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        TagFlowLayout.setOnTagClickListener(new com.zhy.view.flowlayout.TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
             //   showToast(data.get(position));
                FarmToastUtils.show(data.get(position));
                return true;
            }
        });
    }
/**获取热门数据**/
    private void initData() {
        data.add("智慧农场");
        data.add("花果山农场");
        data.add("快乐农场");
        data.add("NBA农场");
        data.add("完美时代农场");
    }
    @OnClick({R.id.cancle_search_tv,R.id.search_img,R.id.img_delete})
    public void ClickAnswer(View v){
        switch (v.getId()){
            case R.id.cancle_search_tv:
                finish();
                break;
            case R.id.search_img:
                adapter.setSearchKeyWord(editText.getText().toString());
                hideSoftInput();
//开始搜索数据
                initSearchArray();
                adapter.notifyDataSetChanged();
                beforeSearchLayout.setVisibility(View.GONE);
                listViewSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.img_delete:
                if(!editText.getText().toString().isEmpty()){
                    editText.setText("");
                }
                break;
        }
    }
/***搜索数据**/
    private void initSearchArray() {
        for (int i=0;i<arrayList.size();i++){
            adapter.arrayList.remove(i);
        }
        for (int i=0;i<5;i++){
            SearchEntity searchEntity=new SearchEntity();
            searchEntity.setName("土豆种子测试");
            searchEntity.setType("种子");
            adapter.arrayList.add(searchEntity);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 界面关闭的时候隐藏软键盘
     */
    public void hideSoftInput() {
        View view = getWindow().peekDecorView();
        InputMethodManager inputManger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            view.requestFocus();
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 打开软键盘
     */
    public void showSoftInput() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
