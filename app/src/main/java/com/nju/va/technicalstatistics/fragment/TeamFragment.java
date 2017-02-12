package com.nju.va.technicalstatistics.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Match;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/24.
 */
public class TeamFragment extends Fragment {
    Match match;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page1_team, container, false);

        final GridView teamView = (GridView) view.findViewById(R.id.team);
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
        match = new Match(new Team("A队"),new Team("B队"));
        List<String> names = new ArrayList<String>(2);
        names.add(match.getLeftTeam().getName());
        names.add(match.getRightTeam().getName());
        return names;
    }
}
