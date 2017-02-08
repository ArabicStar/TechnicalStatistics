package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
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

        team = (Team) getIntent().getParcelableExtra("team_data");
        initInfo();
    }

    private void initInfo(){
        if(team==null)
            throw new NullPointerException("队伍数据没有初始化");

        TextView name = (TextView) findViewById(R.id.team_name);
        name.setText(team.getName());

        //初始化按钮
        final Button modBtn = (Button) findViewById(R.id.modify);
        modBtn.setEnabled(false);

        MemberAdapter adapter = new MemberAdapter(TeamDetailActivity.this,R.layout.line_member,team.getMembers());
        final ListView memberList = (ListView) findViewById(R.id.member_list);
        memberList.setAdapter(adapter);
        memberList.setChoiceMode(ListView.CHOICE_MODE_NONE);

        modBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TeamDetailActivity.this,AddTeamActivity.class);
                intent.putExtra("from",2);
                intent.putExtra("team_data",team);
                startActivity(intent);
            }
        });

    }
}
