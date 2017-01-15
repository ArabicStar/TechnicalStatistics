package com.nju.va.technicalstatistics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Team;

import java.util.List;

/**
 * Created by jqwu on 2017/1/15.
 */
public class TeamAdapter extends ArrayAdapter<Team>{

    private int resourceId;

    public TeamAdapter(Context context, int textViewResourceId, List<Team> teams){
        super(context,textViewResourceId,teams);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team team = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        ImageView teamImage = (ImageView) view.findViewById(R.id.team_img);
        TextView teamName = (TextView) view.findViewById(R.id.team_name);
        teamImage.setImageResource(team.getImgId());
        teamName.setText(team.getName());

        return view;
    }


}
