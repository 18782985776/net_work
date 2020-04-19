// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.data.downLoad;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownActivity_ViewBinding implements Unbinder {
  private DownActivity target;

  private View view2131296308;

  @UiThread
  public DownActivity_ViewBinding(DownActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DownActivity_ViewBinding(final DownActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bt, "field 'bt' and method 'dealClick'");
    target.bt = Utils.castView(view, R.id.bt, "field 'bt'", Button.class);
    view2131296308 = view;
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
    DownActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bt = null;

    view2131296308.setOnClickListener(null);
    view2131296308 = null;
  }
}
