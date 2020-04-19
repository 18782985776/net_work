// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoAlbumMngActivity_ViewBinding implements Unbinder {
  private PhotoAlbumMngActivity target;

  @UiThread
  public PhotoAlbumMngActivity_ViewBinding(PhotoAlbumMngActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoAlbumMngActivity_ViewBinding(PhotoAlbumMngActivity target, View source) {
    this.target = target;

    target.feedGridview = Utils.findRequiredViewAsType(source, R.id.feed_gridview, "field 'feedGridview'", GridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhotoAlbumMngActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.feedGridview = null;
  }
}
