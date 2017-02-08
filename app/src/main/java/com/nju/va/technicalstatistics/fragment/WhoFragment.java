package com.nju.va.technicalstatistics.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/2/8.
 */

public class WhoFragment extends Fragment {
    List<Member> members;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.page4_who, container, false);
        initMem();

        final GridView memberView = (GridView) view.findViewById(R.id.member_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, getData());
        memberView.setAdapter(adapter);
        memberView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        memberView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(memberView.isItemChecked(i))
                    view.setBackgroundResource(R.color.darkskyblue);
                else
                    view.setBackgroundResource(R.color.transparent);

            }
        });


        return view;
    }

    private void initMem(){
        members = new ArrayList<Member>();
        members.add(new Member("lala",1,Member.CHIEF_SPIKER));
        members.add(new Member("eaea",4,Member.CHIEF_SPIKER));
        members.add(new Member("cdcd",6,Member.CHIEF_SPIKER));
        members.add(new Member("bgbg",3,Member.CHIEF_SPIKER));
        members.add(new Member("lyty",2,Member.CHIEF_SPIKER));
        members.add(new Member("yyyy",15,Member.CHIEF_SPIKER));
        members.add(new Member("tttt",7,Member.CHIEF_SPIKER));
        members.add(new Member("vvva",8,Member.CHIEF_SPIKER));
    }

    private List<String> getData(){
        List<String> numbers = new ArrayList<String>();
        for(Member m:members){
            numbers.add(Integer.toString(m.getNumber())+"号");
        }

        return numbers;
    }
}
