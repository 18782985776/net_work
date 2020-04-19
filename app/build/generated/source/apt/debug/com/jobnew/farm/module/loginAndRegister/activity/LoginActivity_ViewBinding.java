// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.loginAndRegister.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131297290;

  private View view2131297325;

  private View view2131296321;

  private View view2131296580;

  private View view2131296596;

  private View view2131296593;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.et_phone_num, "field 'etUsername'", ClearEditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_pwd, "field 'etPassword'", ClearEditText.class);
    view = Utils.findRequiredView(source, R.id.txt_forget_pwd, "method 'onViewClicked'");
    view2131297290 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_register, "method 'onViewClicked'");
    view2131297325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'onViewClicked'");
    view2131296321 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_qq, "method 'onViewClicked'");
    view2131296580 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_weixing, "method 'onViewClicked'");
    view2131296596 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_weibo, "method 'onViewClicked'");
    view2131296593 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUsername = null;
    target.etPassword = null;

    view2131297290.setOnClickListener(null);
    view2131297290 = null;
    view2131297325.setOnClickListener(null);
    view2131297325 = null;
    view2131296321.setOnClickListener(null);
    view2131296321 = null;
    view2131296580.setOnClickListener(null);
    view2131296580 = null;
    view2131296596.setOnClickListener(null);
    view2131296596 = null;
    view2131296593.setOnClickListener(null);
    view2131296593 = null;
  }
}
