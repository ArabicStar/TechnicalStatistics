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
import com.nju.va.technicalstatistics.info.Match;
import com.nju.va.technicalstatistics.info.Point;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/24.
 */
public class WhyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page2_why, container, false);

        final GridView teamView = (GridView) view.findViewById(R.id.why);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, getData());
        teamView.setAdapter(adapter);
        teamView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        teamView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(teamView.isItemChecked(i))
                    view.setBackgroundResource(R.color.darkskyblue);
                else
                    view.setBackgroundResource(R.color.transparent);

            }
        });
        return view;
    }

    private List<String> getData(){
        List<String> names = new ArrayList<String>(2);
        names.add("主动得分");
        names.add("被动得分");
        return names;
    }
}
