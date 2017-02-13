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
import com.nju.va.technicalstatistics.adapter.TeamGridAdapter;
import com.nju.va.technicalstatistics.info.Match;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import net.simonvt.menudrawer.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/24.
 */
public class TeamFragment extends Fragment {
    Match match;
    int selectorPosition = GridView.INVALID_POSITION;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page1_team, container, false);

        final GridView teamView = (GridView) view.findViewById(R.id.team);
        final TeamGridAdapter adapter = new TeamGridAdapter(getContext(),R.layout.gridview_my_simple,getData());
        teamView.setAdapter(adapter);
        teamView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        teamView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.changeState(i);
                selectorPosition = i;
            }
        });

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            bundle.getParcelable("point_data");
        }
        return view;
    }


    private List<Team> getData(){
        match = new Match(new Team("A队"),new Team("B队"));
        List<Team> teams = new ArrayList<Team>(2);
        teams.add(match.getLeftTeam());
        teams.add(match.getRightTeam());
        return teams;
    }
}
