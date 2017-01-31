package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.TeamAdapter;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

public class ChooseTeamActivity extends AppCompatActivity {
    private List<Team> teams = new ArrayList<Team>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_team);

        initTeams();

        TeamAdapter adapter = new TeamAdapter(ChooseTeamActivity.this,R.layout.line_team, teams);
        final ListView teamList = (ListView) findViewById(R.id.team_list);
        teamList.setAdapter(adapter);
        teamList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button confirmBtn = (Button)findViewById(R.id.confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(teamList.getCheckedItemCount()!=2)
                    Toast.makeText(getApplicationContext(),"请选择2支队伍",Toast.LENGTH_SHORT).show();
                else{
                    startActivity(new Intent("android.intent.action.SCORE"));
                }



            }
        });


    }

    /**
     * 用于测试的方法，生成一些队伍信息
     */
    private void initTeams(){
        Team t1 = new Team("江苏男子排球队");
        Member m1 = new Member("张晨",6, Member.CHIEF_SETTER);
        t1.addMember(m1);
        Member m2 = new Member("于垚辰",5, Member.CHIEF_SETTER);
        t1.addMember(m2);
        teams.add(t1);
        Team t2 = new Team("上海金色年华男子排球队");
        teams.add(t2);
        Team t3 = new Team("八一盈冠男子排球队");
        teams.add(t3);
        Team t4 = new Team("山东体彩男子排球队");
        teams.add(t4);
    }
}
