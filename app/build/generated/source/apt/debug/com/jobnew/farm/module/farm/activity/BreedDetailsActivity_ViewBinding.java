// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BreedDetailsActivity_ViewBinding implements Unbinder {
  private BreedDetailsActivity target;

  private View view2131296376;

  private View view2131297062;

  private View view2131297237;

  private View view2131297214;

  private View view2131296620;

  @UiThread
  public BreedDetailsActivity_ViewBinding(BreedDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BreedDetailsActivity_ViewBinding(final BreedDetailsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.convenientBanner, "field 'convenientBanner' and method 'onViewClicked'");
    target.convenientBanner = Utils.castView(view, R.id.convenientBanner, "field 'convenientBanner'", ConvenientBanner.class);
    view2131296376 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBreedName = Utils.findRequiredViewAsType(source, R.id.tv_breed_name, "field 'tvBreedName'", TextView.class);
    target.tvUnitPrive = Utils.findRequiredViewAsType(source, R.id.tv_unit_prive, "field 'tvUnitPrive'", TextView.class);
    target.tvUnit = Utils.findRequiredViewAsType(source, R.id.tv_unit, "field 'tvUnit'", TextView.class);
    target.tvUpdateDateValus = Utils.findRequiredViewAsType(source, R.id.tv_update_date_valus, "field 'tvUpdateDateValus'", TextView.class);
    target.tvRepertory = Utils.findRequiredViewAsType(source, R.id.tv_repertory, "field 'tvRepertory'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_address, "field 'tvAddress' and method 'onViewClicked'");
    target.tvAddress = Utils.castView(view, R.id.tv_address, "field 'tvAddress'", TextView.class);
    view2131297062 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBreedDesc = Utils.findRequiredViewAsType(source, R.id.tv_breed_desc, "field 'tvBreedDesc'", TextView.class);
    target.tvCommentCount = Utils.findRequiredViewAsType(source, R.id.tv_comment_count, "field 'tvCommentCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_total_conment, "field 'tvTotalConment' and method 'onViewClicked'");
    target.tvTotalConment = Utils.castView(view, R.id.tv_total_conment, "field 'tvTotalConment'", TextView.class);
    view2131297237 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_call, "method 'onViewClicked'");
    view2131296620 = view;
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
    BreedDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.convenientBanner = null;
    target.tvBreedName = null;
    target.tvUnitPrive = null;
    target.tvUnit = null;
    target.tvUpdateDateValus = null;
    target.tvRepertory = null;
    target.tvAddress = null;
    target.tvBreedDesc = null;
    target.tvCommentCount = null;
    target.tvTotalConment = null;
    target.tvSave = null;

    view2131296376.setOnClickListener(null);
    view2131296376 = null;
    view2131297062.setOnClickListener(null);
    view2131297062 = null;
    view2131297237.setOnClickListener(null);
    view2131297237 = null;
    view2131297214.setOnClickListener(null);
    view2131297214 = null;
    view2131296620.setOnClickListener(null);
    view2131296620 = null;
  }
}
