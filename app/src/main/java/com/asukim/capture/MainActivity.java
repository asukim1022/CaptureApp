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
        textView.setText("Your ScreenShot Image:");

        captureScreenShot = (Button) findViewById(R.id.capture_screen_shot);
        imageView = (ImageView) findViewById(R.id.imageView);

        Button capture_screen_shot = (Button) findViewById(R.id.capture_screen_shot);
        capture_screen_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenShot(v);
            }
        });
    }

    public void screenShot(View view) {
        mbitmap = getBitmapOFRootView(captureScreenShot);
        imageView.setImageBitmap(mbitmap);
        Toast.makeText(getApplicationContext(), "capture", Toast.LENGTH_SHORT).show();
        createImage(mbitmap);
    }

    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }

    public void createImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + "/capturedscreenandroid.jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

