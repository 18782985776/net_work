// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeaveMessageActivity_ViewBinding implements Unbinder {
  private LeaveMessageActivity target;

  @UiThread
  public LeaveMessageActivity_ViewBinding(LeaveMessageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeaveMessageActivity_ViewBinding(LeaveMessageActivity target, View source) {
    this.target = target;

    target.editMessage = Utils.findRequiredViewAsType(source, R.id.edit_message, "field 'editMessage'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeaveMessageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editMessage = null;
  }
}
