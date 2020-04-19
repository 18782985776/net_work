// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayOrderActivity_ViewBinding implements Unbinder {
  private PayOrderActivity target;

  private View view2131296853;

  private View view2131296893;

  private View view2131296856;

  private View view2131296869;

  private View view2131297333;

  @UiThread
  public PayOrderActivity_ViewBinding(PayOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayOrderActivity_ViewBinding(final PayOrderActivity target, View source) {
    this.target = target;

    View view;
    target.imgAliPay = Utils.findRequiredViewAsType(source, R.id.img_ali_pay, "field 'imgAliPay'", ImageView.class);
    target.imgWeixinPay = Utils.findRequiredViewAsType(source, R.id.img_weixin_pay, "field 'imgWeixinPay'", ImageView.class);
    target.imgBalancePay = Utils.findRequiredViewAsType(source, R.id.img_balance_pay, "field 'imgBalancePay'", ImageView.class);
    target.imgFarmPay = Utils.findRequiredViewAsType(source, R.id.img_farm_pay, "field 'imgFarmPay'", ImageView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_ali, "method 'onViewClicked'");
    view2131296853 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_weixin, "method 'onViewClicked'");
    view2131296893 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_balance, "method 'onViewClicked'");
    view2131296856 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_fram, "method 'onViewClicked'");
    view2131296869 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_submit, "method 'onViewClicked'");
    view2131297333 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PayOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgAliPay = null;
    target.imgWeixinPay = null;
    target.imgBalancePay = null;
    target.imgFarmPay = null;
    target.tvTotalPrice = null;

    view2131296853.setOnClickListener(null);
    view2131296853 = null;
    view2131296893.setOnClickListener(null);
    view2131296893 = null;
    view2131296856.setOnClickListener(null);
    view2131296856 = null;
    view2131296869.setOnClickListener(null);
    view2131296869 = null;
    view2131297333.setOnClickListener(null);
    view2131297333 = null;
  }
}
