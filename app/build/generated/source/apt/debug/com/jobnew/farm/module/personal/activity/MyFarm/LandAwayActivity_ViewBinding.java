// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.MyFarm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LandAwayActivity_ViewBinding implements Unbinder {
  private LandAwayActivity target;

  private View view2131297326;

  private View view2131296314;

  @UiThread
  public LandAwayActivity_ViewBinding(LandAwayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LandAwayActivity_ViewBinding(final LandAwayActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.edt = Utils.findRequiredViewAsType(source, R.id.edt, "field 'edt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.txt_search, "method 'onViewClicked'");
    view2131297326 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_away, "method 'onViewClicked'");
    view2131296314 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LandAwayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.edt = null;

    view2131297326.setOnClickListener(null);
    view2131297326 = null;
    view2131296314.setOnClickListener(null);
    view2131296314 = null;
  }
}
