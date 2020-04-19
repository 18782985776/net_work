package com.jobnew.farm.utils;

import android.content.Context;
import android.util.TypedValue;

import com.aries.ui.widget.action.sheet.UIActionSheetView;


/**
 * Created by wufengqiao on 2017/5/17
 * Function:
 * Desc:
 */
public class ActionSheetUtil {

    public static UIActionSheetView show(Context context, String[] items, UIActionSheetView.OnSheetItemListener onSheetItemListener) {
        return show(context, "", "", items, onSheetItemListener, true, true);
    }

    public static UIActionSheetView show(Context context, int items, UIActionSheetView.OnSheetItemListener onSheetItemListener) {
        return show(context, 0, 0, items, onSheetItemListener, true, true);
    }

    public static UIActionSheetView show(Context context, String title, String[] items, UIActionSheetView.OnSheetItemListener onSheetItemListener) {
        return show(context, title, "", items, onSheetItemListener, true, true);
    }

    public static UIActionSheetView show(Context context, int title, int items, UIActionSheetView.OnSheetItemListener onSheetItemListener) {
        return show(context, title, 0, items, onSheetItemListener, true, true);
    }

    public static UIActionSheetView show(Context context, String title, String cancel, String[] items, UIActionSheetView.OnSheetItemListener onSheetItemListener) {
        return show(context, title, cancel, items, onSheetItemListener, true, true);
    }

    public static UIActionSheetView show(Context context, int title, int cancel, int items, UIActionSheetView.OnSheetItemListener onSheetItemListener) {
        return show(context, title, cancel, items, onSheetItemListener, true, true);
    }

    public static UIActionSheetView show(Context context, int title, int cancel, int items, UIActionSheetView.OnSheetItemListener onSheetItemListener, boolean canceledOnTouchOut, boolean cancelable) {
        UIActionSheetView sheetView = getActionSheetView(context);
        if (title != 0) {
            sheetView.setTitle(title);
        }
        if (cancel != 0) {
            sheetView.setCancelMessage(cancel);
        }
        if (items != 0) {
            sheetView.setItems(items, onSheetItemListener);
        }
        sheetView.setCancelable(cancelable);
        sheetView.setCanceledOnTouchOutside(canceledOnTouchOut);
        sheetView.show();
        return sheetView;
    }


    public static UIActionSheetView show(Context context, String title, String cancel, String[] items, UIActionSheetView.OnSheetItemListener onSheetItemListener, boolean canceledOnTouchOut, boolean cancelable) {
        UIActionSheetView sheetView = getActionSheetView(context);
        if (!title.isEmpty()) {
            sheetView.setTitle(title);
        }
        if (!cancel.isEmpty()) {
            sheetView.setCancelMessage(cancel);
        }
        if (items != null && items.length > 0) {
            sheetView.setItems(items, onSheetItemListener);
        }
        sheetView.setCancelable(cancelable);
        sheetView.setCanceledOnTouchOutside(canceledOnTouchOut);
        sheetView.show();
        return sheetView;
    }

    private static UIActionSheetView getActionSheetView(Context context) {
        UIActionSheetView actionSheetView = new UIActionSheetView(context).builder();
        actionSheetView.setTitleTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        actionSheetView.setCancelMessageTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        actionSheetView.setItemsTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        actionSheetView.setCancelMessage(com.aries.ui.widget.action.sheet.R.string.cancel);
        return actionSheetView;
    }
}
