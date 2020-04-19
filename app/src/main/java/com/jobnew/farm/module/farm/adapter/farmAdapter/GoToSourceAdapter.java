package com.jobnew.farm.module.farm.adapter.farmAdapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.SourceEntity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.RundImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/6/29.
 * title:
 * note:
 */

//public class GoToSourceAdapter extends RecyclerView.Adapter<GoToSourceAdapter.ViewHolder>{
//    private LayoutInflater inflater;
//    private Context context;
//    private ArrayList<String> arrayList;
//    private View headerView;//头部视图
//    public GoToSourceAdapter(Context context, ArrayList<String> arrayList){
//       inflater=LayoutInflater.from(context);
//        this.context=context;
//        this.arrayList=arrayList;
//    }
//
//    @Override
//    public GoToSourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewHolder holder=new ViewHolder(inflater.inflate(R.layout.item_grow_record,parent,false));
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(GoToSourceAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//    public void setHeaderView(View headerView){
//        this.headerView=headerView;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if(headerView!=null){
//
//        }
//        return super.getItemViewType(position);
//    }
//}

public class GoToSourceAdapter extends BaseQuickAdapter<SourceEntity.SourceInfosBean,BaseViewHolder>{
    public static String farmurl="";
    public static String farmManagerName="";
    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    public GoToSourceAdapter(int resourceId,ArrayList<SourceEntity.SourceInfosBean> arrayList){
        super(resourceId,arrayList);
    }

    @Override
    protected void convert(BaseViewHolder helper, SourceEntity.SourceInfosBean item) {
        ImageView rundImgs = helper.getView(R.id.farm_manager_img);
        GlideManager.loadRoundImg(farmurl,rundImgs);
        helper.setText(R.id.farm_manager_tv,farmManagerName);
        helper.setText(R.id.date_tv,sf.format(new Date(item.getCreateDate())));
        helper.setText(R.id.content_tv,item.getArtProductName());
        helper.setText(R.id.operater_tv,item.getManagerName());
        List<SourceEntity.SourceInfosBean.ImagesBean> images = item.getImages();
        if(images.isEmpty()){
            helper.getView(R.id.ll_imgs).setVisibility(View.GONE);
        }else {
            ImageView img1 = helper.getView(R.id.img1);
            ImageView img2 = helper.getView(R.id.img2);
            ImageView img3 = helper.getView(R.id.img3);
            switch (images.size()){
                case 1:
                  GlideManager.loadImg(images.get(0).getImgUrl(),img1);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    break;
                case 2:
                    GlideManager.loadImg(images.get(0).getImgUrl(),img1);
                    GlideManager.loadImg(images.get(1).getImgUrl(),img2);
                    img3.setVisibility(View.GONE);
                    break;
               default://最多显示3张图片
                   GlideManager.loadImg(images.get(0).getImgUrl(),img1);
                   GlideManager.loadImg(images.get(1).getImgUrl(),img2);
                   GlideManager.loadImg(images.get(2).getImgUrl(),img3);
                    break;
            }
        }


    }
    public void setFarmImgUrl(String farmurl){
        this.farmurl=farmurl;
    }
    public void setFarmManager(String farmManagerName){
        this.farmManagerName=farmManagerName;
    }
}
