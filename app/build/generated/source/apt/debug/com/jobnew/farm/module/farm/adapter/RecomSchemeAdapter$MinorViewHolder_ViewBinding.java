// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecomSchemeAdapter$MinorViewHolder_ViewBinding implements Unbinder {
  private RecomSchemeAdapter.MinorViewHolder target;

  @UiThread
  public RecomSchemeAdapter$MinorViewHolder_ViewBinding(RecomSchemeAdapter.MinorViewHolder target,
      View source) {
    this.target = target;

    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
    target.stvName = Utils.findRequiredViewAsType(source, R.id.stv_name, "field 'stvName'", SuperTextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.etFrequencyCycle = Utils.findRequiredViewAsType(source, R.id.et_frequency_cycle, "field 'etFrequencyCycle'", EditText.class);
    target.tvFrequencyUnit = Utils.findRequiredViewAsType(source, R.id.tv_frequency_unit, "field 'tvFrequencyUnit'", TextView.class);
    target.frequencyName = Utils.findRequiredViewAsType(source, R.id.frequency_name, "field 'frequencyName'", SuperTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecomSchemeAdapter.MinorViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvUnitPrice = null;
    target.stvName = null;
    target.tvName = null;
    target.etFrequencyCycle = null;
    target.tvFrequencyUnit = null;
    target.frequencyName = null;
  }
}
