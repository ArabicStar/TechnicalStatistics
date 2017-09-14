package com.nju.va.technicalstatistics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Team;

import java.util.List;

/**
 * Created by jqwu on 2017/2/13.
 */

public class TeamGridAdapter extends ArrayAdapter<Team> {
    private int resourceId;
    private int selectorPosition = GridView.INVALID_POSITION;

    public TeamGridAdapter(Context context, int textViewResourceId, List<Team> teams){
        super(context,textViewResourceId,teams);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Team team = getItem(position);
        View view;
        ViewHolder viewHolder;

        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.textView);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.text.setText(team.getName());

        if (selectorPosition == position) {
            viewHolder.text.setBackgroundResource(R.color.darkskyblue);
        } else {
            //其他的恢复原来的状态
            viewHolder.text.setBackgroundResource(R.color.transparent);
        }

        return view;
    }

    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();

    }

    private final class ViewHolder{
        TextView text;
    }

}
