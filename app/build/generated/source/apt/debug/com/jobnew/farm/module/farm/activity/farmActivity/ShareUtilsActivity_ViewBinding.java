// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareUtilsActivity_ViewBinding implements Unbinder {
  private ShareUtilsActivity target;

  private View view2131296580;

  private View view2131296594;

  private View view2131296593;

  private View view2131296579;

  private View view2131296597;

  private View view2131296346;

  private View view2131297363;

  @UiThread
  public ShareUtilsActivity_ViewBinding(ShareUtilsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareUtilsActivity_ViewBinding(final ShareUtilsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.img_qq, "field 'imgQq' and method 'OnClick'");
    target.imgQq = Utils.castView(view, R.id.img_qq, "field 'imgQq'", ImageView.class);
    view2131296580 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_weixin, "field 'imgWeixin' and method 'OnClick'");
    target.imgWeixin = Utils.castView(view, R.id.img_weixin, "field 'imgWeixin'", ImageView.class);
    view2131296594 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_weibo, "field 'imgWeibo' and method 'OnClick'");
    target.imgWeibo = Utils.castView(view, R.id.img_weibo, "field 'imgWeibo'", ImageView.class);
    view2131296593 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_pyq, "field 'imgPyq' and method 'OnClick'");
    target.imgPyq = Utils.castView(view, R.id.img_pyq, "field 'imgPyq'", ImageView.class);
    view2131296579 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_zone, "field 'imgZone' and method 'OnClick'");
    target.imgZone = Utils.castView(view, R.id.img_zone, "field 'imgZone'", ImageView.class);
    view2131296597 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cancletv, "field 'cancletv' and method 'OnClick'");
    target.cancletv = Utils.castView(view, R.id.cancletv, "field 'cancletv'", TextView.class);
    view2131296346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view, "method 'OnClick'");
    view2131297363 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareUtilsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgQq = null;
    target.imgWeixin = null;
    target.imgWeibo = null;
    target.imgPyq = null;
    target.imgZone = null;
    target.cancletv = null;

    view2131296580.setOnClickListener(null);
    view2131296580 = null;
    view2131296594.setOnClickListener(null);
    view2131296594 = null;
    view2131296593.setOnClickListener(null);
    view2131296593 = null;
    view2131296579.setOnClickListener(null);
    view2131296579 = null;
    view2131296597.setOnClickListener(null);
    view2131296597 = null;
    view2131296346.setOnClickListener(null);
    view2131296346 = null;
    view2131297363.setOnClickListener(null);
    view2131297363 = null;
  }
}
