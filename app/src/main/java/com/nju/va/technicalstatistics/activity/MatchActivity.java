package com.nju.va.technicalstatistics.activity;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawer = MenuDrawer.attach(this, Position.BOTTOM);
        mDrawer.setContentView(R.layout.activity_match);
        mDrawer.setMenuView(R.layout.menu_score_detail);

        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new TeamFragment());
        fragments.add(new WhyFragment());
        fragments.add(new HowFragment());
        fragments.add(new WhoFragment());
        final FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        final CanotSlidingViewpager vp = (CanotSlidingViewpager)findViewById(R.id.viewpager);
//        vp.setOffscreenPageLimit(3);
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

    public void process(Bundle bundle){
        

    }
}
