package org.androidtown.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends Fragment {
    ArrayList<Notice> noticeItem;
    Notice notice;
    Notice notice1;
    Notice notice2;
    Notice notice3;
    Notice notice4;
        NoticeListAdapter adapter;
        ListView noticeListView;

    Button button11;
    Button button22;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        noticeListView = (ListView) rootView.findViewById(R.id.noticeListView);
        button11 = (Button) rootView.findViewById(R.id.button11);
        button22 = (Button) rootView.findViewById(R.id.button22);


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "공공데이터 사용예정!!!.", Toast.LENGTH_SHORT).show();
            }
        });
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "시설정보 확인", Toast.LENGTH_SHORT).show();
                Intent newActivity = new Intent(getContext(), Pop_information.class);
                startActivity(newActivity);
            }
        });

        noticeItem = new ArrayList<Notice>();
        notice = new Notice("A)","고척돔어때 어플 혹은 서울시설공단으로 직접 연락바랍니다.","Q) 시설 예약은 어떻게 하나요?");
        notice1 = new Notice("A)","행사가 있는 날을 제외하고 365일 개방합니다.","Q) 고척돔은 언제 개방하나요?");
        notice2 = new Notice("A)","492대(장애인19, 여성100, 경차27, 응급차2, 버스4, 일반340)","Q) 주차시설은 어떻게 되나요?");
        notice3 = new Notice("A)","누구나 사용가능합니다.","Q) 헬스장 및 부대 시설이용은 고척동 주민만 가능한가요?");
        notice4 = new Notice("A)","최대 2만명까지 수용가능합니다.","Q) 고척돔 수용인원은 어떻게 되나요?");
        noticeItem.add(notice);
        noticeItem.add(notice1);
        noticeItem.add(notice2);
        noticeItem.add(notice3);
        noticeItem.add(notice4);


        adapter = new NoticeListAdapter(getContext(), noticeItem);
        //adapter = new NoticeListAdapter();

        noticeListView.setAdapter(adapter);

        //adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_1), "Box", "111");

        return rootView;
    }




    public void onListItemClick (ListView l, View v, int position, long id) { // get TextView's Text.
        String strText = (String) l.getItemAtPosition(position); // TODO }
    }
}
