// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BreedingSchemeFragment_ViewBinding implements Unbinder {
  private BreedingSchemeFragment target;

  private View view2131297214;

  @UiThread
  public BreedingSchemeFragment_ViewBinding(final BreedingSchemeFragment target, View source) {
    this.target = target;

    View view;
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
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
    BreedingSchemeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvContent = null;
    target.tvTotalPrice = null;
    target.tvSave = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
