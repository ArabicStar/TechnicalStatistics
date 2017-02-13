package com.nju.va.technicalstatistics.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.MemberGridAdapter;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Point;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/2/8.
 */

public class WhoFragment extends Fragment {
    List<Member> members;
    int selectorPosition = GridView.INVALID_POSITION;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page4_who, container, false);

        final GridView memberView = (GridView) view.findViewById(R.id.member_view);
        final MemberGridAdapter adapter = new MemberGridAdapter(getContext(),R.layout.gridview_my_simple, getData());
        memberView.setAdapter(adapter);
        memberView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        memberView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.changeState(i);
                selectorPosition = i;

            }
        });

        final Button confirm = (Button) view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GridView teamView = (GridView) view.findViewById(R.id.team);
//                Team team = (Team) teamView.getSelectedItem();
//                if(team==null){
//                    Toast.makeText(getContext(),"请选择得分队伍",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                GridView whyView = (GridView) view.findViewById(R.id.why);
//                String why = (String) whyView.getSelectedItem();
//                if(why==null){
//                    Toast.makeText(getContext(),"请选择得分原因",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                GridView methodView = (GridView) view.findViewById(R.id.method_view);
//                String method = (String) teamView.getSelectedItem();
//                if(method==null){
//                    Toast.makeText(getContext(),"请选择得分方式",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                EditText remarkView = (EditText) view.findViewById(R.id.remark);
//                String remark = remarkView.getText().toString();
//
//                Member member = (Member) memberView.getSelectedItem();
//                if(member==null){
//                    Toast.makeText(getContext(),"请选择得分队员",Toast.LENGTH_SHORT).show();
//                    return;
//                }

            }
        });


        return view;
    }

    private List<Member> getData(){
        members = new ArrayList<Member>();
        members.add(new Member("lala",1,Member.CHIEF_SPIKER));
        members.add(new Member("eaea",4,Member.CHIEF_SPIKER));
        members.add(new Member("cdcd",6,Member.CHIEF_SPIKER));
        members.add(new Member("bgbg",3,Member.CHIEF_SPIKER));
        members.add(new Member("lyty",2,Member.CHIEF_SPIKER));
        members.add(new Member("yyyy",15,Member.CHIEF_SPIKER));
        members.add(new Member("tttt",7,Member.CHIEF_SPIKER));
        members.add(new Member("vvva",8,Member.CHIEF_SPIKER));
        return members;
    }
}
