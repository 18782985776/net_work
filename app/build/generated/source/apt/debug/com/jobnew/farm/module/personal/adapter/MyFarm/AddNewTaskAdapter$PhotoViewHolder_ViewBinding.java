// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddNewTaskAdapter$PhotoViewHolder_ViewBinding implements Unbinder {
  private AddNewTaskAdapter.PhotoViewHolder target;

  @UiThread
  public AddNewTaskAdapter$PhotoViewHolder_ViewBinding(AddNewTaskAdapter.PhotoViewHolder target,
      View source) {
    this.target = target;

    target.tvPhotoPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvPhotoPrice'", TextView.class);
    target.stvPhoto = Utils.findRequiredViewAsType(source, R.id.stv_photo, "field 'stvPhoto'", SuperTextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.stvDate = Utils.findRequiredViewAsType(source, R.id.stv_date, "field 'stvDate'", SuperTextView.class);
    target.countView = Utils.findRequiredView(source, R.id.layout_photo_count, "field 'countView'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AddNewTaskAdapter.PhotoViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPhotoPrice = null;
    target.stvPhoto = null;
    target.tvName = null;
    target.stvDate = null;
    target.countView = null;
  }
}
