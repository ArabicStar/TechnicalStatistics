package com.nju.va.technicalstatistics.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.TeamGridAdapter;
import com.nju.va.technicalstatistics.info.Match;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/24.
 */
public class TeamFragment extends Fragment {
    int selectorPosition = GridView.INVALID_POSITION;
    GridView teamView;
    List<Team> teams;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page1_team, container, false);

        teamView = (GridView) view.findViewById(R.id.team);
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

    public Team getSelectedItem(){
        if(selectorPosition==GridView.INVALID_POSITION)
            return null;
        else
            return teams.get(selectorPosition);
//        Log.i("selectposition",Integer.toString(selectorPosition));

    }


    private List<Team> getData(){
        Match match = new Match(new Team("A队"),new Team("B队"));
        teams = new ArrayList<Team>(2);
        teams.add(match.getLeftTeam());
        teams.add(match.getRightTeam());
        return teams;
    }
}
