// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.loginAndRegister.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131297328;

  private View view2131296324;

  private View view2131297264;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.etUserPhone = Utils.findRequiredViewAsType(source, R.id.et_user_phone, "field 'etUserPhone'", ClearEditText.class);
    view = Utils.findRequiredView(source, R.id.txt_send_code, "field 'txtSendCode' and method 'onViewClicked'");
    target.txtSendCode = Utils.castView(view, R.id.txt_send_code, "field 'txtSendCode'", TextView.class);
    view2131297328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etVerificationCode = Utils.findRequiredViewAsType(source, R.id.et_verification_code, "field 'etVerificationCode'", ClearEditText.class);
    target.etPws = Utils.findRequiredViewAsType(source, R.id.et_pws, "field 'etPws'", ClearEditText.class);
    target.cbAgree = Utils.findRequiredViewAsType(source, R.id.cb_agree, "field 'cbAgree'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.btn_register, "method 'onViewClicked'");
    view2131296324 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_agreement, "method 'onViewClicked'");
    view2131297264 = view;
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
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUserPhone = null;
    target.txtSendCode = null;
    target.etVerificationCode = null;
    target.etPws = null;
    target.cbAgree = null;

    view2131297328.setOnClickListener(null);
    view2131297328 = null;
    view2131296324.setOnClickListener(null);
    view2131296324 = null;
    view2131297264.setOnClickListener(null);
    view2131297264 = null;
  }
}
