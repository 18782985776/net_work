// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindPhoneActivity_ViewBinding implements Unbinder {
  private BindPhoneActivity target;

  private View view2131297328;

  private View view2131296319;

  @UiThread
  public BindPhoneActivity_ViewBinding(BindPhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindPhoneActivity_ViewBinding(final BindPhoneActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.btn_forget, "method 'onViewClicked'");
    view2131296319 = view;
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
    BindPhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUserPhone = null;
    target.txtSendCode = null;
    target.etVerificationCode = null;

    view2131297328.setOnClickListener(null);
    view2131297328 = null;
    view2131296319.setOnClickListener(null);
    view2131296319 = null;
  }
}
