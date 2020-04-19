// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
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

public class MatchInfoActivity_ViewBinding implements Unbinder {
  private MatchInfoActivity target;

  private View view2131296754;

  private View view2131296798;

  private View view2131296645;

  @UiThread
  public MatchInfoActivity_ViewBinding(MatchInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MatchInfoActivity_ViewBinding(final MatchInfoActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", LinearLayout.class);
    target.matchNameTv = Utils.findRequiredViewAsType(source, R.id.match_name_tv, "field 'matchNameTv'", TextView.class);
    target.opennerTv = Utils.findRequiredViewAsType(source, R.id.openner_tv, "field 'opennerTv'", TextView.class);
    target.addressTv = Utils.findRequiredViewAsType(source, R.id.address_tv, "field 'addressTv'", TextView.class);
    target.joinTimeTv = Utils.findRequiredViewAsType(source, R.id.join_time_tv, "field 'joinTimeTv'", TextView.class);
    target.matchTimeTv = Utils.findRequiredViewAsType(source, R.id.match_time_tv, "field 'matchTimeTv'", TextView.class);
    target.matchNumberTv = Utils.findRequiredViewAsType(source, R.id.match_number_tv, "field 'matchNumberTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.message_img, "field 'messageImg' and method 'dealClick'");
    target.messageImg = Utils.castView(view, R.id.message_img, "field 'messageImg'", ImageView.class);
    view2131296754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.phone_img, "field 'phoneImg' and method 'dealClick'");
    target.phoneImg = Utils.castView(view, R.id.phone_img, "field 'phoneImg'", ImageView.class);
    view2131296798 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.awardTv = Utils.findRequiredViewAsType(source, R.id.award_tv, "field 'awardTv'", TextView.class);
    target.matchDetailsTv = Utils.findRequiredViewAsType(source, R.id.match_details_tv, "field 'matchDetailsTv'", TextView.class);
    target.matchRecycleView = Utils.findRequiredViewAsType(source, R.id.match_recycleView, "field 'matchRecycleView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.join, "field 'join' and method 'dealClick'");
    target.join = Utils.castView(view, R.id.join, "field 'join'", TextView.class);
    view2131296645 = view;
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
    MatchInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivTitleRight = null;
    target.titleBar = null;
    target.matchNameTv = null;
    target.opennerTv = null;
    target.addressTv = null;
    target.joinTimeTv = null;
    target.matchTimeTv = null;
    target.matchNumberTv = null;
    target.messageImg = null;
    target.phoneImg = null;
    target.awardTv = null;
    target.matchDetailsTv = null;
    target.matchRecycleView = null;
    target.join = null;

    view2131296754.setOnClickListener(null);
    view2131296754 = null;
    view2131296798.setOnClickListener(null);
    view2131296798 = null;
    view2131296645.setOnClickListener(null);
    view2131296645 = null;
  }
}
