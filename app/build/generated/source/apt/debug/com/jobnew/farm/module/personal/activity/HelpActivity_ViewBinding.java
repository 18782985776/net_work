// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.marno.easystatelibrary.EasyStatusView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HelpActivity_ViewBinding implements Unbinder {
  private HelpActivity target;

  @UiThread
  public HelpActivity_ViewBinding(HelpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HelpActivity_ViewBinding(HelpActivity target, View source) {
    this.target = target;

    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    target.ptrLayout = Utils.findRequiredViewAsType(source, R.id.ptr_layout, "field 'ptrLayout'", PtrFrameLayout.class);
    target.esvMain = Utils.findRequiredViewAsType(source, R.id.esv_main, "field 'esvMain'", EasyStatusView.class);
    target.tvSave = Utils.findRequiredViewAsType(source, R.id.tv_save, "field 'tvSave'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HelpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvContent = null;
    target.ptrLayout = null;
    target.esvMain = null;
    target.tvSave = null;
  }
}
