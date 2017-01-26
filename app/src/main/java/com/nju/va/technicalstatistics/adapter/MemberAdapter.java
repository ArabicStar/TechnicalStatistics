package com.nju.va.technicalstatistics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.List;

/**
 * Created by jqwu on 2017/1/17.
 */
public class MemberAdapter extends ArrayAdapter<Member> {

    private int resourceId;

    public MemberAdapter(Context context, int textViewResourceId, List<Member> members){
        super(context,textViewResourceId,members);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Member member = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView memberName = (TextView) view.findViewById(R.id.member_name);
        TextView memberNum = (TextView) view.findViewById(R.id.member_number);
        TextView memberPos = (TextView) view.findViewById(R.id.member_position);

        memberName.setText(member.getName());
        memberNum.setText(Integer.toString(member.getNumber())+"号");
        memberPos.setText(member.getPositionString());

        return view;
    }
}
