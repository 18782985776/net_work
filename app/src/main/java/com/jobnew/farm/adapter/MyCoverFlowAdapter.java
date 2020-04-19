package com.jobnew.farm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jobnew.farm.R;
import com.jobnew.farm.coverFlowView.ACoverFlowAdapter;
import com.jobnew.farm.entity.HomeBannerBean;

import java.util.List;

/**
 * Created by wc on 2017/5/25.
 * Function：
 * desc：
 */

public class MyCoverFlowAdapter extends ACoverFlowAdapter<MyCoverFlowAdapter.ViewHolder> {

    private List<HomeBannerBean> mArray;

    private Context context;

    public MyCoverFlowAdapter(Context context, List<HomeBannerBean> mArray) {
        this.context = context;
        this.mArray = mArray;
    }

    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View content;
        content = View.inflate(parent.getContext(), R.layout.home_banner_item_view, new LinearLayout(parent.getContext()));
        return new ViewHolder(content);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ViewHolder holder = (ViewHolder) vh;
        HomeBannerBean channelBean = mArray.get(position);
        Glide.with(context)
                .load(channelBean.getImg())
                .asBitmap()
                .error(R.mipmap.home_banner_z)
                .centerCrop()
                .into(holder.imageView);
        holder.textView.setText(channelBean.getName());
    }
    class ViewHolder extends ACoverFlowAdapter.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_channelImg);
            textView= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}


