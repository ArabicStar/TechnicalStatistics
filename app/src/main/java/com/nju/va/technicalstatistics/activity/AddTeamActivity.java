package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

public class AddTeamActivity extends AppCompatActivity {

    Team team;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    team.addMember((Member)data.getParcelableExtra("member_data"));
                }
                break;
            default:

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
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
    }


}
