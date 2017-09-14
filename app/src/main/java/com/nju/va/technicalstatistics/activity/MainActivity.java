package com.nju.va.technicalstatistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nju.va.technicalstatistics.R;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //team data button
        final Button teamBtn = (Button) findViewById( R.id.team_data_button );
        teamBtn.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                startActivity( new Intent( "android.intent.action.TEAMLIST" ) );
            }
        } );

        final Button newCompBtn = (Button) findViewById( R.id.new_competition_button );
        newCompBtn.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                startActivity( new Intent( "android.intent.action.CHOOSETEAM" ) );
            }
        } );

        final Button preCompBtn = (Button) findViewById( R.id.previous_competition_button );
        preCompBtn.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                startActivity( new Intent( MainActivity.this, MatchActivity.class ) );
            }
        } );
    }
}
