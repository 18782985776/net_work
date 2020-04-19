package com.jobnew.farm.module.farm.adapter.farmAdapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.NoteEntity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.RundImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/6/28.
 * title:
 * note:
 */

public class MoreCommentAdapter extends BaseQuickAdapter<NoteEntity.ListBean,BaseViewHolder> {
    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    public MoreCommentAdapter(int layoutResId, List<NoteEntity.ListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NoteEntity.ListBean item) {
        helper.setText(R.id.key_word1,item.getUser().getName());
        helper.setText(R.id.tv_date,sf.format(new Date(item.getCreateDate())));
       ImageView rundImg1 = helper.getView(R.id.rund_img1);
        GlideManager.loadRoundImg(item.getUser().getAvatar(),rundImg1);
        helper.setText(R.id.tv_content,item.getContent());
        if(item.getReplyMsg()==null){//店家无回复
            helper.getView(R.id.ll_shopper_reply).setVisibility(View.GONE);
        }else {
            helper.setText(R.id.tv_shopper_reply,item.getReplyMsg());
            Log.d("尼玛", "convert: "+item.getReplyMsg());
        }
        if(item.getImgList()==null){//图片问题
            helper.getView(R.id.picture_linear1).setVisibility(View.GONE);
        } else if(item.getImgList().size()==0){
            helper.getView(R.id.picture_linear1).setVisibility(View.GONE);
        }else {
            ImageView img1 = helper.getView(R.id.show_img1);
            ImageView img2 = helper.getView(R.id.show_img2);
            ImageView img3 = helper.getView(R.id.show_img3);
            switch (item.getImgList().size()){
                case 1:
                    GlideManager.loadImg(item.getImgList().get(0),img1);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    break;
                case 2:
                    GlideManager.loadImg(item.getImgList().get(0),img1);
                    GlideManager.loadImg(item.getImgList().get(1),img2);
                    img3.setVisibility(View.GONE);
                    break;
                default:
                    GlideManager.loadImg(item.getImgList().get(0),img1);
                    GlideManager.loadImg(item.getImgList().get(1),img2);
                    GlideManager.loadImg(item.getImgList().get(2),img3);
                    break;
            }
        }

    }
}
