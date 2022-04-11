package com.example.hired;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.lang.reflect.Method;

@SuppressLint("SetJavaScriptEnabled")
public class MultimediaPlayer extends Activity
{
    private WebView mWebView;
    private boolean mIsPaused = false;
    Button userBut;
    Button previous;
    Button advance;


    /**
     * Initializes video class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        userBut = (Button) findViewById(R.id.back_user);
        previous = (Button) findViewById(R.id.prevButton);
        previous = (Button) findViewById(R.id.proceedButton);

        userBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }});


        mWebView = (WebView) findViewById(R.id.webviewer);

        final WebView webview = (WebView) findViewById(R.id.webviewer);
        webview.setWebViewClient(new WebViewClient());

        WebSettings websetting = webview.getSettings();
        websetting.setJavaScriptEnabled(true);
        websetting.setDomStorageEnabled(true);
        mIsPaused = true;
        resumeBrowser();
        webview.loadUrl("https://youtube.com/watch?v=R1HW6Comeno");
    }

    /**
     * Pauses the screen
     */
    @Override
    protected void onPause()
    {
        pauseBrowser();
        super.onPause();
    }


    /**
     * Resumes the screen
     */
    @Override
    protected void onResume()
    {
        resumeBrowser();
        super.onResume();
    }

    /**
     * Hidden method to pause webpage
     */
    private void pauseBrowser()
    {
        if (!mIsPaused)
        {
            // pause flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onPause");
            mWebView.pauseTimers();
            mIsPaused = true;
        }
    }

    /**
     * Hidden method to resume webpage
     */
    private void resumeBrowser()
    {
        if (mIsPaused)
        {
            // resume flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onResume");
            mWebView.resumeTimers();
            mIsPaused = false;
        }
    }

    /**
     * Calls hidden method
     * @param wv webview
     * @param name name of method call
     */
    private void callHiddenWebViewMethod(final WebView wv, final String name)
    {
        try
        {
            final Method method = WebView.class.getMethod(name);
            method.invoke(mWebView);
        } catch (final Exception e)
        {}
    }
}