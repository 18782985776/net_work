// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.MyFarm;

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

public class AddNewTaskAcitivity_ViewBinding implements Unbinder {
  private AddNewTaskAcitivity target;

  private View view2131297214;

  @UiThread
  public AddNewTaskAcitivity_ViewBinding(AddNewTaskAcitivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddNewTaskAcitivity_ViewBinding(final AddNewTaskAcitivity target, View source) {
    this.target = target;

    View view;
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "method 'onViewClicked'");
    view2131297214 = view;
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
    AddNewTaskAcitivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTotalPrice = null;
    target.rvContent = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
