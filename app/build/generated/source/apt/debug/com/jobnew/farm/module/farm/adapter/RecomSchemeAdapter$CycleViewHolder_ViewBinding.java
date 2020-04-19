// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecomSchemeAdapter$CycleViewHolder_ViewBinding implements Unbinder {
  private RecomSchemeAdapter.CycleViewHolder target;

  @UiThread
  public RecomSchemeAdapter$CycleViewHolder_ViewBinding(RecomSchemeAdapter.CycleViewHolder target,
      View source) {
    this.target = target;

    target.tvRightDay = Utils.findRequiredViewAsType(source, R.id.tv_right_day, "field 'tvRightDay'", TextView.class);
    target.stvPlantCycle = Utils.findRequiredViewAsType(source, R.id.stv_plant_cycle, "field 'stvPlantCycle'", SuperTextView.class);
    target.cycleView = Utils.findRequiredView(source, R.id.layout_plant_cycle, "field 'cycleView'");
  }

  @Override
  @CallSuper
  public void unbind() {
    RecomSchemeAdapter.CycleViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRightDay = null;
    target.stvPlantCycle = null;
    target.cycleView = null;
  }
}
