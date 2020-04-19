// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanActivity_ViewBinding implements Unbinder {
  private ScanActivity target;

  private View view2131296553;

  private View view2131296855;

  @UiThread
  public ScanActivity_ViewBinding(ScanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanActivity_ViewBinding(final ScanActivity target, View source) {
    this.target = target;

    View view;
    target.qrZx = Utils.findRequiredViewAsType(source, R.id.qr_zx, "field 'qrZx'", ZXingView.class);
    target.rlTitleTop = Utils.findRequiredViewAsType(source, R.id.rl_titleTop, "field 'rlTitleTop'", RelativeLayout.class);
    target.txtQita = Utils.findRequiredViewAsType(source, R.id.txt_qita, "field 'txtQita'", TextView.class);
    view = Utils.findRequiredView(source, R.id.img_back, "method 'onViewClicked'");
    view2131296553 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_back, "method 'onViewClicked'");
    view2131296855 = view;
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
    ScanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qrZx = null;
    target.rlTitleTop = null;
    target.txtQita = null;

    view2131296553.setOnClickListener(null);
    view2131296553 = null;
    view2131296855.setOnClickListener(null);
    view2131296855 = null;
  }
}
