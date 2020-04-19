// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewAddAddressActivity_ViewBinding implements Unbinder {
  private NewAddAddressActivity target;

  private View view2131296852;

  @UiThread
  public NewAddAddressActivity_ViewBinding(NewAddAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewAddAddressActivity_ViewBinding(final NewAddAddressActivity target, View source) {
    this.target = target;

    View view;
    target.txtAddress = Utils.findRequiredViewAsType(source, R.id.txt_address, "field 'txtAddress'", TextView.class);
    target.etUserName = Utils.findRequiredViewAsType(source, R.id.et_user_name, "field 'etUserName'", ClearEditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", ClearEditText.class);
    target.cbAgree = Utils.findRequiredViewAsType(source, R.id.cb_agree, "field 'cbAgree'", CheckBox.class);
    target.etDetailedAddress = Utils.findRequiredViewAsType(source, R.id.et_detailed_address, "field 'etDetailedAddress'", EditText.class);
    view = Utils.findRequiredView(source, R.id.rl_address, "method 'onViewClicked'");
    view2131296852 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    NewAddAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtAddress = null;
    target.etUserName = null;
    target.etPhone = null;
    target.cbAgree = null;
    target.etDetailedAddress = null;

    view2131296852.setOnClickListener(null);
    view2131296852 = null;
  }
}
