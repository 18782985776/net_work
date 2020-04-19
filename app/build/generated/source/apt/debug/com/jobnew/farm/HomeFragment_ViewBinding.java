// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.coverFlowView.CoverFlowView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view2131296717;

  private View view2131296584;

  private View view2131297305;

  private View view2131296867;

  private View view2131296683;

  private View view2131296685;

  private View view2131296706;

  private View view2131296700;

  private View view2131296702;

  private View view2131296705;

  private View view2131296698;

  private View view2131296692;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.llTopTitle = Utils.findRequiredViewAsType(source, R.id.ll_TopTitle, "field 'llTopTitle'", LinearLayout.class);
    target.coverFlowView = Utils.findRequiredViewAsType(source, R.id.coverflow_view, "field 'coverFlowView'", CoverFlowView.class);
    target.txtBannerName = Utils.findRequiredViewAsType(source, R.id.txt_banner_name, "field 'txtBannerName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_search, "field 'layoutSearch' and method 'onViewClicked'");
    target.layoutSearch = Utils.castView(view, R.id.ll_search, "field 'layoutSearch'", LinearLayout.class);
    view2131296717 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.textViewLocation = Utils.findRequiredViewAsType(source, R.id.txt_location, "field 'textViewLocation'", TextView.class);
    target.llActivityLl = Utils.findRequiredViewAsType(source, R.id.ll_activity_ll, "field 'llActivityLl'", LinearLayout.class);
    target.llGeneratedLl = Utils.findRequiredViewAsType(source, R.id.ll_generated_ll, "field 'llGeneratedLl'", LinearLayout.class);
    target.llBazaarLl = Utils.findRequiredViewAsType(source, R.id.ll_bazaar_ll, "field 'llBazaarLl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.img_scan, "method 'onViewClicked'");
    view2131296584 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_msg, "method 'onViewClicked'");
    view2131297305 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_farm, "method 'onViewClicked'");
    view2131296867 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_activity, "method 'onViewClicked'");
    view2131296683 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_activity_more, "method 'onViewClicked'");
    view2131296685 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_live, "method 'onViewClicked'");
    view2131296706 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_generated, "method 'onViewClicked'");
    view2131296700 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_generated_more, "method 'onViewClicked'");
    view2131296702 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_kitchen, "method 'onViewClicked'");
    view2131296705 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_exclusive_farm, "method 'onViewClicked'");
    view2131296698 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_bazaar_more, "method 'onViewClicked'");
    view2131296692 = view;
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
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTopTitle = null;
    target.coverFlowView = null;
    target.txtBannerName = null;
    target.layoutSearch = null;
    target.textViewLocation = null;
    target.llActivityLl = null;
    target.llGeneratedLl = null;
    target.llBazaarLl = null;

    view2131296717.setOnClickListener(null);
    view2131296717 = null;
    view2131296584.setOnClickListener(null);
    view2131296584 = null;
    view2131297305.setOnClickListener(null);
    view2131297305 = null;
    view2131296867.setOnClickListener(null);
    view2131296867 = null;
    view2131296683.setOnClickListener(null);
    view2131296683 = null;
    view2131296685.setOnClickListener(null);
    view2131296685 = null;
    view2131296706.setOnClickListener(null);
    view2131296706 = null;
    view2131296700.setOnClickListener(null);
    view2131296700 = null;
    view2131296702.setOnClickListener(null);
    view2131296702 = null;
    view2131296705.setOnClickListener(null);
    view2131296705 = null;
    view2131296698.setOnClickListener(null);
    view2131296698 = null;
    view2131296692.setOnClickListener(null);
    view2131296692 = null;
  }
}
