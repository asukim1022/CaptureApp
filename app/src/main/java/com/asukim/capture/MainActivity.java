package com.asukim.capture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Bitmap mbitmap;
    Button captureScreenShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("ScreenShot : ");

        captureScreenShot = (Button) findViewById(R.id.captureScreenShot);
        imageView = (ImageView) findViewById(R.id.imageView);

        captureScreenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbitmap = getBitmapOFRootView(captureScreenShot);
                imageView.setImageBitmap(mbitmap);
                Toast.makeText(getApplicationContext(), "capture", Toast.LENGTH_SHORT).show();
                createImage(mbitmap);
            }
        });
    }


    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();

        //뷰가 업데이트 될 때마다 그 때의 뷰 이미지를 Drawing cache에 저장할지 여부를 결정
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }


    public void createImage(Bitmap bmp) {

        //바이트 배열을 차례대로 출력을 위한 클래스
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        //compress() 의 두번째 파라메터로 40 을 넘기고있는데 이건 40%로 압축
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + "/capture.jpg");
        try {
            file.createNewFile();

            //filepath로 지정한 파일에 대한 OutputStream을 생성
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

