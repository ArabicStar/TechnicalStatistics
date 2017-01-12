package com.nju.va.technicalstatistics;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamListView extends ListActivity {

    // private List<String> data = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.team_list_line,
                new String[]{"name","img"},
                new int[]{R.id.name,R.id.img});
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "江苏男子排球队");
        map.put("img", R.drawable.team_img);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "上海金色年华男子排球队");
        map.put("img", R.drawable.team_img);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "山东体彩男子排球队");
        map.put("img", R.drawable.team_img);
        list.add(map);

        return list;
    }
}
