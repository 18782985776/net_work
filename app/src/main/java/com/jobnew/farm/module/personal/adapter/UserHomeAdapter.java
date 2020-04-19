package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.constants.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wc on 2017/7/12.
 * Function：
 * desc：
 */

public class UserHomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context mContext;
    public UserHomeAdapter(int layoutResId, List<String> data, Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RecyclerView recyclerView=helper.getView(R.id.recycler_view);
        RecyclerView recyclerViewTwo=helper.getView(R.id.recycler_view_two);
        TextView textView=helper.getView(R.id.txt_up_list);

        StringBuffer sb=new StringBuffer();
        sb.append(" ");
        List<String> data=new ArrayList<>();
        for (int i = 0; i <helper.getLayoutPosition() ; i++) {
            data.add("item1");
            sb.append("小安说地方就爱上对方就撒旦"+i+"、");
        }
        GridLayoutManager gm=new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(gm);
        UserHomeImgAdapter adapter=new UserHomeImgAdapter(R.layout.activity_home_image_item,data);
        recyclerView.setAdapter(adapter);

        UserHomeTwoAdapter adapter2=new UserHomeTwoAdapter(R.layout.activity_home_two_item,data);
        LinearLayoutManager lm=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerViewTwo.setLayoutManager(lm);
        recyclerViewTwo.setAdapter(adapter2);

        ImageSpan imageSpan=new ImageSpan(mContext,R.mipmap.mine_icon_fabulous2);
        SpannableString spanStr = new SpannableString(sb.toString());
        spanStr.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spanStr);
    }
}
