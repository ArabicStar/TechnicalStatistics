package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nju.va.technicalstatistics.R;

public class AddMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        initComp();


    }

    private void initComp(){
        // 初始化控件
        final Spinner positionList = (Spinner) findViewById(R.id.member_position_list);
        final EditText name = (EditText)findViewById(R.id.member_name);
        final EditText number = (EditText)findViewById(R.id.member_number);
        Button addBtn = (Button)findViewById(R.id.add) ;
        Button cancelBtn = (Button)findViewById(R.id.cancel) ;

        //addBtn初始化
        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(number.getText().length()!=0&&name.getText().length()!=0){
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"请完整填写队员信息",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }
}
