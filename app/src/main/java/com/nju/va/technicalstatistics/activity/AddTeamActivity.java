package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nju.va.technicalstatistics.R;

public class AddTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        final Button cancelBtn = (Button)findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        final Button addMemberBtn = (Button)findViewById(R.id.add_member_button);
        addMemberBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent("android.intent.action.ADDMEMBER"));
            }
        });
    }
}
