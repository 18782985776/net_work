// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.order;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AfterDetailsActivity_ViewBinding implements Unbinder {
  private AfterDetailsActivity target;

  @UiThread
  public AfterDetailsActivity_ViewBinding(AfterDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AfterDetailsActivity_ViewBinding(AfterDetailsActivity target, View source) {
    this.target = target;

    target.txtIsGet = Utils.findRequiredViewAsType(source, R.id.txt_is_get, "field 'txtIsGet'", TextView.class);
    target.txtWhy = Utils.findRequiredViewAsType(source, R.id.txt_why, "field 'txtWhy'", TextView.class);
    target.txtMoney = Utils.findRequiredViewAsType(source, R.id.txt_money, "field 'txtMoney'", TextView.class);
    target.txtIntro = Utils.findRequiredViewAsType(source, R.id.txt_intro, "field 'txtIntro'", TextView.class);
    target.txtPic = Utils.findRequiredViewAsType(source, R.id.txt_pic, "field 'txtPic'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.txtIsWhy = Utils.findRequiredViewAsType(source, R.id.txt_is_why, "field 'txtIsWhy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AfterDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtIsGet = null;
    target.txtWhy = null;
    target.txtMoney = null;
    target.txtIntro = null;
    target.txtPic = null;
    target.recyclerView = null;
    target.txtIsWhy = null;
  }
}
