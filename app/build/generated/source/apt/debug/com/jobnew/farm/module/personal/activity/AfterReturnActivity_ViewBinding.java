// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import com.jobnew.farm.widget.ExpandGridView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AfterReturnActivity_ViewBinding implements Unbinder {
  private AfterReturnActivity target;

  private View view2131297210;

  private View view2131297211;

  private View view2131296894;

  private View view2131297333;

  private View view2131296714;

  private View view2131296715;

  @UiThread
  public AfterReturnActivity_ViewBinding(AfterReturnActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AfterReturnActivity_ViewBinding(final AfterReturnActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_return_cargo, "field 'tvReturnCargo' and method 'onViewClicked'");
    target.tvReturnCargo = Utils.castView(view, R.id.tv_return_cargo, "field 'tvReturnCargo'", TextView.class);
    view2131297210 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_return_money, "field 'tvReturnMoney' and method 'onViewClicked'");
    target.tvReturnMoney = Utils.castView(view, R.id.tv_return_money, "field 'tvReturnMoney'", TextView.class);
    view2131297211 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llType = Utils.findRequiredViewAsType(source, R.id.ll_type, "field 'llType'", LinearLayout.class);
    target.imgReceived = Utils.findRequiredViewAsType(source, R.id.img_received, "field 'imgReceived'", ImageView.class);
    target.imgReceivedNo = Utils.findRequiredViewAsType(source, R.id.img_received_no, "field 'imgReceivedNo'", ImageView.class);
    target.gridView = Utils.findRequiredViewAsType(source, R.id.feed_gridview, "field 'gridView'", ExpandGridView.class);
    target.txtTypeTitle = Utils.findRequiredViewAsType(source, R.id.txt_type_title, "field 'txtTypeTitle'", TextView.class);
    target.txtType = Utils.findRequiredViewAsType(source, R.id.txt_type, "field 'txtType'", TextView.class);
    target.txtInstructions = Utils.findRequiredViewAsType(source, R.id.txt_instructions, "field 'txtInstructions'", TextView.class);
    target.etInstructions = Utils.findRequiredViewAsType(source, R.id.et_instructions, "field 'etInstructions'", EditText.class);
    view = Utils.findRequiredView(source, R.id.rl_why, "field 'rlWhy' and method 'onViewClicked'");
    target.rlWhy = Utils.castView(view, R.id.rl_why, "field 'rlWhy'", RelativeLayout.class);
    view2131296894 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.etMoney = Utils.findRequiredViewAsType(source, R.id.et_money, "field 'etMoney'", ClearEditText.class);
    view = Utils.findRequiredView(source, R.id.txt_submit, "method 'onViewClicked'");
    view2131297333 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_received, "method 'onViewClicked'");
    view2131296714 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_received_no, "method 'onViewClicked'");
    view2131296715 = view;
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
    AfterReturnActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvReturnCargo = null;
    target.tvReturnMoney = null;
    target.llType = null;
    target.imgReceived = null;
    target.imgReceivedNo = null;
    target.gridView = null;
    target.txtTypeTitle = null;
    target.txtType = null;
    target.txtInstructions = null;
    target.etInstructions = null;
    target.rlWhy = null;
    target.tvMoney = null;
    target.etMoney = null;

    view2131297210.setOnClickListener(null);
    view2131297210 = null;
    view2131297211.setOnClickListener(null);
    view2131297211 = null;
    view2131296894.setOnClickListener(null);
    view2131296894 = null;
    view2131297333.setOnClickListener(null);
    view2131297333 = null;
    view2131296714.setOnClickListener(null);
    view2131296714 = null;
    view2131296715.setOnClickListener(null);
    view2131296715 = null;
  }
}
