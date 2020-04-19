// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.exclusive;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExclusiveLandActivity_ViewBinding implements Unbinder {
  private ExclusiveLandActivity target;

  private View view2131297202;

  private View view2131297229;

  private View view2131296884;

  private View view2131296864;

  private View view2131296854;

  @UiThread
  public ExclusiveLandActivity_ViewBinding(ExclusiveLandActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExclusiveLandActivity_ViewBinding(final ExclusiveLandActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_ranking_filter, "field 'tvRankingFilter' and method 'onViewClicked'");
    target.tvRankingFilter = Utils.castView(view, R.id.tv_ranking_filter, "field 'tvRankingFilter'", TextView.class);
    view2131297202 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPriceFilter = Utils.findRequiredViewAsType(source, R.id.tv_price_filter, "field 'tvPriceFilter'", CheckedTextView.class);
    target.tvAreaFilter = Utils.findRequiredViewAsType(source, R.id.tv_area_filter, "field 'tvAreaFilter'", CheckedTextView.class);
    target.tvDistanceFilter = Utils.findRequiredViewAsType(source, R.id.tv_distance_filter, "field 'tvDistanceFilter'", CheckedTextView.class);
    view = Utils.findRequiredView(source, R.id.tv_super_filter, "field 'tvSuperFilter' and method 'onViewClicked'");
    target.tvSuperFilter = Utils.castView(view, R.id.tv_super_filter, "field 'tvSuperFilter'", TextView.class);
    view2131297229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvTitle = Utils.findRequiredViewAsType(source, R.id.rv_title, "field 'rvTitle'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.rl_price_filter, "method 'onViewClicked'");
    view2131296884 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_distance_filter, "method 'onViewClicked'");
    view2131296864 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_area_filter, "method 'onViewClicked'");
    view2131296854 = view;
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
    ExclusiveLandActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRankingFilter = null;
    target.tvPriceFilter = null;
    target.tvAreaFilter = null;
    target.tvDistanceFilter = null;
    target.tvSuperFilter = null;
    target.rvTitle = null;

    view2131297202.setOnClickListener(null);
    view2131297202 = null;
    view2131297229.setOnClickListener(null);
    view2131297229 = null;
    view2131296884.setOnClickListener(null);
    view2131296884 = null;
    view2131296864.setOnClickListener(null);
    view2131296864 = null;
    view2131296854.setOnClickListener(null);
    view2131296854 = null;
  }
}
