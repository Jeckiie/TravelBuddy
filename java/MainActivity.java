package com.travelbuddy.jerko.travelbuddy;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SectionsFragment.sectionsFragmentListener, ListFragment.ListFragmentListener{



    TabLayout tabLayout;
    private PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Kategorije"));
        tabLayout.addTab(tabLayout.newTab().setText("Stavke"));
        tabLayout.addTab(tabLayout.newTab().setText("Moja lista"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        tabLayout.getTabAt(2).select();


    }

    @Override
    public void sendIndex(int index) {
        ((ListFragment) adapter.getItem(1)).getIndex(index);
    }

    @Override
    public void sendItemName(String input) {
        ((MainFragment) adapter.getItem(2)).addData(input);
    }
}
