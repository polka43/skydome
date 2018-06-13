package org.androidtown.tab;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jonghun on 2017-11-08.
 */

public class JoinActivity extends AppCompatActivity {

    int version = 1;
    // 왜있는거임 버전??? 18.06.06 진수 작성

//종훈이꺼    DatabaseOpenHelper helper;
//종훈이꺼    SQLiteDatabase database;

    EditText idEditText,pwEditText,emailEditText,phoneEditText;

    RadioButton man_Rbt,wman_Rbt;

    Button btnJoin;

    User task;
    String gender= "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idEditText = (EditText) findViewById(R.id.idText);
        pwEditText = (EditText) findViewById(R.id.passwordText);
        emailEditText = (EditText) findViewById(R.id.emailText);
        phoneEditText = (EditText) findViewById(R.id.phoneText);
        man_Rbt = (RadioButton) findViewById(R.id.genderman);
        wman_Rbt = (RadioButton) findViewById(R.id.genderWoMan);
        btnJoin = (Button) findViewById(R.id.registerButton);



//종훈이꺼        helper = new DatabaseOpenHelper(JoinActivity.this, DatabaseOpenHelper.tableName, null, version);
//종훈이꺼        database = helper.getWritableDatabase();

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (man_Rbt.isChecked()) {
                    gender = "M";
                }else if (wman_Rbt.isChecked()){
                    gender = "W";
                }


                if(idEditText.getText().toString().length() == 0 || pwEditText.getText().toString().length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(JoinActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                task = new User();
                task.execute();

                Toast toast = Toast.makeText(JoinActivity.this, "가입이 완료되었습니다. 로그인을 해주세요.", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class User extends AsyncTask<Void,String,Void> {
        @Override
        protected Void doInBackground(Void... param) {
            // TODO Auto-generated method stub

            try{
                HttpClient client = new DefaultHttpClient();

                String postURL = HostAddress.url+"join.jsp";

                HttpPost post = new HttpPost(postURL);
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                //파라미터
                params.add(new BasicNameValuePair("userId", idEditText.getText().toString()));
                params.add(new BasicNameValuePair("userPw", pwEditText.getText().toString()));
                params.add(new BasicNameValuePair("userEmail", emailEditText.getText().toString()));
                params.add(new BasicNameValuePair("userPhone", phoneEditText.getText().toString()));
                params.add(new BasicNameValuePair("userSex", gender));


                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                post.setEntity(ent);//ent UTF-8방식으로 보냄

                HttpResponse responsePOST = client.execute(post);
                HttpEntity resEntity = responsePOST.getEntity();
                if (resEntity != null)
                {
                    Log.i("RESPONSE", EntityUtils.toString(resEntity));
                }

            }catch(IOException e){
                e.printStackTrace();
            }
            return null;

        }
    }
}
