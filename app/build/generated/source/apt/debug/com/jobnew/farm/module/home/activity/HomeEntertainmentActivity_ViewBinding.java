// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeEntertainmentActivity_ViewBinding implements Unbinder {
  private HomeEntertainmentActivity target;

  private View view2131296669;

  private View view2131296670;

  private View view2131296671;

  @UiThread
  public HomeEntertainmentActivity_ViewBinding(HomeEntertainmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeEntertainmentActivity_ViewBinding(final HomeEntertainmentActivity target,
      View source) {
    this.target = target;

    View view;
    target.gradeTv = Utils.findRequiredViewAsType(source, R.id.grade_tv, "field 'gradeTv'", TextView.class);
    target.gradeImg = Utils.findRequiredViewAsType(source, R.id.grade_img, "field 'gradeImg'", ImageView.class);
    target.newwestTv = Utils.findRequiredViewAsType(source, R.id.newwest_tv, "field 'newwestTv'", TextView.class);
    target.newwestImg = Utils.findRequiredViewAsType(source, R.id.newwest_img, "field 'newwestImg'", ImageView.class);
    target.distanceFilterTv = Utils.findRequiredViewAsType(source, R.id.distance_filter_tv, "field 'distanceFilterTv'", TextView.class);
    target.distanceFilterImg = Utils.findRequiredViewAsType(source, R.id.distance_filter_img, "field 'distanceFilterImg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.linear1, "field 'linear1' and method 'dealClick'");
    target.linear1 = Utils.castView(view, R.id.linear1, "field 'linear1'", LinearLayout.class);
    view2131296669 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.linear2, "field 'linear2' and method 'dealClick'");
    target.linear2 = Utils.castView(view, R.id.linear2, "field 'linear2'", LinearLayout.class);
    view2131296670 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.linear3, "field 'linear3' and method 'dealClick'");
    target.linear3 = Utils.castView(view, R.id.linear3, "field 'linear3'", LinearLayout.class);
    view2131296671 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeEntertainmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gradeTv = null;
    target.gradeImg = null;
    target.newwestTv = null;
    target.newwestImg = null;
    target.distanceFilterTv = null;
    target.distanceFilterImg = null;
    target.linear1 = null;
    target.linear2 = null;
    target.linear3 = null;

    view2131296669.setOnClickListener(null);
    view2131296669 = null;
    view2131296670.setOnClickListener(null);
    view2131296670 = null;
    view2131296671.setOnClickListener(null);
    view2131296671 = null;
  }
}
