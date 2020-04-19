// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

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

public class TopUpFarmCurrencyActivity_ViewBinding implements Unbinder {
  private TopUpFarmCurrencyActivity target;

  private View view2131297020;

  private View view2131297021;

  private View view2131297022;

  private View view2131297023;

  private View view2131297024;

  private View view2131297025;

  private View view2131297026;

  private View view2131297027;

  private View view2131296853;

  private View view2131296893;

  private View view2131296856;

  private View view2131297333;

  private View view2131296588;

  private View view2131296548;

  @UiThread
  public TopUpFarmCurrencyActivity_ViewBinding(TopUpFarmCurrencyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TopUpFarmCurrencyActivity_ViewBinding(final TopUpFarmCurrencyActivity target,
      View source) {
    this.target = target;

    View view;
    target.imgAliPay = Utils.findRequiredViewAsType(source, R.id.img_ali_pay, "field 'imgAliPay'", ImageView.class);
    target.imgWeixinPay = Utils.findRequiredViewAsType(source, R.id.img_weixin_pay, "field 'imgWeixinPay'", ImageView.class);
    target.balanceTv = Utils.findRequiredViewAsType(source, R.id.balance_tv, "field 'balanceTv'", TextView.class);
    target.imgBalancePay = Utils.findRequiredViewAsType(source, R.id.img_balance_pay, "field 'imgBalancePay'", ImageView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.txtCount = Utils.findRequiredViewAsType(source, R.id.txt_count, "field 'txtCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.text1, "field 'text1' and method 'onViewClicked'");
    target.text1 = Utils.castView(view, R.id.text1, "field 'text1'", TextView.class);
    view2131297020 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text2, "field 'text2' and method 'onViewClicked'");
    target.text2 = Utils.castView(view, R.id.text2, "field 'text2'", TextView.class);
    view2131297021 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text3, "field 'text3' and method 'onViewClicked'");
    target.text3 = Utils.castView(view, R.id.text3, "field 'text3'", TextView.class);
    view2131297022 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text4, "field 'text4' and method 'onViewClicked'");
    target.text4 = Utils.castView(view, R.id.text4, "field 'text4'", TextView.class);
    view2131297023 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text5, "field 'text5' and method 'onViewClicked'");
    target.text5 = Utils.castView(view, R.id.text5, "field 'text5'", TextView.class);
    view2131297024 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text6, "field 'text6' and method 'onViewClicked'");
    target.text6 = Utils.castView(view, R.id.text6, "field 'text6'", TextView.class);
    view2131297025 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text7, "field 'text7' and method 'onViewClicked'");
    target.text7 = Utils.castView(view, R.id.text7, "field 'text7'", TextView.class);
    view2131297026 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text8, "field 'text8' and method 'onViewClicked'");
    target.text8 = Utils.castView(view, R.id.text8, "field 'text8'", TextView.class);
    view2131297027 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
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
    view = Utils.findRequiredView(source, R.id.txt_submit, "method 'onViewClicked'");
    view2131297333 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_sub, "method 'onViewClicked'");
    view2131296588 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_add, "method 'onViewClicked'");
    view2131296548 = view;
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
    TopUpFarmCurrencyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgAliPay = null;
    target.imgWeixinPay = null;
    target.balanceTv = null;
    target.imgBalancePay = null;
    target.tvTotalPrice = null;
    target.txtCount = null;
    target.text1 = null;
    target.text2 = null;
    target.text3 = null;
    target.text4 = null;
    target.text5 = null;
    target.text6 = null;
    target.text7 = null;
    target.text8 = null;

    view2131297020.setOnClickListener(null);
    view2131297020 = null;
    view2131297021.setOnClickListener(null);
    view2131297021 = null;
    view2131297022.setOnClickListener(null);
    view2131297022 = null;
    view2131297023.setOnClickListener(null);
    view2131297023 = null;
    view2131297024.setOnClickListener(null);
    view2131297024 = null;
    view2131297025.setOnClickListener(null);
    view2131297025 = null;
    view2131297026.setOnClickListener(null);
    view2131297026 = null;
    view2131297027.setOnClickListener(null);
    view2131297027 = null;
    view2131296853.setOnClickListener(null);
    view2131296853 = null;
    view2131296893.setOnClickListener(null);
    view2131296893 = null;
    view2131296856.setOnClickListener(null);
    view2131296856 = null;
    view2131297333.setOnClickListener(null);
    view2131297333 = null;
    view2131296588.setOnClickListener(null);
    view2131296588 = null;
    view2131296548.setOnClickListener(null);
    view2131296548 = null;
  }
}
