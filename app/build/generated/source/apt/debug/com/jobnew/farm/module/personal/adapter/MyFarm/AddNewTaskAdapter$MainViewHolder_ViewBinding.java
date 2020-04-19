// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddNewTaskAdapter$MainViewHolder_ViewBinding implements Unbinder {
  private AddNewTaskAdapter.MainViewHolder target;

  @UiThread
  public AddNewTaskAdapter$MainViewHolder_ViewBinding(AddNewTaskAdapter.MainViewHolder target,
      View source) {
    this.target = target;

    target.stvName = Utils.findRequiredViewAsType(source, R.id.stv_name, "field 'stvName'", SuperTextView.class);
    target.frequencyName = Utils.findRequiredViewAsType(source, R.id.frequency_name, "field 'frequencyName'", SuperTextView.class);
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddNewTaskAdapter.MainViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stvName = null;
    target.frequencyName = null;
    target.rvContent = null;
  }
}
