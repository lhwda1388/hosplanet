package com.hosplanet.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.hosplanet.R;
import com.hosplanet.main.presenter.HospUrlPresenter;
import com.hosplanet.main.presenter.HospUrlPresenterImpl;

public class HospUrlActivity extends AppCompatActivity implements HospUrlPresenter.View{
    private Button button;
    private HospUrlPresenter hospUrlPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_url);
        button = (Button)findViewById(R.id.btnExit);
        hospUrlPresenter = new HospUrlPresenterImpl(HospUrlActivity.this);
        hospUrlPresenter.setView(this);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hospUrlPresenter.closeIntent();
            }
        });
        Intent intent = getIntent();
        String hospUrl = intent.getStringExtra("com.hosplanet.HOSPURL");
        hospUrlPresenter.showWebView(hospUrl);
    }

    @Override
    public void showWebView(String hospUrl) {
        WebView webView = (WebView)findViewById(R.id.hospWebView);
        Log.i("RESULTURL", hospUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(hospUrl);

    }

    @Override
    public void closeIntent() {
        Log.i("FINISH","FINISH");
        finish();
    }


}
