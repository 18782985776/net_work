// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecomSchemeAdapter$MainViewHolder_ViewBinding implements Unbinder {
  private RecomSchemeAdapter.MainViewHolder target;

  @UiThread
  public RecomSchemeAdapter$MainViewHolder_ViewBinding(RecomSchemeAdapter.MainViewHolder target,
      View source) {
    this.target = target;

    target.stvName = Utils.findRequiredViewAsType(source, R.id.stv_name, "field 'stvName'", SuperTextView.class);
    target.etFrequencyCycle = Utils.findRequiredViewAsType(source, R.id.et_frequency_cycle, "field 'etFrequencyCycle'", EditText.class);
    target.tvFrequencyUnit = Utils.findRequiredViewAsType(source, R.id.tv_frequency_unit, "field 'tvFrequencyUnit'", TextView.class);
    target.frequencyName = Utils.findRequiredViewAsType(source, R.id.frequency_name, "field 'frequencyName'", SuperTextView.class);
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecomSchemeAdapter.MainViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stvName = null;
    target.etFrequencyCycle = null;
    target.tvFrequencyUnit = null;
    target.frequencyName = null;
    target.rvContent = null;
  }
}
