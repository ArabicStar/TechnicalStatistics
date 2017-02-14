package com.nju.va.technicalstatistics.activity;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.nju.va.technicalstatistics.R;
import com.nju.va.technicalstatistics.adapter.FragAdapter;
import com.nju.va.technicalstatistics.component.CanotSlidingViewpager;
import com.nju.va.technicalstatistics.fragment.FragInteractor;
import com.nju.va.technicalstatistics.fragment.HowFragment;
import com.nju.va.technicalstatistics.fragment.TeamFragment;
import com.nju.va.technicalstatistics.fragment.WhoFragment;
import com.nju.va.technicalstatistics.fragment.WhyFragment;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity implements FragInteractor{

    private MenuDrawer mDrawer;
    private TeamFragment tFrag;
    private WhyFragment wFrag;
    private HowFragment hFrag;
    private WhoFragment whoFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawer = MenuDrawer.attach(this, Position.BOTTOM);
        mDrawer.setContentView(R.layout.activity_match);
        mDrawer.setMenuView(R.layout.menu_score_detail);

        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>(4);
        tFrag = new TeamFragment();
        fragments.add(tFrag);
        wFrag = new WhyFragment();
        fragments.add(wFrag);
        hFrag = new HowFragment();
        fragments.add(hFrag);
        whoFrag = new WhoFragment();
        fragments.add(whoFrag);
        final FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        final CanotSlidingViewpager vp = (CanotSlidingViewpager)findViewById(R.id.viewpager);
        vp.setOffscreenPageLimit(3);
        vp.setScrollble(true);
        vp.setAdapter(adapter);

        Button pointDetailBtn = (Button)findViewById(R.id.point_detail) ;
        pointDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu();
//                vp.setCurrentItem(0, true);
                vp.setAdapter(adapter);
            }
        });


    }

    public void test(){
        if(tFrag.getSelectedItem()==null) {
            Toast.makeText(getApplicationContext(), "请选择得分队伍", Toast.LENGTH_SHORT).show();
            return;
        }

        if(wFrag.getSelectedItem()==null) {
            Toast.makeText(getApplicationContext(), "请选择得分原因", Toast.LENGTH_SHORT).show();
            return;
        }

        if(hFrag.getSelectedItem()==null) {
            Toast.makeText(getApplicationContext(), "请选择得分方式", Toast.LENGTH_SHORT).show();
            return;
        }

//        if(whoFrag.getSelectedItems()==null) {
//            Toast.makeText(getApplicationContext(), "请选择得分队员", Toast.LENGTH_SHORT);
//            return;
//        }

        mDrawer.closeMenu();


    }

    public void process(Bundle bundle){
        

    }
}
