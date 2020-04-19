// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserHomeActivity_ViewBinding implements Unbinder {
  private UserHomeActivity target;

  private View view2131296728;

  private View view2131296549;

  @UiThread
  public UserHomeActivity_ViewBinding(UserHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserHomeActivity_ViewBinding(final UserHomeActivity target, View source) {
    this.target = target;

    View view;
    target.banner = Utils.findRequiredViewAsType(source, R.id.convenientBanner, "field 'banner'", ConvenientBanner.class);
    target.txtName = Utils.findRequiredViewAsType(source, R.id.txt_name, "field 'txtName'", TextView.class);
    target.imgSex = Utils.findRequiredViewAsType(source, R.id.img_sex, "field 'imgSex'", ImageView.class);
    target.txtCity = Utils.findRequiredViewAsType(source, R.id.txt_city, "field 'txtCity'", TextView.class);
    target.imgAvatar = Utils.findRequiredViewAsType(source, R.id.img_avatar, "field 'imgAvatar'", CircleImageView.class);
    target.txtSignature = Utils.findRequiredViewAsType(source, R.id.txt_signature, "field 'txtSignature'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_user1, "method 'onViewClicked'");
    view2131296728 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_add_dynamic, "method 'onViewClicked'");
    view2131296549 = view;
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
    UserHomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.banner = null;
    target.txtName = null;
    target.imgSex = null;
    target.txtCity = null;
    target.imgAvatar = null;
    target.txtSignature = null;
    target.recyclerView = null;

    view2131296728.setOnClickListener(null);
    view2131296728 = null;
    view2131296549.setOnClickListener(null);
    view2131296549 = null;
  }
}
