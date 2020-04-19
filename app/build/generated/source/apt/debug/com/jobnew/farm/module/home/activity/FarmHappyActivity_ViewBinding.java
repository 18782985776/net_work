// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.marno.easystatelibrary.EasyStatusView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmHappyActivity_ViewBinding implements Unbinder {
  private FarmHappyActivity target;

  private View view2131297202;

  private View view2131297121;

  private View view2131297104;

  private View view2131297229;

  private View view2131297228;

  private View view2131297114;

  private View view2131297078;

  private View view2131297075;

  private View view2131297122;

  private View view2131296327;

  private View view2131296328;

  private View view2131296329;

  @UiThread
  public FarmHappyActivity_ViewBinding(FarmHappyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FarmHappyActivity_ViewBinding(final FarmHappyActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_ranking_filter, "field 'tvRankingFilter' and method 'ControlData'");
    target.tvRankingFilter = Utils.castView(view, R.id.tv_ranking_filter, "field 'tvRankingFilter'", TextView.class);
    view2131297202 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_hot_filter, "field 'tvHotFilter' and method 'ControlData'");
    target.tvHotFilter = Utils.castView(view, R.id.tv_hot_filter, "field 'tvHotFilter'", TextView.class);
    view2131297121 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_distance_filter, "field 'tvDistanceFilter' and method 'ControlData'");
    target.tvDistanceFilter = Utils.castView(view, R.id.tv_distance_filter, "field 'tvDistanceFilter'", TextView.class);
    view2131297104 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_super_filter, "field 'tvSuperFilter' and method 'ControlData'");
    target.tvSuperFilter = Utils.castView(view, R.id.tv_super_filter, "field 'tvSuperFilter'", TextView.class);
    view2131297229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img1 = Utils.findRequiredViewAsType(source, R.id.img1, "field 'img1'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_super_cancle, "field 'tvSuperCancle' and method 'ControlData'");
    target.tvSuperCancle = Utils.castView(view, R.id.tv_super_cancle, "field 'tvSuperCancle'", TextView.class);
    view2131297228 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img2 = Utils.findRequiredViewAsType(source, R.id.img2, "field 'img2'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_fishing, "field 'tvFishing' and method 'ControlData'");
    target.tvFishing = Utils.castView(view, R.id.tv_fishing, "field 'tvFishing'", TextView.class);
    view2131297114 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img3 = Utils.findRequiredViewAsType(source, R.id.img3, "field 'img3'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_chess_and_card, "field 'tvChessAndCard' and method 'ControlData'");
    target.tvChessAndCard = Utils.castView(view, R.id.tv_chess_and_card, "field 'tvChessAndCard'", TextView.class);
    view2131297078 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img4 = Utils.findRequiredViewAsType(source, R.id.img4, "field 'img4'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_catering, "field 'tvCatering' and method 'ControlData'");
    target.tvCatering = Utils.castView(view, R.id.tv_catering, "field 'tvCatering'", TextView.class);
    view2131297075 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img5 = Utils.findRequiredViewAsType(source, R.id.img5, "field 'img5'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_hotel, "field 'tvHotel' and method 'ControlData'");
    target.tvHotel = Utils.castView(view, R.id.tv_hotel, "field 'tvHotel'", TextView.class);
    view2131297122 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img6 = Utils.findRequiredViewAsType(source, R.id.img6, "field 'img6'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.businesstv6, "field 'businesstv6' and method 'ControlData'");
    target.businesstv6 = Utils.castView(view, R.id.businesstv6, "field 'businesstv6'", TextView.class);
    view2131296327 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img7 = Utils.findRequiredViewAsType(source, R.id.img7, "field 'img7'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.businesstv7, "field 'businesstv7' and method 'ControlData'");
    target.businesstv7 = Utils.castView(view, R.id.businesstv7, "field 'businesstv7'", TextView.class);
    view2131296328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.img8 = Utils.findRequiredViewAsType(source, R.id.img8, "field 'img8'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.businesstv8, "field 'businesstv8' and method 'ControlData'");
    target.businesstv8 = Utils.castView(view, R.id.businesstv8, "field 'businesstv8'", TextView.class);
    view2131296329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ControlData(Utils.<TextView>castParam(p0, "doClick", 0, "ControlData", 0));
      }
    });
    target.superFilterMenue = Utils.findRequiredViewAsType(source, R.id.super_filter_menue, "field 'superFilterMenue'", HorizontalScrollView.class);
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    target.ptrLayout = Utils.findRequiredViewAsType(source, R.id.ptr_layout, "field 'ptrLayout'", PtrFrameLayout.class);
    target.esvMain = Utils.findRequiredViewAsType(source, R.id.esv_main, "field 'esvMain'", EasyStatusView.class);
    target.hotImg = Utils.findRequiredViewAsType(source, R.id.hot_img, "field 'hotImg'", ImageView.class);
    target.distanceImg = Utils.findRequiredViewAsType(source, R.id.distance_img, "field 'distanceImg'", ImageView.class);
    target.superImg = Utils.findRequiredViewAsType(source, R.id.super_img, "field 'superImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FarmHappyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivTitleRight = null;
    target.titleBar = null;
    target.tvRankingFilter = null;
    target.tvHotFilter = null;
    target.tvDistanceFilter = null;
    target.tvSuperFilter = null;
    target.img1 = null;
    target.tvSuperCancle = null;
    target.img2 = null;
    target.tvFishing = null;
    target.img3 = null;
    target.tvChessAndCard = null;
    target.img4 = null;
    target.tvCatering = null;
    target.img5 = null;
    target.tvHotel = null;
    target.img6 = null;
    target.businesstv6 = null;
    target.img7 = null;
    target.businesstv7 = null;
    target.img8 = null;
    target.businesstv8 = null;
    target.superFilterMenue = null;
    target.rvContent = null;
    target.ptrLayout = null;
    target.esvMain = null;
    target.hotImg = null;
    target.distanceImg = null;
    target.superImg = null;

    view2131297202.setOnClickListener(null);
    view2131297202 = null;
    view2131297121.setOnClickListener(null);
    view2131297121 = null;
    view2131297104.setOnClickListener(null);
    view2131297104 = null;
    view2131297229.setOnClickListener(null);
    view2131297229 = null;
    view2131297228.setOnClickListener(null);
    view2131297228 = null;
    view2131297114.setOnClickListener(null);
    view2131297114 = null;
    view2131297078.setOnClickListener(null);
    view2131297078 = null;
    view2131297075.setOnClickListener(null);
    view2131297075 = null;
    view2131297122.setOnClickListener(null);
    view2131297122 = null;
    view2131296327.setOnClickListener(null);
    view2131296327 = null;
    view2131296328.setOnClickListener(null);
    view2131296328 = null;
    view2131296329.setOnClickListener(null);
    view2131296329 = null;
  }
}
