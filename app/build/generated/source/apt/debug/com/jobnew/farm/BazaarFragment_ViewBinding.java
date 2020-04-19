// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BazaarFragment_ViewBinding implements Unbinder {
  private BazaarFragment target;

  private View view2131297256;

  private View view2131296846;

  private View view2131296847;

  private View view2131296848;

  private View view2131296849;

  private View view2131296850;

  private View view2131297252;

  private View view2131297253;

  private View view2131297254;

  private View view2131297255;

  private View view2131296708;

  private View view2131296719;

  private View view2131296570;

  private View view2131296887;

  @UiThread
  public BazaarFragment_ViewBinding(final BazaarFragment target, View source) {
    this.target = target;

    View view;
    target.llTopTitle = Utils.findRequiredViewAsType(source, R.id.ll_TopTitle, "field 'llTopTitle'", LinearLayout.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.ll_title, "field 'llTitle'", LinearLayout.class);
    target.llTitleTop3 = Utils.findRequiredViewAsType(source, R.id.ll_title_top3, "field 'llTitleTop3'", LinearLayout.class);
    target.recyclerViewOne = Utils.findRequiredViewAsType(source, R.id.recycler_view_one, "field 'recyclerViewOne'", RecyclerView.class);
    target.recyclerViewTwo = Utils.findRequiredViewAsType(source, R.id.recycler_view_two, "field 'recyclerViewTwo'", RecyclerView.class);
    target.llTitleTop2 = Utils.findRequiredViewAsType(source, R.id.ll_title_top2, "field 'llTitleTop2'", LinearLayout.class);
    target.llTitleTop22 = Utils.findRequiredViewAsType(source, R.id.ll_title_top2_2, "field 'llTitleTop22'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.two_5, "field 'two5' and method 'onViewClicked'");
    target.two5 = Utils.castView(view, R.id.two_5, "field 'two5'", TextView.class);
    view2131297256 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llAll = Utils.findRequiredViewAsType(source, R.id.ll_all, "field 'llAll'", LinearLayout.class);
    target.txtLocation = Utils.findRequiredViewAsType(source, R.id.txt_location, "field 'txtLocation'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_1, "method 'onViewClicked'");
    view2131296846 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_2, "method 'onViewClicked'");
    view2131296847 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_3, "method 'onViewClicked'");
    view2131296848 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_4, "method 'onViewClicked'");
    view2131296849 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_5, "method 'onViewClicked'");
    view2131296850 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two_1, "method 'onViewClicked'");
    view2131297252 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two_2, "method 'onViewClicked'");
    view2131297253 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two_3, "method 'onViewClicked'");
    view2131297254 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two_4, "method 'onViewClicked'");
    view2131297255 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_more, "method 'onViewClicked'");
    view2131296708 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_shrinkage, "method 'onViewClicked'");
    view2131296719 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_kind_my_shelf, "method 'onViewClicked'");
    view2131296570 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_search, "method 'onViewClicked'");
    view2131296887 = view;
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
    BazaarFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTopTitle = null;
    target.llTitle = null;
    target.llTitleTop3 = null;
    target.recyclerViewOne = null;
    target.recyclerViewTwo = null;
    target.llTitleTop2 = null;
    target.llTitleTop22 = null;
    target.two5 = null;
    target.llAll = null;
    target.txtLocation = null;

    view2131297256.setOnClickListener(null);
    view2131297256 = null;
    view2131296846.setOnClickListener(null);
    view2131296846 = null;
    view2131296847.setOnClickListener(null);
    view2131296847 = null;
    view2131296848.setOnClickListener(null);
    view2131296848 = null;
    view2131296849.setOnClickListener(null);
    view2131296849 = null;
    view2131296850.setOnClickListener(null);
    view2131296850 = null;
    view2131297252.setOnClickListener(null);
    view2131297252 = null;
    view2131297253.setOnClickListener(null);
    view2131297253 = null;
    view2131297254.setOnClickListener(null);
    view2131297254 = null;
    view2131297255.setOnClickListener(null);
    view2131297255 = null;
    view2131296708.setOnClickListener(null);
    view2131296708 = null;
    view2131296719.setOnClickListener(null);
    view2131296719 = null;
    view2131296570.setOnClickListener(null);
    view2131296570 = null;
    view2131296887.setOnClickListener(null);
    view2131296887 = null;
  }
}
