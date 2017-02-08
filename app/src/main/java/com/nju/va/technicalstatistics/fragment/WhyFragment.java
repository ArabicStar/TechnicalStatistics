package com.nju.va.technicalstatistics.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Point;

/**
 * Created by jqwu on 2017/1/24.
 */
public class WhyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page2_why, container, false);



        return view;
    }
}
