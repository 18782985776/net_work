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

public class KindMyShelfActivity_ViewBinding implements Unbinder {
  private KindMyShelfActivity target;

  private View view2131297298;

  private View view2131296883;

  @UiThread
  public KindMyShelfActivity_ViewBinding(KindMyShelfActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public KindMyShelfActivity_ViewBinding(final KindMyShelfActivity target, View source) {
    this.target = target;

    View view;
    target.txtPrice = Utils.findRequiredViewAsType(source, R.id.txt_price, "field 'txtPrice'", TextView.class);
    target.imgPrice = Utils.findRequiredViewAsType(source, R.id.img_price, "field 'imgPrice'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.txt_location, "field 'txtLocation' and method 'onViewClicked'");
    target.txtLocation = Utils.castView(view, R.id.txt_location, "field 'txtLocation'", TextView.class);
    view2131297298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_price, "method 'onViewClicked'");
    view2131296883 = view;
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
    KindMyShelfActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtPrice = null;
    target.imgPrice = null;
    target.txtLocation = null;

    view2131297298.setOnClickListener(null);
    view2131297298 = null;
    view2131296883.setOnClickListener(null);
    view2131296883 = null;
  }
}
