package com.nju.va.technicalstatistics.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.nju.va.technicalstatistics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/24.
 */
public class WhyFragment extends Fragment {
    GridView whyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page2_why, container, false);

        whyView = (GridView) view.findViewById(R.id.why);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.gridview_my_simple, getData());
        whyView.setAdapter(adapter);
        whyView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        whyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(whyView.isItemChecked(i))
                    view.setBackgroundResource(R.color.darkskyblue);
                else
                    view.setBackgroundResource(R.color.transparent);

            }
        });
        return view;
    }

    public String getSelectedItem(){
        return (String) whyView.getSelectedItem();
    }

    private List<String> getData(){
        List<String> names = new ArrayList<String>(2);
        names.add("主动得分");
        names.add("被动得分");
        return names;
    }
}
