package com.example.hired;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

@SuppressLint("SetJavaScriptEnabled")
public class MultimediaPlayer extends AppCompatActivity
{
    private WebView mWebView;
    private boolean mIsPaused = false;

    @Override
    /**
     * Initializes video class
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        //Hard coded right now but eventually should link with url company enters
       // String media_url = "www.massacademy.org";

        /*mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new WebChromeClient());

        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);

        mIsPaused = true;
        resumeBrowser();
        mWebView.loadUrl(media_url);*/

        final WebView webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());

        WebSettings websetting = webview.getSettings();
        websetting.setJavaScriptEnabled(true);
        webview.loadUrl("https://youtube.com/watch?v=R1HW6Comeno");
    }

    @Override
    /**
     * Pauses the screen
     */
    protected void onPause()
    {
        pauseBrowser();
        super.onPause();
    }

    @Override
    /**
     * Resumes the screen
     */
    protected void onResume()
    {
        resumeBrowser();
        super.onResume();
    }

    /**
     * Hidden method to pause video
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
     * Hidden method to resume video
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