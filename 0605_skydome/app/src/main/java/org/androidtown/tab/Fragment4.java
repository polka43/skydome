package org.androidtown.tab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class Fragment4 extends Fragment {
    Spinner sp;
    ListViewAdapter adapter;
    TextView textView;

    NoticeListAdapter adapter1;
    ListView noticeListView1;
    ArrayList<Notice> noticeItem1;
    Notice notice1;
    Notice notice3;

    NoticeListAdapter adapter2;
    ListView noticeListView2;
    ArrayList<Notice> noticeItem2;
    Notice notice2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
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
        notice1 = new Notice("1."," 김밥싸들고 행주산성 소풍가기~","남한산성 아니 행주산성!!");
        notice3 = new Notice("2."," 파주 헤이리마을, 아울렛, 쁘띠프랑스 소풍가기~","파주 어디까지 가봤니?");
        noticeItem1.add(notice1);
        noticeItem1.add(notice3);
        adapter1 = new NoticeListAdapter(getContext(), noticeItem1);
        //adapter = new NoticeListAdapter();

        noticeItem2 = new ArrayList<Notice>();
        notice2 = new Notice("1."," 현지 한국인들과 여행온 외국인들이 함께 여행하며 친목을 쌓으며 친구되기 프로젝트!!","한국은 처음이지?");
        noticeItem2.add(notice2);
        adapter2 = new NoticeListAdapter(getContext(), noticeItem2);
        noticeListView2.setAdapter(adapter2);

        sp  = (Spinner) rootView.findViewById(R.id.rankSpinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp.getSelectedItem().equals("경기"))
                {
                    noticeListView1.setAdapter(adapter1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                }
        });


        return rootView;
    }


}
