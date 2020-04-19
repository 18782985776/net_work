// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInfoActivity_ViewBinding implements Unbinder {
  private UserInfoActivity target;

  private View view2131296882;

  private View view2131296870;

  private View view2131296878;

  private View view2131296852;

  private View view2131296863;

  private View view2131296720;

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInfoActivity_ViewBinding(final UserInfoActivity target, View source) {
    this.target = target;

    View view;
    target.txtMessageNickname = Utils.findRequiredViewAsType(source, R.id.txt_messageNickname, "field 'txtMessageNickname'", TextView.class);
    target.txtMessageGender = Utils.findRequiredViewAsType(source, R.id.txt_messageGender, "field 'txtMessageGender'", TextView.class);
    target.txtLocation = Utils.findRequiredViewAsType(source, R.id.txt_location, "field 'txtLocation'", TextView.class);
    target.txtSignature = Utils.findRequiredViewAsType(source, R.id.txt_signature, "field 'txtSignature'", TextView.class);
    target.imgMessageHeader = Utils.findRequiredViewAsType(source, R.id.img_messageHeader, "field 'imgMessageHeader'", CircleImageView.class);
    target.txtBirthday = Utils.findRequiredViewAsType(source, R.id.txt_birthday, "field 'txtBirthday'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_photo, "method 'onViewClicked'");
    view2131296882 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_gender, "method 'onViewClicked'");
    view2131296870 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_nike, "method 'onViewClicked'");
    view2131296878 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_address, "method 'onViewClicked'");
    view2131296852 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_date_selection, "method 'onViewClicked'");
    view2131296863 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_signature, "method 'onViewClicked'");
    view2131296720 = view;
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
    UserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtMessageNickname = null;
    target.txtMessageGender = null;
    target.txtLocation = null;
    target.txtSignature = null;
    target.imgMessageHeader = null;
    target.txtBirthday = null;

    view2131296882.setOnClickListener(null);
    view2131296882 = null;
    view2131296870.setOnClickListener(null);
    view2131296870 = null;
    view2131296878.setOnClickListener(null);
    view2131296878 = null;
    view2131296852.setOnClickListener(null);
    view2131296852 = null;
    view2131296863.setOnClickListener(null);
    view2131296863 = null;
    view2131296720.setOnClickListener(null);
    view2131296720 = null;
  }
}
