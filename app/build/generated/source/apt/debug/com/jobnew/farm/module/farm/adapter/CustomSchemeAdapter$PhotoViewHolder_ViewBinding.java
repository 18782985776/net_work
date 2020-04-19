// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

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

public class CustomSchemeAdapter$PhotoViewHolder_ViewBinding implements Unbinder {
  private CustomSchemeAdapter.PhotoViewHolder target;

  @UiThread
  public CustomSchemeAdapter$PhotoViewHolder_ViewBinding(CustomSchemeAdapter.PhotoViewHolder target,
      View source) {
    this.target = target;

    target.tvPhotoPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvPhotoPrice'", TextView.class);
    target.stvPhoto = Utils.findRequiredViewAsType(source, R.id.stv_photo, "field 'stvPhoto'", SuperTextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.countView = Utils.findRequiredView(source, R.id.layout_photo_count, "field 'countView'");
    target.durationView = Utils.findRequiredView(source, R.id.layout_photo_duration, "field 'durationView'");
    target.intervalView = Utils.findRequiredView(source, R.id.layout_photo_interval, "field 'intervalView'");
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomSchemeAdapter.PhotoViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPhotoPrice = null;
    target.stvPhoto = null;
    target.tvName = null;
    target.countView = null;
    target.durationView = null;
    target.intervalView = null;
  }
}
