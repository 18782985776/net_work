// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InvestmentActivity_ViewBinding implements Unbinder {
  private InvestmentActivity target;

  private View view2131296325;

  @UiThread
  public InvestmentActivity_ViewBinding(InvestmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InvestmentActivity_ViewBinding(final InvestmentActivity target, View source) {
    this.target = target;

    View view;
    target.edtName = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edtName'", ClearEditText.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", ClearEditText.class);
    target.etDetail = Utils.findRequiredViewAsType(source, R.id.et_detail, "field 'etDetail'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_submit, "method 'onViewClicked'");
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    InvestmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtName = null;
    target.edtPhone = null;
    target.etDetail = null;

    view2131296325.setOnClickListener(null);
    view2131296325 = null;
  }
}
