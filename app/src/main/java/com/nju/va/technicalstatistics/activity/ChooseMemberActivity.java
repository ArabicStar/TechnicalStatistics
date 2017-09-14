package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.MemberAdapter;
import com.nju.va.technicalstatistics.info.Match;

public class ChooseMemberActivity extends AppCompatActivity {

    Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_member);

        match = (Match) getIntent().getParcelableExtra("match_data");

        MemberAdapter adapter1 = new MemberAdapter(ChooseMemberActivity.this,R.layout.line_member, match.getLeftTeam().getMembers());
        MemberAdapter adapter2 = new MemberAdapter(ChooseMemberActivity.this,R.layout.line_member, match.getRightTeam().getMembers());
        ((TextView) findViewById(R.id.team_name1)).setText(match.getLeftTeam().getName());
        ((TextView) findViewById(R.id.team_name2)).setText(match.getRightTeam().getName());

        final ListView memberList1 = (ListView) findViewById(R.id.member_list1);
        final ListView memberList2 = (ListView) findViewById(R.id.member_list2);
        final Button confirmBtn = (Button)findViewById(R.id.confirm);

        memberList1.setAdapter(adapter1);
        memberList1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        memberList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(memberList1.isItemChecked(i))
                    view.setBackgroundResource(R.color.darkskyblue);
                else
                    view.setBackgroundResource(R.color.transparent);

            }
        });

        memberList2.setAdapter(adapter2);
        memberList2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        memberList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(memberList1.isItemChecked(i))
                    view.setBackgroundResource(R.color.darkskyblue);
                else
                    view.setBackgroundResource(R.color.transparent);

            }
        });



        confirmBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(memberList1.getCheckedItemCount()<6||memberList2.getCheckedItemCount()<6)
                    Toast.makeText(getApplicationContext(),"每支队伍各选至少6名首发队员",Toast.LENGTH_SHORT).show();
                else{
                    startActivity(new Intent("android.intent.action.SCORE"));
                }

            }
        });

    }

}
