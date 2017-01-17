package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.MemberAdapter;
import com.nju.va.technicalstatistics.adapter.TeamAdapter;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.List;

public class TeamDetailActivity extends AppCompatActivity {

    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        team = (Team) getIntent().getSerializableExtra("team_data");
        initInfo();
    }

    private void initInfo(){
        if(team==null)
            throw new NullPointerException("队伍数据没有初始化");

        TextView name = (TextView) findViewById(R.id.team_name);
        name.setText(team.getName());

        MemberAdapter adapter = new MemberAdapter(TeamDetailActivity.this,R.layout.line_member,team.getMembers());
        final ListView memberList = (ListView) findViewById(R.id.member_list);
        memberList.setAdapter(adapter);

    }
}
