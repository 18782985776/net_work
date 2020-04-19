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
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingFrequencyActivity_ViewBinding implements Unbinder {
  private SettingFrequencyActivity target;

  private View view2131297214;

  @UiThread
  public SettingFrequencyActivity_ViewBinding(SettingFrequencyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingFrequencyActivity_ViewBinding(final SettingFrequencyActivity target, View source) {
    this.target = target;

    View view;
    target.tvRightDay = Utils.findRequiredViewAsType(source, R.id.tv_right_day, "field 'tvRightDay'", TextView.class);
    target.stvPlantCycle = Utils.findRequiredViewAsType(source, R.id.stv_plant_cycle, "field 'stvPlantCycle'", SuperTextView.class);
    target.view = Utils.findRequiredView(source, R.id.layout_plant_cycle, "field 'view'");
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "method 'onClick'");
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingFrequencyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRightDay = null;
    target.stvPlantCycle = null;
    target.view = null;
    target.rvContent = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
