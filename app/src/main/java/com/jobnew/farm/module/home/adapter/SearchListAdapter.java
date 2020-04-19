package com.jobnew.farm.module.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.entity.SearchEntity;
import com.jobnew.farm.utils.SpannableStringUtil;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/14.
 * title:
 * note:
 */

public class SearchListAdapter extends BaseAdapter {
    public ArrayList<SearchEntity> arrayList;
    private LayoutInflater inflater;
    private String searchKeyWord;
    public SearchListAdapter(Context context,ArrayList<SearchEntity> arrayList){
        this.arrayList=arrayList;
        inflater=LayoutInflater.from(context);
    }
    /**和搜索的关键字相关**/
    public void setSearchKeyWord(String searchKeyWord){
        this.searchKeyWord=searchKeyWord;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public SearchEntity getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_search_list_view,null,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
          holder= (ViewHolder) convertView.getTag();
        }
        if(searchKeyWord!=null){//在这里匹配文字颜色
            String name = getItem(position).getName();
            SpannableStringUtil.getInstance().setSpannableStringToTextView(getItem(position).getName(),searchKeyWord, Color.parseColor("#90b659"),holder.matchSearchTv);
        }
        return convertView;
    }
   private class  ViewHolder{
        TextView matchSearchTv;
        TextView typeTv;
        ViewHolder(View convertView){
            matchSearchTv= (TextView) convertView.findViewById(R.id.match_search_tv);
            typeTv= (TextView) convertView.findViewById(R.id.type_tv);
        }
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
