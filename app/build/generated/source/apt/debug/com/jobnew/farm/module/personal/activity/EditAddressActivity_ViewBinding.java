// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditAddressActivity_ViewBinding implements Unbinder {
  private EditAddressActivity target;

  private View view2131296811;

  private View view2131296695;

  @UiThread
  public EditAddressActivity_ViewBinding(EditAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditAddressActivity_ViewBinding(final EditAddressActivity target, View source) {
    this.target = target;

    View view;
    target.edtName = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edtName'", ClearEditText.class);
    target.edtPhone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edtPhone'", ClearEditText.class);
    target.txtSelectAddress = Utils.findRequiredViewAsType(source, R.id.txt_select_address, "field 'txtSelectAddress'", TextView.class);
    target.etDetailedAddress = Utils.findRequiredViewAsType(source, R.id.et_detailed_address, "field 'etDetailedAddress'", EditText.class);
    view = Utils.findRequiredView(source, R.id.provincial_city_layout, "method 'onViewClicked'");
    view2131296811 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_deleteAdress, "method 'onViewClicked'");
    view2131296695 = view;
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
    EditAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtName = null;
    target.edtPhone = null;
    target.txtSelectAddress = null;
    target.etDetailedAddress = null;

    view2131296811.setOnClickListener(null);
    view2131296811 = null;
    view2131296695.setOnClickListener(null);
    view2131296695 = null;
  }
}
