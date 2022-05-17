package com.example.hired;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")
public class VideoDisplay extends AppCompatActivity implements Serializable
{
    private WebView mWebView;
    private boolean mIsPaused;
    private Button userBut;
    private Button previous;
    private Button advance;
    private int url;
    private ArrayList<String> urls;
    private DatabaseReference ref;
    private FirebaseAuth auth;

    /**
     * Initializes video class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        url = 0;
        urls = new ArrayList<String>();

        urls = (ArrayList<String>) getIntent().getSerializableExtra("key");

        if(urls.isEmpty()){
            urls.add("youtube.com");
        }

        Log.d("arraylist",""+ urls);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        userBut = (Button) findViewById(R.id.back_user);
        previous = (Button) findViewById(R.id.previous_video);
        advance = (Button) findViewById(R.id.proceed);

        mWebView = (WebView) findViewById(R.id.webviewer);

        final WebView webview = (WebView) findViewById(R.id.webviewer);
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError (WebView view,int errorCode, String description, String
                    failingUrl){
                Toast.makeText(VideoDisplay.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError er){
                handler.proceed(); // Ignore SSL certificate errors
            }
        });

        WebSettings websetting = webview.getSettings();
        websetting.setJavaScriptEnabled(true);
        websetting.setDomStorageEnabled(true);
        mIsPaused = true;
        resumeBrowser();
        webview.loadUrl(urls.get(url));

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

    }

    /**
     * Plays the next video
     * @param url_list list of youtube urls
     */
    public void advanceScreen(ArrayList<String> url_list)
    {
        resumeBrowser();
        url++;
        if (url < urls.size())
            mWebView.loadUrl(url_list.get(url));
        else{
            Toast.makeText(getApplicationContext(),"This is the last video.",
                    Toast.LENGTH_SHORT).show();
            url = urls.size()-1;
        }

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

        else{
            Toast.makeText(getApplicationContext(),"This is the first video.",
                    Toast.LENGTH_SHORT).show();
            url = 0;
        }
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
        {
            Log.d("error","ERROR MESSAGE: APP IS BREAKING");
        }
    }

}