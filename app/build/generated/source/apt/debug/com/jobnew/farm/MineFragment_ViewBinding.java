// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view2131296886;

  private View view2131296889;

  private View view2131296892;

  private View view2131296874;

  private View view2131296888;

  private View view2131296862;

  private View view2131296872;

  private View view2131296879;

  private View view2131296712;

  private View view2131296696;

  private View view2131296703;

  private View view2131296697;

  private View view2131296877;

  private View view2131296890;

  private View view2131296875;

  private View view2131296871;

  private View view2131296860;

  private View view2131296876;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    target.llTopTitle = Utils.findRequiredViewAsType(source, R.id.ll_TopTitle, "field 'llTopTitle'", LinearLayout.class);
    target.cirImgPhoto = Utils.findRequiredViewAsType(source, R.id.cirImg_photo, "field 'cirImgPhoto'", CircleImageView.class);
    target.txtName = Utils.findRequiredViewAsType(source, R.id.txt_name, "field 'txtName'", TextView.class);
    target.imgSex = Utils.findRequiredViewAsType(source, R.id.img_sex, "field 'imgSex'", ImageView.class);
    target.txtLevel = Utils.findRequiredViewAsType(source, R.id.txt_level, "field 'txtLevel'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_scanner, "field 'rlScanner' and method 'onViewClicked'");
    target.rlScanner = Utils.castView(view, R.id.rl_scanner, "field 'rlScanner'", ImageView.class);
    view2131296886 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_share, "method 'onViewClicked'");
    view2131296889 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_user_home, "method 'onViewClicked'");
    view2131296892 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_address, "method 'onViewClicked'");
    view2131296874 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_setting, "method 'onViewClicked'");
    view2131296888 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_customer, "method 'onViewClicked'");
    view2131296862 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_investment, "method 'onViewClicked'");
    view2131296872 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_order, "method 'onViewClicked'");
    view2131296879 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_payment_order, "method 'onViewClicked'");
    view2131296712 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_delivery_order, "method 'onViewClicked'");
    view2131296696 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_goods_order, "method 'onViewClicked'");
    view2131296703 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_evaluation_order, "method 'onViewClicked'");
    view2131296697 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_wallet, "method 'onViewClicked'");
    view2131296877 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_shop, "method 'onViewClicked'");
    view2131296890 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_farm, "method 'onViewClicked'");
    view2131296875 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_help, "method 'onViewClicked'");
    view2131296871 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_collection, "method 'onViewClicked'");
    view2131296860 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_my_generated, "method 'onViewClicked'");
    view2131296876 = view;
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
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTopTitle = null;
    target.cirImgPhoto = null;
    target.txtName = null;
    target.imgSex = null;
    target.txtLevel = null;
    target.rlScanner = null;

    view2131296886.setOnClickListener(null);
    view2131296886 = null;
    view2131296889.setOnClickListener(null);
    view2131296889 = null;
    view2131296892.setOnClickListener(null);
    view2131296892 = null;
    view2131296874.setOnClickListener(null);
    view2131296874 = null;
    view2131296888.setOnClickListener(null);
    view2131296888 = null;
    view2131296862.setOnClickListener(null);
    view2131296862 = null;
    view2131296872.setOnClickListener(null);
    view2131296872 = null;
    view2131296879.setOnClickListener(null);
    view2131296879 = null;
    view2131296712.setOnClickListener(null);
    view2131296712 = null;
    view2131296696.setOnClickListener(null);
    view2131296696 = null;
    view2131296703.setOnClickListener(null);
    view2131296703 = null;
    view2131296697.setOnClickListener(null);
    view2131296697 = null;
    view2131296877.setOnClickListener(null);
    view2131296877 = null;
    view2131296890.setOnClickListener(null);
    view2131296890 = null;
    view2131296875.setOnClickListener(null);
    view2131296875 = null;
    view2131296871.setOnClickListener(null);
    view2131296871 = null;
    view2131296860.setOnClickListener(null);
    view2131296860 = null;
    view2131296876.setOnClickListener(null);
    view2131296876 = null;
  }
}
