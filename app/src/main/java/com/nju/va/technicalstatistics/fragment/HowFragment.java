package com.nju.va.technicalstatistics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Point;

/**
 * Created by jqwu on 2017/1/24.
 */
public class HowFragment extends Fragment {
    GridView methodView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page3_how, container, false);

        methodView = (GridView) view.findViewById(R.id.method_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.gridview_my_simple, Point.METHOD_NAME);
        methodView.setAdapter(adapter);
        methodView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        methodView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(methodView.isItemChecked(i))
                    view.setBackgroundResource(R.color.darkskyblue);
                else
                    view.setBackgroundResource(R.color.transparent);

            }
        });
        return view;
    }

    public String getSelectedItem(){
        return (String) methodView.getSelectedItem();
    }
}
