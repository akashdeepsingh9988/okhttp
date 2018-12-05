package com.example.robin.okhttpexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView dataTx;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataTx = findViewById(R.id.data);
    }

    public void getData(View v)
    {
        String url = "https://dog.ceo/api/breeds/image/random";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   final String data = response.body().string();

                if(response.isSuccessful())
                    {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    JSONObject obj = new JSONObject(data);
                                    String status = obj.getString("status");
                                    String msg = obj.getString("message");
                                    dataTx.setText(status+"\n"+msg);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }

                    else
                    {

                    }

            }
        });

     //   dataTx.setText("Hello Worrrrllldldldldldl");
    }
}
