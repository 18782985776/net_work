// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LaunchedActivity_ViewBinding implements Unbinder {
  private LaunchedActivity target;

  private View view2131296965;

  private View view2131296437;

  @UiThread
  public LaunchedActivity_ViewBinding(LaunchedActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LaunchedActivity_ViewBinding(final LaunchedActivity target, View source) {
    this.target = target;

    View view;
    target.activityNameTv = Utils.findRequiredViewAsType(source, R.id.activity_name_tv, "field 'activityNameTv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.startTime_ll, "field 'startTimeLl' and method 'dealClick'");
    target.startTimeLl = Utils.castView(view, R.id.startTime_ll, "field 'startTimeLl'", LinearLayout.class);
    view2131296965 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.endTime_ll, "field 'endTimeLl' and method 'dealClick'");
    target.endTimeLl = Utils.castView(view, R.id.endTime_ll, "field 'endTimeLl'", LinearLayout.class);
    view2131296437 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.addressTv = Utils.findRequiredViewAsType(source, R.id.address_tv, "field 'addressTv'", EditText.class);
    target.recuceTv = Utils.findRequiredViewAsType(source, R.id.recuce_tv, "field 'recuceTv'", TextView.class);
    target.increaseTv = Utils.findRequiredViewAsType(source, R.id.increase_tv, "field 'increaseTv'", TextView.class);
    target.priceTv = Utils.findRequiredViewAsType(source, R.id.price_tv, "field 'priceTv'", EditText.class);
    target.setPhoneTv = Utils.findRequiredViewAsType(source, R.id.set_phone_tv, "field 'setPhoneTv'", EditText.class);
    target.editText2 = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'editText2'", EditText.class);
    target.myRecycleView = Utils.findRequiredViewAsType(source, R.id.myRecycle_view, "field 'myRecycleView'", RecyclerView.class);
    target.startTimeTv = Utils.findRequiredViewAsType(source, R.id.startTime_tv, "field 'startTimeTv'", TextView.class);
    target.endTimeTv = Utils.findRequiredViewAsType(source, R.id.endTime_tv, "field 'endTimeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LaunchedActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.activityNameTv = null;
    target.startTimeLl = null;
    target.endTimeLl = null;
    target.addressTv = null;
    target.recuceTv = null;
    target.increaseTv = null;
    target.priceTv = null;
    target.setPhoneTv = null;
    target.editText2 = null;
    target.myRecycleView = null;
    target.startTimeTv = null;
    target.endTimeTv = null;

    view2131296965.setOnClickListener(null);
    view2131296965 = null;
    view2131296437.setOnClickListener(null);
    view2131296437 = null;
  }
}
