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
import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")
public class VideoDisplay extends Activity
{
    private WebView mWebView;
    private boolean mIsPaused = false;
    Button userBut;
    Button previous;
    Button advance;
    int url;
    ArrayList<String> urls;

    /**
     * Initializes video class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        url = 0;
        urls = new ArrayList<String>();
        //eventually fetch urls from company survey
        //hard coding from poorvi's youtube channel to test
        urls.add("https://youtu.be/R1HW6Comeno");
        urls.add("https://www.youtube.com/watch?v=tA8yqkwNp_M");
        urls.add("https://www.youtube.com/watch?v=jl9-Z3GDfrU");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        userBut = (Button) findViewById(R.id.back_user);
        previous = (Button) findViewById(R.id.previous_video);
        advance = (Button) findViewById(R.id.proceed);

        //Button Methods

        userBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }});
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousScreen(urls);
            }});
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advanceScreen(urls);
            }});


        mWebView = (WebView) findViewById(R.id.webviewer);

        final WebView webview = (WebView) findViewById(R.id.webviewer);
        webview.setWebViewClient(new WebViewClient());

        WebSettings websetting = webview.getSettings();
        websetting.setJavaScriptEnabled(true);
        websetting.setDomStorageEnabled(true);
        mIsPaused = true;
        resumeBrowser();
        webview.loadUrl(urls.get(url));

    }

    /**
     * Plays the next video
     * @param url_list list of youtube urls
     */
    public void advanceScreen(ArrayList<String> url_list)
    {
        resumeBrowser();
        url++;
        if (url<url_list.size()-1)
        mWebView.loadUrl(url_list.get(url));
        //eventually add a message saying "you've reached the end"
        //or don't show the next button on the last video
    }

    /**
     * Plays the previous video
     * @param url_list list of youtube urls
     */
    public void previousScreen(ArrayList<String> url_list)
    {
        resumeBrowser();
        url--;
        if (url>-1)
        mWebView.loadUrl(url_list.get(url));
        //eventually add a message saying "this is the first video"
        //or maybe don't show the previous button on the first video
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
            //resume flash and javascript etc
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