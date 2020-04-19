// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view2131296858;

  private View view2131296851;

  private View view2131296859;

  private View view2131296880;

  private View view2131296857;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_change_pwd, "field 'rlChangePwd' and method 'onViewClicked'");
    target.rlChangePwd = Utils.castView(view, R.id.rl_change_pwd, "field 'rlChangePwd'", RelativeLayout.class);
    view2131296858 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.txtNumPhone = Utils.findRequiredViewAsType(source, R.id.txt_num_phone, "field 'txtNumPhone'", TextView.class);
    target.txtPhone = Utils.findRequiredViewAsType(source, R.id.txt_phone, "field 'txtPhone'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_abort_us, "method 'onViewClicked'");
    view2131296851 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_clear, "method 'onViewClicked'");
    view2131296859 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_out_app, "method 'onViewClicked'");
    view2131296880 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_bind_phone, "method 'onViewClicked'");
    view2131296857 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlChangePwd = null;
    target.txtNumPhone = null;
    target.txtPhone = null;

    view2131296858.setOnClickListener(null);
    view2131296858 = null;
    view2131296851.setOnClickListener(null);
    view2131296851 = null;
    view2131296859.setOnClickListener(null);
    view2131296859 = null;
    view2131296880.setOnClickListener(null);
    view2131296880 = null;
    view2131296857.setOnClickListener(null);
    view2131296857 = null;
  }
}
