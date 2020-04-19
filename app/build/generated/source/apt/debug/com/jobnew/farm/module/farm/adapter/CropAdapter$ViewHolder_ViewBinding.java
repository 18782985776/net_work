// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CropAdapter$ViewHolder_ViewBinding implements Unbinder {
  private CropAdapter.ViewHolder target;

  @UiThread
  public CropAdapter$ViewHolder_ViewBinding(CropAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ivCrop = Utils.findRequiredViewAsType(source, R.id.iv_crop, "field 'ivCrop'", ImageView.class);
    target.tvCropName = Utils.findRequiredViewAsType(source, R.id.tv_crop_name, "field 'tvCropName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CropAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivCrop = null;
    target.tvCropName = null;
  }
}
