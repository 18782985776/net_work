// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

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

public class SelectCropActivity_ViewBinding implements Unbinder {
  private SelectCropActivity target;

  private View view2131297214;

  @UiThread
  public SelectCropActivity_ViewBinding(SelectCropActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectCropActivity_ViewBinding(final SelectCropActivity target, View source) {
    this.target = target;

    View view;
    target.tvCropName = Utils.findRequiredViewAsType(source, R.id.tv_crop_name, "field 'tvCropName'", TextView.class);
    target.tvUnitPrive = Utils.findRequiredViewAsType(source, R.id.tv_unit_prive, "field 'tvUnitPrive'", TextView.class);
    target.tvMaturityCycle = Utils.findRequiredViewAsType(source, R.id.tv_maturity_cycle, "field 'tvMaturityCycle'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.tvUnit = Utils.findRequiredViewAsType(source, R.id.tv_unit, "field 'tvUnit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    target.area = Utils.findRequiredView(source, R.id.quantity_area, "field 'area'");
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectCropActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCropName = null;
    target.tvUnitPrive = null;
    target.tvMaturityCycle = null;
    target.tvTotalPrice = null;
    target.tvUnit = null;
    target.tvSave = null;
    target.rvContent = null;
    target.area = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
