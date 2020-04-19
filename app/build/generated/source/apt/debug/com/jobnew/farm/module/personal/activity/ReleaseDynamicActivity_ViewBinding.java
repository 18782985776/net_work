// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReleaseDynamicActivity_ViewBinding implements Unbinder {
  private ReleaseDynamicActivity target;

  private View view2131297000;

  @UiThread
  public ReleaseDynamicActivity_ViewBinding(ReleaseDynamicActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReleaseDynamicActivity_ViewBinding(final ReleaseDynamicActivity target, View source) {
    this.target = target;

    View view;
    target.gridView = Utils.findRequiredViewAsType(source, R.id.feed_gridview, "field 'gridView'", GridView.class);
    target.etContent = Utils.findRequiredViewAsType(source, R.id.et_content, "field 'etContent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submit_eva, "method 'onViewClicked'");
    view2131297000 = view;
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
    ReleaseDynamicActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gridView = null;
    target.etContent = null;

    view2131297000.setOnClickListener(null);
    view2131297000 = null;
  }
}
