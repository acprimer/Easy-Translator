package com.translator.acprimer.easytranslator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fssa.gd.fefqw.AZS;
import com.google.gson.Gson;

import java.util.Random;

import ww.ee.ff.Wnn;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private ImageView ivSearch;
    private TextView tvSource;
    private TextView tvTrans;

    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = etInput.getText().toString();
                if (TextUtils.isEmpty(query)) {
                    Toast.makeText(MainActivity.this, "Input the word.", Toast.LENGTH_SHORT).show();
                    return;
                }
                queryResultFromWeb();
            }
        });

//        AZS mjokeM = AZS.getInstance(getApplicationContext(), Constants.AIMENG_AD_APP_ID);
//        mjokeM.s();	   //正常调用插屏展示,默认有浮窗广告,外弹广告
    }

    private void initView() {
        etInput = (EditText) findViewById(R.id.query);
        ivSearch = (ImageView) findViewById(R.id.search);
        tvSource = (TextView) findViewById(R.id.source);
        tvTrans = (TextView) findViewById(R.id.trans);
    }

    private void queryResultFromWeb() {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String salt = String.valueOf(new Random().nextLong());
        String sign = Utils.md5(Constants.BAIDU_APP_ID + query + salt + Constants.BAIDU_KEY);
        String url = String.format(Constants.BAIDU_TRANSLATOR_URL,
                query, "en", "zh", Constants.BAIDU_APP_ID, salt, sign);
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TransResult result = new Gson().fromJson(response, TransResult.class);
                        tvSource.setText(result.getData().get(0).getSrc());
                        tvTrans.setText(result.getData().get(0).getDst());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Input the word.", Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(request);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            Wnn pm = Wnn.getInstance(getApplicationContext(), Constants.DYD_AD_APP_ID);
            pm.e(this);
            AZS  mjokeM  =  AZS.getInstance(getApplicationContext(), Constants.AIMENG_AD_APP_ID);
            mjokeM.e(MainActivity.this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
