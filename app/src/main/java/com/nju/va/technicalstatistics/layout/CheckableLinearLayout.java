package com.nju.va.technicalstatistics.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Created by jqwu on 2017/1/31.
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private boolean mChecked;
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;

    }
    @Override
    public boolean isChecked() {
        return mChecked;
    }
    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
