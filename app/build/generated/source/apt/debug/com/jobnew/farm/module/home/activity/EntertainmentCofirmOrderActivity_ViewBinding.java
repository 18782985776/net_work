// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EntertainmentCofirmOrderActivity_ViewBinding implements Unbinder {
  private EntertainmentCofirmOrderActivity target;

  private View view2131296638;

  private View view2131296640;

  private View view2131296630;

  private View view2131297084;

  @UiThread
  public EntertainmentCofirmOrderActivity_ViewBinding(EntertainmentCofirmOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EntertainmentCofirmOrderActivity_ViewBinding(final EntertainmentCofirmOrderActivity target,
      View source) {
    this.target = target;

    View view;
    target.ivFarm = Utils.findRequiredViewAsType(source, R.id.iv_farm, "field 'ivFarm'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_product, "field 'ivPruduct' and method 'dealChlick'");
    target.ivPruduct = Utils.castView(view, R.id.iv_product, "field 'ivPruduct'", ImageView.class);
    view2131296638 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealChlick(p0);
      }
    });
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.go = Utils.findRequiredViewAsType(source, R.id.go, "field 'go'", RelativeLayout.class);
    target.tvIntro = Utils.findRequiredViewAsType(source, R.id.tv_intro, "field 'tvIntro'", TextView.class);
    target.numTv = Utils.findRequiredViewAsType(source, R.id.num_tv, "field 'numTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_reduce, "field 'ivReduce' and method 'dealChlick'");
    target.ivReduce = Utils.castView(view, R.id.iv_reduce, "field 'ivReduce'", ImageView.class);
    view2131296640 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealChlick(p0);
      }
    });
    target.tvBuy = Utils.findRequiredViewAsType(source, R.id.tv_buy, "field 'tvBuy'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_increase, "field 'ivIncrease' and method 'dealChlick'");
    target.ivIncrease = Utils.castView(view, R.id.iv_increase, "field 'ivIncrease'", ImageView.class);
    view2131296630 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealChlick(p0);
      }
    });
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_commit, "field 'tvCommit' and method 'dealChlick'");
    target.tvCommit = Utils.castView(view, R.id.tv_commit, "field 'tvCommit'", TextView.class);
    view2131297084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealChlick(p0);
      }
    });
    target.tvProductName = Utils.findRequiredViewAsType(source, R.id.tv_product_name, "field 'tvProductName'", TextView.class);
    target.tvFarmName = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'tvFarmName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EntertainmentCofirmOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivFarm = null;
    target.ivPruduct = null;
    target.tvPrice = null;
    target.go = null;
    target.tvIntro = null;
    target.numTv = null;
    target.ivReduce = null;
    target.tvBuy = null;
    target.ivIncrease = null;
    target.tvTotalPrice = null;
    target.tvCommit = null;
    target.tvProductName = null;
    target.tvFarmName = null;

    view2131296638.setOnClickListener(null);
    view2131296638 = null;
    view2131296640.setOnClickListener(null);
    view2131296640 = null;
    view2131296630.setOnClickListener(null);
    view2131296630 = null;
    view2131297084.setOnClickListener(null);
    view2131297084 = null;
  }
}
