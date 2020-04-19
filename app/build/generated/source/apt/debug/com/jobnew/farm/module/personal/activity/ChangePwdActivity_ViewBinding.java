// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePwdActivity_ViewBinding implements Unbinder {
  private ChangePwdActivity target;

  private View view2131297272;

  @UiThread
  public ChangePwdActivity_ViewBinding(ChangePwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePwdActivity_ViewBinding(final ChangePwdActivity target, View source) {
    this.target = target;

    View view;
    target.etOriginal = Utils.findRequiredViewAsType(source, R.id.et_original, "field 'etOriginal'", ClearEditText.class);
    target.etNew = Utils.findRequiredViewAsType(source, R.id.et_new, "field 'etNew'", ClearEditText.class);
    target.etNewAgain = Utils.findRequiredViewAsType(source, R.id.et_new_again, "field 'etNewAgain'", ClearEditText.class);
    view = Utils.findRequiredView(source, R.id.txt_change_pwd, "method 'onViewClicked'");
    view2131297272 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etOriginal = null;
    target.etNew = null;
    target.etNewAgain = null;

    view2131297272.setOnClickListener(null);
    view2131297272 = null;
  }
}
