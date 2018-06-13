package org.androidtown.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;

    public Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
      //  Fragment22 fragment22 = (Fragment22) getSupportFragmentManager().findFragmentById(R.id.container1);
        //fragment22.addItem(ContextCompat.getDrawable(this, R.drawable.ic_1), "New Box", "New Account Box Black 36dp") ;



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("돔구장 소개"));
        tabs.addTab(tabs.newTab().setText("행사정보"));
        tabs.addTab(tabs.newTab().setText("시설예약"));
        tabs.addTab(tabs.newTab().setText("My page"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                Fragment selected = null;
                if (position == 0) {
                    Log.e("확인1","확인1");
                    selected = fragment1;
                } else if (position == 1) {
                    Log.e("확인2","확인2");
                    selected = fragment2;
                    //Intent intent=new Intent(getActivity(),ListV.class);
                    // startActivity(intent);
                } else if (position == 2) {
                    Log.e("확인3","확인3");
                    selected = fragment3;
                }else if (position == 3) {
                    Log.e("확인4", "확인4");
                    selected = fragment4;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }
}
