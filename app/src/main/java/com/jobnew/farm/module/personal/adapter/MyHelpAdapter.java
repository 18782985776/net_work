package com.jobnew.farm.module.personal.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.base.HelpEntity;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/14.
 * title:
 * note:
 */

public class MyHelpAdapter extends BaseQuickAdapter<HelpEntity,BaseViewHolder> {
private ArrayList<HelpEntity> arrayList;
    public MyHelpAdapter(ArrayList<HelpEntity> arrayList,int resourcelayout){
        super(resourcelayout,arrayList);
        this.arrayList=arrayList;
    }
    @Override
    protected void convert(BaseViewHolder helper, HelpEntity item) {
        helper.setText(R.id.questiono_tv,item.getTitle());
        helper.setText(R.id.answer_tv,item.getContent());
        helper.getView(R.id.questiono_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.getView(R.id.answer_tv).getVisibility()==View.GONE){
                    helper.getView(R.id.answer_tv).setVisibility(View.VISIBLE);
                }else {
                    helper.getView(R.id.answer_tv).setVisibility(View.GONE);
                }
            }
        });
    }
}
