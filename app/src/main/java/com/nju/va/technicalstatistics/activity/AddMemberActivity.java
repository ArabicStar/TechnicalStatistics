package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.os.Parcelable;
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
import com.nju.va.technicalstatistics.info.Member;

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
                if(!number.getText().toString().isEmpty()&&!name.getText().toString().isEmpty()){
                    @Member.PlayerPosition int pos=positionList.getSelectedItemPosition();
                    Member member = new Member(name.getText().toString(),Integer.parseInt(number.getText().toString()),pos);
                    Intent intent = new Intent();
                    intent.putExtra("member_data",(Parcelable) member);
                    setResult(RESULT_OK,intent);
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
