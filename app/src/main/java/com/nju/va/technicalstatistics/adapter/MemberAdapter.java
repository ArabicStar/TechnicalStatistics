package com.nju.va.technicalstatistics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Member;

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
        View view;
        ViewHolder viewHolder;

        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) view.findViewById(R.id.layout) ;
            viewHolder.name = (TextView) view.findViewById(R.id.member_name);
            viewHolder.number = (TextView) view.findViewById(R.id.member_number);
            viewHolder.position = (TextView) view.findViewById(R.id.member_position);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if(member!=null){
            viewHolder.name.setText(member.getName());
            viewHolder.number.setText(Integer.toString(member.getNumber())+"号");
            viewHolder.position.setText(member.getPositionString());
        }


        return view;
    }

    private final class ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView number;
        TextView position;
    }
}
