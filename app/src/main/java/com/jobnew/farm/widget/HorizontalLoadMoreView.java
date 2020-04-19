package com.jobnew.farm.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.jobnew.farm.R;

/**
 * Created by wufengqiao on 2017/6/16.
 * function：
 * desc：
 */

public class HorizontalLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.layout_loading;
    }

    @Override
    protected int getLoadingViewId() {
        return com.chad.library.R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return com.chad.library.R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return com.chad.library.R.id.load_more_load_end_view;
    }
}
