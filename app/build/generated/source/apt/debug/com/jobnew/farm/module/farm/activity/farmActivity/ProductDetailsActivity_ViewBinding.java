// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.StatusBarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductDetailsActivity_ViewBinding implements Unbinder {
  private ProductDetailsActivity target;

  private View view2131297004;

  private View view2131296840;

  private View view2131296598;

  private View view2131296513;

  private View view2131296760;

  private View view2131296309;

  private View view2131296311;

  @UiThread
  public ProductDetailsActivity_ViewBinding(ProductDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductDetailsActivity_ViewBinding(final ProductDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", LinearLayout.class);
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", ConvenientBanner.class);
    target.nameTv = Utils.findRequiredViewAsType(source, R.id.name_tv, "field 'nameTv'", TextView.class);
    target.priceTv = Utils.findRequiredViewAsType(source, R.id.price_tv, "field 'priceTv'", TextView.class);
    target.unitTv = Utils.findRequiredViewAsType(source, R.id.unit_tv, "field 'unitTv'", TextView.class);
    target.describeTv = Utils.findRequiredViewAsType(source, R.id.describe_tv, "field 'describeTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.suyuan_bt, "field 'suyuanBt' and method 'dealClick'");
    target.suyuanBt = Utils.castView(view, R.id.suyuan_bt, "field 'suyuanBt'", Button.class);
    view2131297004 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reduce_img, "field 'reduceImg' and method 'dealClick'");
    target.reduceImg = Utils.castView(view, R.id.reduce_img, "field 'reduceImg'", ImageView.class);
    view2131296840 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.numTv = Utils.findRequiredViewAsType(source, R.id.num_tv, "field 'numTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.increase_img, "field 'increaseImg' and method 'dealClick'");
    target.increaseImg = Utils.castView(view, R.id.increase_img, "field 'increaseImg'", ImageView.class);
    view2131296598 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.postageTv = Utils.findRequiredViewAsType(source, R.id.postage_tv, "field 'postageTv'", TextView.class);
    target.noPostageTv = Utils.findRequiredViewAsType(source, R.id.noPostage_tv, "field 'noPostageTv'", TextView.class);
    target.salerTv = Utils.findRequiredViewAsType(source, R.id.saler_tv, "field 'salerTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.go_to_farm, "field 'goToFarmTv' and method 'dealClick'");
    target.goToFarmTv = view;
    view2131296513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.commentNumTv = Utils.findRequiredViewAsType(source, R.id.comment_num_tv, "field 'commentNumTv'", TextView.class);
    target.allCommentTv = Utils.findRequiredViewAsType(source, R.id.all_comment_tv, "field 'allCommentTv'", TextView.class);
    target.rundImg1 = Utils.findRequiredViewAsType(source, R.id.rund_img1, "field 'rundImg1'", ImageView.class);
    target.keyWord1 = Utils.findRequiredViewAsType(source, R.id.key_word1, "field 'keyWord1'", TextView.class);
    target.starView1 = Utils.findRequiredViewAsType(source, R.id.star_view1, "field 'starView1'", StatusBarView.class);
    target.dateTv1 = Utils.findRequiredViewAsType(source, R.id.date_tv1, "field 'dateTv1'", TextView.class);
    target.describeTv1 = Utils.findRequiredViewAsType(source, R.id.describe_tv1, "field 'describeTv1'", TextView.class);
    target.showImg1 = Utils.findRequiredViewAsType(source, R.id.show_img1, "field 'showImg1'", ImageView.class);
    target.showImg2 = Utils.findRequiredViewAsType(source, R.id.show_img2, "field 'showImg2'", ImageView.class);
    target.showImg3 = Utils.findRequiredViewAsType(source, R.id.show_img3, "field 'showImg3'", ImageView.class);
    target.pictureLinear1 = Utils.findRequiredViewAsType(source, R.id.picture_linear1, "field 'pictureLinear1'", LinearLayout.class);
    target.managerCommentBack1 = Utils.findRequiredViewAsType(source, R.id.manager_comment_back1, "field 'managerCommentBack1'", TextView.class);
    target.item1 = Utils.findRequiredViewAsType(source, R.id.item1, "field 'item1'", LinearLayout.class);
    target.rundImg2 = Utils.findRequiredViewAsType(source, R.id.rund_img2, "field 'rundImg2'", ImageView.class);
    target.keyWord2 = Utils.findRequiredViewAsType(source, R.id.key_word2, "field 'keyWord2'", TextView.class);
    target.starView2 = Utils.findRequiredViewAsType(source, R.id.star_view2, "field 'starView2'", StatusBarView.class);
    target.dateTv2 = Utils.findRequiredViewAsType(source, R.id.date_tv2, "field 'dateTv2'", TextView.class);
    target.describeTv2 = Utils.findRequiredViewAsType(source, R.id.describe_tv2, "field 'describeTv2'", TextView.class);
    target.showImg4 = Utils.findRequiredViewAsType(source, R.id.show_img4, "field 'showImg4'", ImageView.class);
    target.showImg5 = Utils.findRequiredViewAsType(source, R.id.show_img5, "field 'showImg5'", ImageView.class);
    target.showImg6 = Utils.findRequiredViewAsType(source, R.id.show_img6, "field 'showImg6'", ImageView.class);
    target.pictureLinear2 = Utils.findRequiredViewAsType(source, R.id.picture_linear2, "field 'pictureLinear2'", LinearLayout.class);
    target.managerCommentBack2 = Utils.findRequiredViewAsType(source, R.id.manager_comment_back2, "field 'managerCommentBack2'", TextView.class);
    target.item2 = Utils.findRequiredViewAsType(source, R.id.item2, "field 'item2'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.more, "field 'moreView' and method 'dealClick'");
    target.moreView = view;
    view2131296760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.totalPriceTv = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'totalPriceTv'", TextView.class);
    target.llconmment = Utils.findRequiredViewAsType(source, R.id.ll_comment, "field 'llconmment'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.bt_add_car, "field 'btAddCar' and method 'dealClick'");
    target.btAddCar = Utils.castView(view, R.id.bt_add_car, "field 'btAddCar'", TextView.class);
    view2131296309 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_sure_buy, "field 'btSureBuy' and method 'dealClick'");
    target.btSureBuy = Utils.castView(view, R.id.bt_sure_buy, "field 'btSureBuy'", TextView.class);
    view2131296311 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivTitleRight = null;
    target.titleBar = null;
    target.banner = null;
    target.nameTv = null;
    target.priceTv = null;
    target.unitTv = null;
    target.describeTv = null;
    target.suyuanBt = null;
    target.reduceImg = null;
    target.numTv = null;
    target.increaseImg = null;
    target.postageTv = null;
    target.noPostageTv = null;
    target.salerTv = null;
    target.goToFarmTv = null;
    target.commentNumTv = null;
    target.allCommentTv = null;
    target.rundImg1 = null;
    target.keyWord1 = null;
    target.starView1 = null;
    target.dateTv1 = null;
    target.describeTv1 = null;
    target.showImg1 = null;
    target.showImg2 = null;
    target.showImg3 = null;
    target.pictureLinear1 = null;
    target.managerCommentBack1 = null;
    target.item1 = null;
    target.rundImg2 = null;
    target.keyWord2 = null;
    target.starView2 = null;
    target.dateTv2 = null;
    target.describeTv2 = null;
    target.showImg4 = null;
    target.showImg5 = null;
    target.showImg6 = null;
    target.pictureLinear2 = null;
    target.managerCommentBack2 = null;
    target.item2 = null;
    target.moreView = null;
    target.totalPriceTv = null;
    target.llconmment = null;
    target.btAddCar = null;
    target.btSureBuy = null;

    view2131297004.setOnClickListener(null);
    view2131297004 = null;
    view2131296840.setOnClickListener(null);
    view2131296840 = null;
    view2131296598.setOnClickListener(null);
    view2131296598 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
    view2131296760.setOnClickListener(null);
    view2131296760 = null;
    view2131296309.setOnClickListener(null);
    view2131296309 = null;
    view2131296311.setOnClickListener(null);
    view2131296311 = null;
  }
}
