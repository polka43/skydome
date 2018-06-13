package org.androidtown.tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class Fragment2 extends ListFragment {
    //    TextView getLoginId;
//    id값 어떻게할지 정하기. -진수
    String title;
    String desc;
    String dbid;
    ListView listview ;
    ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        adapter = new ListViewAdapter() ;
        setListAdapter(adapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String sMessage = "";

        String result = SendByHttp(sMessage);
        String[][] parsedData = jsonParserList(result);
//        getLoginId=(TextView)findViewById(R.id.getLoginId);
//        id값 정하기


        Integer parsedData_length = parsedData.length;
        Log.i("앱에 뿌려지는 총 데이터 수  : ", parsedData_length.toString());

        for(int i = 0; i < parsedData.length; i++) {
            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.img2), parsedData[i][0], parsedData[i][1],parsedData[i][2]) ;
        }

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // get item
//                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
//
//                String titleStr = item.getTitle();
//                String descStr = item.getDesc();
//                String dbidStr = item.getDbid();
//                Drawable iconDrawable = item.getIcon() ; // 이거필요없지않음 ? 아이콘 쓸때 쓰자나 근데 왜 뜨지

                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다값
                // 2. AndroidMan11ifest.xml 에 화면을 등록한다
                // 3. Intent 객체를 생성하여 날린다
                //Intent intent = new Intent(getContext(), JoinActivity.class); // 다음넘어갈 화면

                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다

//                intent.putExtra("title", titleStr);
//                intent.putExtra("desc", descStr);
//                intent.putExtra("dbid", dbidStr);
//
//                // 여기 안쓰고 걍 밑에 인텐트로 넘긴거잖아 ㅋㅋㅋㅋ 개무시임 ㅋㅋㅋ
//                //intent.putExtra("artist", listViewItemList.get(position).artist);
//                startActivity(intent);

//                // TODO : use item data.
//            }
//        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private String SendByHttp(String msg) {
        if (msg == null) {
            msg = "";
        }

        String URL = HostAddress.url+"event.jsp";
        DefaultHttpClient client = new DefaultHttpClient();

        try {
            HttpPost post = new HttpPost(URL);
            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();

            return "";
        }
    }

    public String[][] jsonParserList(String pRecvServerPage){
        Log.i("서버에서 받은 전체 내용", pRecvServerPage);

        try{
            System.out.println("1");

            JSONObject json = new JSONObject();
            JSONArray jArr = new JSONArray(pRecvServerPage);

            String[] jsonName = {"title","content","uid"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            Integer total_length= parseredData.length;

            Log.i("JOSN에서 가져온 총 데이터 수  : ",total_length.toString());

            for(int i = 0; i<jArr.length();i++){
                json = jArr.getJSONObject(i);

                for (int j=0;j<jsonName.length; j++){
                    parseredData[i][j] = json.getString(jsonName[j]);
                }
            }

            for(int i=0;i<parseredData.length;i++)
            {

                title = parseredData[i][0];
                desc = parseredData[i][1];
                dbid = parseredData[i][2];
                Log.i("JSON을 분석한 데이터"+i+":",parseredData[i][0]);
                Log.i("JSON을 분석한 데이터"+i+":",parseredData[i][1]);
                Log.i("JSON을 분석한 데이터"+i+":",parseredData[i][2]);

            }

            return parseredData;

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

}
