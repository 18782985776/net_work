// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

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

public class WelcomeActivity_ViewBinding implements Unbinder {
  private WelcomeActivity target;

  private View view2131296946;

  @UiThread
  public WelcomeActivity_ViewBinding(WelcomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WelcomeActivity_ViewBinding(final WelcomeActivity target, View source) {
    this.target = target;

    View view;
    target.welcomeImg = Utils.findRequiredViewAsType(source, R.id.welcome_img, "field 'welcomeImg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.skip_tv, "field 'skipTv' and method 'dealClick'");
    target.skipTv = Utils.castView(view, R.id.skip_tv, "field 'skipTv'", TextView.class);
    view2131296946 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    WelcomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.welcomeImg = null;
    target.skipTv = null;

    view2131296946.setOnClickListener(null);
    view2131296946 = null;
  }
}
