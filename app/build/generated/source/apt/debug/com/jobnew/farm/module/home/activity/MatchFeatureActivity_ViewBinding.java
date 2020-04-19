// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MatchFeatureActivity_ViewBinding implements Unbinder {
  private MatchFeatureActivity target;

  private View view2131296297;

  private View view2131296939;

  private View view2131296763;

  private View view2131297178;

  @UiThread
  public MatchFeatureActivity_ViewBinding(MatchFeatureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MatchFeatureActivity_ViewBinding(final MatchFeatureActivity target, View source) {
    this.target = target;

    View view;
    target.tabMatch = Utils.findRequiredViewAsType(source, R.id.tab_match, "field 'tabMatch'", TabLayout.class);
    target.mPager = Utils.findRequiredViewAsType(source, R.id.m_pager, "field 'mPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.back_img, "field 'backImg' and method 'dealViewClick'");
    target.backImg = Utils.castView(view, R.id.back_img, "field 'backImg'", ImageView.class);
    view2131296297 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_addresstv, "field 'showAddresstv' and method 'dealViewClick'");
    target.showAddresstv = Utils.castView(view, R.id.show_addresstv, "field 'showAddresstv'", TextView.class);
    view2131296939 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.my_match_tv, "field 'myMatchTv' and method 'dealViewClick'");
    target.myMatchTv = Utils.castView(view, R.id.my_match_tv, "field 'myMatchTv'", TextView.class);
    view2131296763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_orderby_address, "field 'tvOrderByAddress' and method 'dealViewClick'");
    target.tvOrderByAddress = Utils.castView(view, R.id.tv_orderby_address, "field 'tvOrderByAddress'", TextView.class);
    view2131297178 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealViewClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MatchFeatureActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabMatch = null;
    target.mPager = null;
    target.backImg = null;
    target.showAddresstv = null;
    target.myMatchTv = null;
    target.tvOrderByAddress = null;

    view2131296297.setOnClickListener(null);
    view2131296297 = null;
    view2131296939.setOnClickListener(null);
    view2131296939 = null;
    view2131296763.setOnClickListener(null);
    view2131296763 = null;
    view2131297178.setOnClickListener(null);
    view2131297178 = null;
  }
}
