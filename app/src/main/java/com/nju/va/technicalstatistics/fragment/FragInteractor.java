package com.nju.va.technicalstatistics.fragment;

import android.os.Bundle;

/**
 * Created by jqwu on 2017/2/13.
 */

public interface FragInteractor {
    /**
     * Fragment 向Activity传递指令，这个方法可以根据需求来定义
     * @param bundle
     */
    public void process(Bundle bundle);
}
