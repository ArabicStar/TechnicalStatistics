package com.nju.va.technicalstatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TeamListView extends AppCompatActivity {
    ListView listView;
    String[] names = {"江苏男子排球队", "上海金色年华男子排球队", "山东体彩男子排球队"};
    //    String [] texts={"文本内容A","文本内容B","文本内容C"};
    int[] resIds = {R.drawable.team_img, R.drawable.team_img, R.drawable.team_img};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_team_list);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle("BaseAdapter for ListView");
        listView = (ListView) this.findViewById(R.id.team_list);
        listView.setAdapter(new TeamListAdapter(names, resIds));

        Button addBtn = (Button)findViewById(R.id.add_team_button);
        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent("android.intent.action.ADDTEAM"));
            }
        });
    }

    public class TeamListAdapter extends BaseAdapter {
        View[] itemViews;

        public TeamListAdapter(String[] names,
                               int[] itemImageRes) {
            itemViews = new View[names.length];

            for (int i = 0; i < itemViews.length; ++i) {
                itemViews[i] = makeItemView(names[i],
                        itemImageRes[i]);
            }
        }

        public int getCount() {
            return itemViews.length;
        }

        public View getItem(int position) {
            return itemViews[position];
        }

        public long getItemId(int position) {
            return position;
        }

        private View makeItemView(String strName, int resId) {
            LayoutInflater inflater = (LayoutInflater) TeamListView.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.dialog_new_member.item关联
            View itemView = inflater.inflate(R.layout.team_list_line, null);

            // 通过findViewById()方法实例R.dialog_new_member.item内各组件
            TextView name = (TextView) itemView.findViewById(R.id.name);
            name.setText(strName);
            ImageView image = (ImageView) itemView.findViewById(R.id.img);
            image.setImageResource(resId);

            return itemView;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                return itemViews[position];
            return convertView;
        }
    }
}
