package com.jobnew.farm.widget;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.jobnew.farm.R;

/**
 * Created by wufengqiao on 2017/6/2.
 * function：
 * desc：
 */

public class QuantityHelper implements View.OnClickListener {


    private View minus;
    private EditText editText;
    private View add;
    private int count;
    private boolean isImportability;

    int maximum = Integer.MAX_VALUE;
    int minimum = 0;
    private OnCountChangeListener onCountChangeListener;

    public QuantityHelper(View view) {
        this(view, false, 0, Integer.MAX_VALUE);
    }

    public QuantityHelper(View view, boolean isImportability) {
        this(view, isImportability, 0, Integer.MAX_VALUE);
    }

    public QuantityHelper(View view, int minimum, int maximum) {
        this(view, false, minimum, maximum);
    }

    QuantityHelper(View view, boolean isImportability, int minimum, int maximum) {
        minus = view.findViewById(R.id.iv_minus);
        editText = (EditText) view.findViewById(R.id.et_count);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                count = getCount();
                checkNum();
                if (onCountChangeListener != null) {
                    onCountChangeListener.onCountChange(count);
                }
            }
        });
        add = view.findViewById(R.id.iv_add);

        minus.setOnClickListener(this);
        add.setOnClickListener(this);

        this.isImportability = isImportability;
        editText.setFocusable(isImportability);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.minimum = minimum;
        this.maximum = maximum;
        initCount();

    }

    private void initCount() {
        if (minimum < 0) {
            if (maximum > 0) {
                count = 0;
            } else {
                count = maximum;
            }
        } else {
            count = minimum;
        }
        editText.setText(count + "");
    }

    public interface OnCountChangeListener {
        public void onCountChange(int count);
    }

    @Override
    public void onClick(View v) {
        count = getCount();
        switch (v.getId()) {
            case R.id.iv_minus:
                count--;
                checkNum();

                break;
            case R.id.iv_add:
                count++;
                checkNum();
                break;
        }
        editText.setText(count + "");
    }

    private int checkNum() {
        if (count < minimum) {
            this.count = minimum;
            editText.setText(count + "");
        } else if (count > maximum) {
            this.count = maximum;
            editText.setText(count + "");
        }
        return this.count;
    }

    public void setOnCountChangeListener(OnCountChangeListener listener) {
        onCountChangeListener = listener;
    }

    /**
     * 设置是否输入
     *
     * @param isImportability
     */
    public void setImportability(boolean isImportability) {
        this.isImportability = isImportability;
        editText.setFocusable(isImportability);
    }

    /**
     * 设置数量
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
        checkNum();
        editText.setText(count + "");
    }

    /**
     * 获取数量
     *
     * @return
     */
    public int getCount() {
        String str = editText.getText().toString();
        if (TextUtils.isEmpty(str)) {
            count = 0;
        } else {
            count = Integer.valueOf(str);
        }
        checkNum();
        return count;
    }

    /**
     * 设置最大值
     *
     * @param maximum
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
        checkNum();
        editText.setText(count + "");
    }

    /**
     * 设置最小值
     *
     * @param minimum
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
        checkNum();
        editText.setText(count + "");
    }
}
