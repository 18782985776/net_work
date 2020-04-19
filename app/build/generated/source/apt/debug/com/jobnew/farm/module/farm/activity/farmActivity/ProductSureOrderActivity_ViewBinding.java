// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.RundImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductSureOrderActivity_ViewBinding implements Unbinder {
  private ProductSureOrderActivity target;

  private View view2131296840;

  private View view2131296598;

  private View view2131297084;

  private View view2131296687;

  private View view2131296699;

  @UiThread
  public ProductSureOrderActivity_ViewBinding(ProductSureOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductSureOrderActivity_ViewBinding(final ProductSureOrderActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", LinearLayout.class);
    target.tt = Utils.findRequiredViewAsType(source, R.id.tt, "field 'tt'", TextView.class);
    target.tvReceiver = Utils.findRequiredViewAsType(source, R.id.tv_receiver, "field 'tvReceiver'", TextView.class);
    target.addressTv = Utils.findRequiredViewAsType(source, R.id.address_tv, "field 'addressTv'", TextView.class);
    target.tvFarmName = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'tvFarmName'", TextView.class);
    target.imgProduct = Utils.findRequiredViewAsType(source, R.id.img_product, "field 'imgProduct'", ImageView.class);
    target.tvProduct = Utils.findRequiredViewAsType(source, R.id.tv_product, "field 'tvProduct'", TextView.class);
    target.tvUnitName = Utils.findRequiredViewAsType(source, R.id.tv_unitName, "field 'tvUnitName'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvIntro = Utils.findRequiredViewAsType(source, R.id.tv_intro, "field 'tvIntro'", TextView.class);
    target.tvProductNum = Utils.findRequiredViewAsType(source, R.id.tv_product_num, "field 'tvProductNum'", TextView.class);
    target.dd = Utils.findRequiredViewAsType(source, R.id.dd, "field 'dd'", TextView.class);
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
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.tvPostCost = Utils.findRequiredViewAsType(source, R.id.tv_post_cost, "field 'tvPostCost'", TextView.class);
    target.tvTotalPrice2 = Utils.findRequiredViewAsType(source, R.id.tv_total_price2, "field 'tvTotalPrice2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_commit, "field 'tvCommit' and method 'dealClick'");
    target.tvCommit = Utils.castView(view, R.id.tv_commit, "field 'tvCommit'", TextView.class);
    view2131297084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_address, "field 'llAddress' and method 'dealClick'");
    target.llAddress = Utils.castView(view, R.id.ll_address, "field 'llAddress'", LinearLayout.class);
    view2131296687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_farm, "field 'llFarm' and method 'dealClick'");
    target.llFarm = Utils.castView(view, R.id.ll_farm, "field 'llFarm'", LinearLayout.class);
    view2131296699 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.imgFarm = Utils.findRequiredViewAsType(source, R.id.img_farm, "field 'imgFarm'", RundImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductSureOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivTitleRight = null;
    target.titleBar = null;
    target.tt = null;
    target.tvReceiver = null;
    target.addressTv = null;
    target.tvFarmName = null;
    target.imgProduct = null;
    target.tvProduct = null;
    target.tvUnitName = null;
    target.tvPrice = null;
    target.tvIntro = null;
    target.tvProductNum = null;
    target.dd = null;
    target.reduceImg = null;
    target.numTv = null;
    target.increaseImg = null;
    target.tvTotalPrice = null;
    target.tvPostCost = null;
    target.tvTotalPrice2 = null;
    target.tvCommit = null;
    target.llAddress = null;
    target.llFarm = null;
    target.tvPhone = null;
    target.imgFarm = null;

    view2131296840.setOnClickListener(null);
    view2131296840 = null;
    view2131296598.setOnClickListener(null);
    view2131296598 = null;
    view2131297084.setOnClickListener(null);
    view2131297084 = null;
    view2131296687.setOnClickListener(null);
    view2131296687 = null;
    view2131296699.setOnClickListener(null);
    view2131296699 = null;
  }
}
