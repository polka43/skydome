package org.androidtown.tab;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.androidquery.util.AQUtility.getContext;


public class Fragment4 extends android.support.v4.app.Fragment {
    Spinner sp;
    ListViewAdapter adapter;
    TextView textView;

    NoticeListAdapter adapter1;
    ListView noticeListView1;
    ArrayList<Notice> noticeItem1;
    Notice notice1;
    Notice notice2;
    Notice notice3;
    Notice notice4;

    NoticeListAdapter adapter2;
    ListView noticeListView2;
    ArrayList<Notice> noticeItem2;


    NoticeListAdapter adapter4;
    ArrayList<Notice> noticeItem4;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment4, container, false);
        noticeListView1 = (ListView) rootView.findViewById(R.id.noticeListView1);
        noticeListView2 = (ListView) rootView.findViewById(R.id.showListView);
        Log.e("온크리에이트","온크리에이트 뷰");

        SharedPreferences preferences = getContext().getSharedPreferences("id",MODE_PRIVATE);
        String id = preferences.getString("id", "");
        Toast.makeText(getContext() , "id"+id,Toast.LENGTH_LONG);

        Log.e("aaaaa", id+"");
        textView = (TextView) rootView.findViewById(R.id.idConfirm);
        // textView.setText(id);
        noticeItem1 = new ArrayList<Notice>();
        notice1 = new Notice("1.","일시 : 2018년 10월 9일 (화) 오후 7시","2018 현대카드 슈퍼콘서트");
        notice3 = new Notice("2.","일시 : 2018년 10월 30일 (화) 오후 6시","넥센 vs 기아");
        noticeItem1.add(notice1);
        noticeItem1.add(notice3);
        adapter1 = new NoticeListAdapter(getContext(), noticeItem1);

        //adapter = new NoticeListAdapter();

        noticeItem2 = new ArrayList<Notice>();
        notice2 = new Notice("1.","일시 : 2018년 10월 9일 (화) 오후 7시","2018 현대카드 슈퍼콘서트");
        noticeItem2.add(notice2);
        adapter2 = new NoticeListAdapter(getContext(), noticeItem2);
        noticeListView2.setAdapter(adapter2);


        noticeItem4 = new ArrayList<Notice>();
        notice4 = new Notice("1.","일시 : 2018년 12월 26일 (수) 오후 10시","크리스마스 싸이 올나잇!!");
        noticeItem4.add(notice4);
        adapter4 = new NoticeListAdapter(getContext(), noticeItem4);

        sp  = (Spinner) rootView.findViewById(R.id.rankSpinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp.getSelectedItem().equals("10월"))
                {
                    noticeListView1.setAdapter(adapter1);
                }else if(sp.getSelectedItem().equals("12월")){
                    noticeListView1.setAdapter(adapter4);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return rootView;
    }


}
