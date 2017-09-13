package com.nju.va.technicalstatistics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.info.Team;

import java.security.acl.LastOwnerException;
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

    public void update(List<Team> teams){

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team team = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) view.findViewById(R.id.layout);
            viewHolder.image = (ImageView) view.findViewById(R.id.team_img);
            viewHolder.name = (TextView) view.findViewById(R.id.team_name);
            view.setTag(viewHolder);

        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.image.setImageResource(team.getImgId());
        viewHolder.name.setText(team.getName());

        return view;
    }

    private final class ViewHolder{
        LinearLayout layout;
        ImageView image;
        TextView name;
    }


}
