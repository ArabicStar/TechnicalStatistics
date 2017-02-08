package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.MemberAdapter;
import com.nju.va.technicalstatistics.data.TeamHibernator;
import com.nju.va.technicalstatistics.data.impl.TeamSqliteHibernator;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

public class AddTeamActivity extends AppCompatActivity {

    Team team;
    MemberAdapter adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    team.addMember((Member)data.getSerializableExtra("member_data"));
                    adapter.notifyDataSetChanged();
                }
                break;
            default:

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        Intent fromIntent = getIntent();
        if(fromIntent.getIntExtra("from", 0)==2){
            team = (Team) getIntent().getParcelableExtra("team_data");
        }else
            team = new Team();

        final Button cancelBtn = (Button)findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        final Button addMemberBtn = (Button)findViewById(R.id.add_member_button);
        addMemberBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(AddTeamActivity.this,AddMemberActivity.class);
                startActivityForResult(intent,1);
            }
        });

        final ListView memberList = (ListView) findViewById(R.id.member_list);
        adapter = new MemberAdapter(AddTeamActivity.this,R.layout.line_member,team.getMembers());
        memberList.setAdapter(adapter);

        final TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(team.getName());

        final Button saveBtn = (Button)findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            ;
            @Override
            public void onClick(View view) {
                team.setName(nameView.getText().toString());
                TeamHibernator teamHibernator = new TeamSqliteHibernator(AddTeamActivity.this);
                if(team.getId()==0) {
                    teamHibernator.save(team);
                    teamHibernator.close();
                    Toast.makeText(getApplicationContext(),"创建队伍成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    teamHibernator.update(team);
                    teamHibernator.close();
                    Toast.makeText(getApplicationContext(),"队伍修改成功",Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });


    }


}
