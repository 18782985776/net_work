// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchActivityForHome_ViewBinding implements Unbinder {
  private SearchActivityForHome target;

  private View view2131296560;

  private View view2131296345;

  private View view2131296926;

  @UiThread
  public SearchActivityForHome_ViewBinding(SearchActivityForHome target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchActivityForHome_ViewBinding(final SearchActivityForHome target, View source) {
    this.target = target;

    View view;
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    target.indicatorSearchImg = Utils.findRequiredViewAsType(source, R.id.indicator_search_img, "field 'indicatorSearchImg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.img_delete, "field 'imgDelete' and method 'ClickAnswer'");
    target.imgDelete = Utils.castView(view, R.id.img_delete, "field 'imgDelete'", RelativeLayout.class);
    view2131296560 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ClickAnswer(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cancle_search_tv, "field 'cancleSearchTv' and method 'ClickAnswer'");
    target.cancleSearchTv = Utils.castView(view, R.id.cancle_search_tv, "field 'cancleSearchTv'", TextView.class);
    view2131296345 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ClickAnswer(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.search_img, "field 'searchImg' and method 'ClickAnswer'");
    target.searchImg = Utils.castView(view, R.id.search_img, "field 'searchImg'", ImageView.class);
    view2131296926 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ClickAnswer(p0);
      }
    });
    target.TagFlowLayout = Utils.findRequiredViewAsType(source, R.id.TagFlowLayout, "field 'TagFlowLayout'", TagFlowLayout.class);
    target.beforeSearchLayout = Utils.findRequiredViewAsType(source, R.id.before_search_layout, "field 'beforeSearchLayout'", LinearLayout.class);
    target.listViewSearch = Utils.findRequiredViewAsType(source, R.id.listView_search, "field 'listViewSearch'", ListView.class);
    target.searchMainLinearLayout = Utils.findRequiredViewAsType(source, R.id.search_main_linearLayout, "field 'searchMainLinearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchActivityForHome target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText = null;
    target.indicatorSearchImg = null;
    target.imgDelete = null;
    target.cancleSearchTv = null;
    target.searchImg = null;
    target.TagFlowLayout = null;
    target.beforeSearchLayout = null;
    target.listViewSearch = null;
    target.searchMainLinearLayout = null;

    view2131296560.setOnClickListener(null);
    view2131296560 = null;
    view2131296345.setOnClickListener(null);
    view2131296345 = null;
    view2131296926.setOnClickListener(null);
    view2131296926 = null;
  }
}
