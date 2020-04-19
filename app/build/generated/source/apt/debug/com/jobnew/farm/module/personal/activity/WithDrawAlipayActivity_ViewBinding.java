// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WithDrawAlipayActivity_ViewBinding implements Unbinder {
  private WithDrawAlipayActivity target;

  private View view2131297260;

  private View view2131297001;

  private View view2131296686;

  @UiThread
  public WithDrawAlipayActivity_ViewBinding(WithDrawAlipayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithDrawAlipayActivity_ViewBinding(final WithDrawAlipayActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.txt_add, "method 'onViewClicked'");
    view2131297260 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.submit_withdraw, "method 'onViewClicked'");
    view2131297001 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_add, "method 'onViewClicked'");
    view2131296686 = view;
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
    WithDrawAlipayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;

    view2131297260.setOnClickListener(null);
    view2131297260 = null;
    view2131297001.setOnClickListener(null);
    view2131297001 = null;
    view2131296686.setOnClickListener(null);
    view2131296686 = null;
  }
}
