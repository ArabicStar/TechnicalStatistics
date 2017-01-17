package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.TeamAdapter;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.POSITION;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamListView extends AppCompatActivity {
    private List<Team> teamList = new ArrayList<Team>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_team_list);

        initTeams();

        TeamAdapter adapter = new TeamAdapter(TeamListView.this,R.layout.line_team,teamList);
        final ListView teamList = (ListView) findViewById(R.id.team_list);
        teamList.setAdapter(adapter);
        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Team team = (Team) teamList.getItemAtPosition(position);
                Intent intent = new Intent(TeamListView.this,TeamDetailActivity.class);
                intent.putExtra("team_data",team);
                startActivity(intent);
            }
        });

        Button addBtn = (Button)findViewById(R.id.add_team_button);
        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent("android.intent.action.ADDTEAM"));
            }
        });
    }

    /**
     * 用于测试的方法，生成一些队伍信息
     */
    private void initTeams(){
        Team t1 = new Team("江苏男子排球队");
        Member m1 = new Member("张晨",6, POSITION.主攻);
        t1.addMember(m1);
        Member m2 = new Member("于垚辰",5, POSITION.二传);
        t1.addMember(m2);
        teamList.add(t1);
        Team t2 = new Team("上海金色年华男子排球队");
        teamList.add(t2);
        Team t3 = new Team("八一盈冠男子排球队");
        teamList.add(t3);
        Team t4 = new Team("山东体彩男子排球队");
        teamList.add(t4);
    }

}
