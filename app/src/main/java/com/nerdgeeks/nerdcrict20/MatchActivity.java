package com.nerdgeeks.nerdcrict20;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.nerdgeeks.nerdcrict20.adapters.TabAdapter;


public class MatchActivity extends AppCompatActivity {

    private TabLayout fixtab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        toolBar.setTitle("Match Scorecard");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager fixView = (ViewPager) findViewById(R.id.sparkViewPager);

        fixtab = (TabLayout) findViewById(R.id.fixTabs);

        //Adding the tabs using addTab() method
        fixtab.addTab(fixtab.newTab().setText("Batting"));
        fixtab.addTab(fixtab.newTab().setText("Bowling"));

        fixView.setAdapter(new TabAdapter(getSupportFragmentManager(), fixtab.getTabCount(), this));

        fixtab.setupWithViewPager(fixView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_leave);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
    }
}
