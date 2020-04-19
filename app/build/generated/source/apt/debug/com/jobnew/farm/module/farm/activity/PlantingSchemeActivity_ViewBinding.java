// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlantingSchemeActivity_ViewBinding implements Unbinder {
  private PlantingSchemeActivity target;

  private View view2131297205;

  private View view2131297096;

  @UiThread
  public PlantingSchemeActivity_ViewBinding(PlantingSchemeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlantingSchemeActivity_ViewBinding(final PlantingSchemeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_recom_scheme, "field 'tvRecomScheme' and method 'onViewClicked'");
    target.tvRecomScheme = Utils.castView(view, R.id.tv_recom_scheme, "field 'tvRecomScheme'", TextView.class);
    view2131297205 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_custom_scheme, "field 'tvCustomScheme' and method 'onViewClicked'");
    target.tvCustomScheme = Utils.castView(view, R.id.tv_custom_scheme, "field 'tvCustomScheme'", TextView.class);
    view2131297096 = view;
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
    PlantingSchemeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRecomScheme = null;
    target.tvCustomScheme = null;

    view2131297205.setOnClickListener(null);
    view2131297205 = null;
    view2131297096.setOnClickListener(null);
    view2131297096 = null;
  }
}
