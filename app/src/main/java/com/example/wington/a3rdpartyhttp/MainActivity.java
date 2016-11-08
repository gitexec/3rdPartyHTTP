package com.example.wington.a3rdpartyhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String IMG_URL = "http://static5.gamespot.com/uploads/original/1557/15576725/3153546-cwdfniaukaaxebz.jpg";
    private ImageView iBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iBitmap = (ImageView) findViewById(R.id.ivLabel);
        downloadImageAsync(IMG_URL);
    }

    private void downloadImageAsync(String imgUrl) {
        AsyncHttpClient client = new AsyncHttpClient();
        final String[] imageTypes = new String[]{"image/jpeg"};
        client.get(imgUrl, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                //Get the input stram of bytes
                InputStream in = new ByteArrayInputStream(binaryData);
                //Decode the stream
                Bitmap bm = new BitmapFactory().decodeStream(in);
                iBitmap.setImageBitmap(bm);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                Toast.makeText(getBaseContext(),"Image error did not download",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
