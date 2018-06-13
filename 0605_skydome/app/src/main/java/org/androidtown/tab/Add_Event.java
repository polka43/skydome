package org.androidtown.tab;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class Add_Event extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CODE = 0x0100;


    ImageView ivPhoto;
    EditText etTitle;
    EditText etContent;

    File seltedPhoto;

//    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ivPhoto = (ImageView)findViewById(R.id.ivPhoto);
        etTitle = (EditText)findViewById(R.id.etTitle);
        etContent = (EditText)findViewById(R.id.etContent);

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGallery();
            }
        });

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
                    Toast.makeText(Add_Event.this, "모두 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    AQuery aQuery = new AQuery(Add_Event.this);
                    String url = HostAddress.url + "add_event.jsp";

                    Map<String, Object> params = new LinkedHashMap<>();

                    params.put("title", title);
                    params.put("content", content);
                    if(seltedPhoto != null){
                        params.put("image", seltedPhoto);
                    }

                    aQuery.ajax(url, params, String.class, new AjaxCallback<String>() {
                        @Override
                        public void callback(String url, String result, AjaxStatus status) {
                            try {
                                JSONObject jReponse = new JSONObject(result);
                                if (jReponse != null && !jReponse.isNull("result")) {
                                    Toast.makeText(Add_Event.this, "등록이 완료 되었습니다", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } catch (Exception e) {
                                Toast.makeText(Add_Event.this, "등록 하는데 오류가 발생 하였습니다", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_IMAGE_CODE) {
                Uri selectedImage = data.getData();

                File tmpCacheFile = new File(getCacheDir(), UUID.randomUUID() + ".jpg");
                if(fileCopy(selectedImage, tmpCacheFile)){
                    seltedPhoto = tmpCacheFile;

                    Bitmap bitmap = BitmapFactory.decodeFile(seltedPhoto.getAbsolutePath());
                    ivPhoto.setImageBitmap(bitmap);
                }
            }
        }

    }

    protected boolean fileCopy(Uri in, File out) {
        try {
            File inFile = new File(in.getPath());
            InputStream is = new FileInputStream(inFile);
            // InputStream is =
            // context.getContentResolver().openInputStream(in);
            FileOutputStream outputStream = new FileOutputStream(out);

            BufferedInputStream bin = new BufferedInputStream(is);
            BufferedOutputStream bout = new BufferedOutputStream(outputStream);

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = bin.read(buffer, 0, 1024)) != -1) {
                bout.write(buffer, 0, bytesRead);
            }

            bout.close();
            bin.close();

            outputStream.close();
            is.close();
        } catch (IOException e) {
            InputStream is;
            try {
                is = getContentResolver().openInputStream(in);

                FileOutputStream outputStream = new FileOutputStream(out);

                BufferedInputStream bin = new BufferedInputStream(is);
                BufferedOutputStream bout = new BufferedOutputStream(outputStream);

                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = bin.read(buffer, 0, 1024)) != -1) {
                    bout.write(buffer, 0, bytesRead);
                }

                bout.close();
                bin.close();

                outputStream.close();
                is.close();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return false;
            }

        }

        return true;
    }

    public void startGallery() {
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_IMAGE_CODE);
            }
            else {
                Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
                cameraIntent.setType("image/*");
                if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CODE);
                }
            }
        }
        else {
            Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
            cameraIntent.setType("image/*");
            if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CODE);
            }
        }
    }
}
