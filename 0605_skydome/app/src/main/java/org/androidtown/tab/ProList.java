package org.androidtown.tab;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jonghun on 2017-11-08.
 */

public class ProList extends AppCompatActivity {
    Button button33;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        button33 = (Button) findViewById(R.id.paty);

        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProList.this, "신청 되셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        setTitle("프로젝트");
        TextView tv_title = (TextView) findViewById(R.id.title);
        TextView tv_memo = (TextView) findViewById(R.id.memo);
        ImageView tv_img = (ImageView) findViewById(R.id.img);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        String memo = it.getStringExtra("memo");
        //String img = it.getStringExtra("img");
        Resources res = getResources();

        tv_title.setText(title);
        tv_memo.setText(memo);

/*
        int id_img = res.getIdentifier("imageView1", "String", getPackageName());
        String img = res.getString(id_img);
        int id_imgg = res.getIdentifier(img, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(id_imgg);
        tv_img.setBackgroundResource(id_imgg);
*/

    }
    public void closePicture(View v) {
        finish();
    }

}
