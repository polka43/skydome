package org.androidtown.tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment3 extends ListFragment {
    private Around_ListViewItem data;

    ListView listView = null;
    Around_ListViewAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.around_listview, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview);
        adapter = new Around_ListViewAdapter(rootView);
        adapter.setTelClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Around_ListViewItem item = adapter.getItem((Integer) v.getTag());

                String tel = "tel : " + item.getTel();
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });

        adapter.setMapClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Around_ListViewItem item = adapter.getItem((Integer) v.getTag());
                Uri gmmIntentUri = Uri.parse("geo:" + item.getMapy() + "," + item.getMapx());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }


        });

//        listView.setAdapter(adapter);
        setListAdapter(adapter);
        loadData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void loadData() {
        AQuery aq = new AQuery(getContext());

        HashMap<String, String> params = new HashMap<>();
        params.put("ServiceKey","EJt3X5VcUzwhcTsR7BqCp0dlXcdBHWiEPsWaeJ6lUN6fcIhfxb8X4kwFIjKmP6APcVJBPILeStpY%2B7hTUhTK8w%3D%3D");
        params.put("numOfRows","20");
        params.put("pageNo","1");
        params.put("MobileOS","ETC");
        params.put("MobileApp","AppTest");
        params.put("arrange","A");
        params.put("listYN","Y");
        params.put("areaCode","1");
        params.put("eventStartDate","20170901");
        params.put("_type","json");

        String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival";
        url = addParams(url, params);

        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            public void callback(String url, JSONObject resutl, AjaxStatus status) {
                if (resutl != null) {
                    Log.i("test", resutl.toString());

                    try {
                        JSONArray jar = resutl.optJSONObject("response").optJSONObject("body").optJSONObject("items").optJSONArray("item");

                        ArrayList<Around_ListViewItem> arItem = new ArrayList<>();
                        for (int i = 0; i < jar.length(); i++) {
                            JSONObject jobj = jar.optJSONObject(i);

                            Around_ListViewItem item = new Around_ListViewItem();
                            item.setTitle(jobj.optString("title"));
                            item.setAddress(jobj.optString("addr1"));
                            item.setFirstimage(jobj.optString("firstimage"));
                            item.setMapx(jobj.optDouble("mapx"));
                            item.setMapy(jobj.optDouble("mapy"));
                            item.setTel(jobj.optString("tel"));

                            arItem.add(item);

                        }

                        if (arItem.size() > 0) {
                            adapter.getArItem().addAll(arItem);
                            adapter.notifyDataSetChanged();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "제이슨오류", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "잘못된 요청", Toast.LENGTH_SHORT).show();

                }
            }
        }.timeout(20000));
    }


    private String addParams(String url, HashMap<String, String> mapParam){
        StringBuilder stringBuilder = new StringBuilder(url+"?");

        if(mapParam != null){
            for(String key : mapParam.keySet()){
                stringBuilder.append(key+"=");
                stringBuilder.append(mapParam.get(key)+"&");
            }
        }
        return  stringBuilder.toString();
    }
}
