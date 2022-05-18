package com.example.hired;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")
public class VideoDisplay extends YouTubeBaseActivity implements Serializable {
    private Button userBut;
    private Button previous;
    private Button advance;
    private int url;
    private ArrayList<String> urls;
    private String api_key;
    private YouTubePlayerView ytPlayer;
    private YouTubePlayer playboy;

    /**
     * Initializes video class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api_key = "AIzaSyBqIHSck4tus3mG1gkc_OtHGaYp-piHJwM";
        url = 0;
        urls = new ArrayList<String>();

        urls = (ArrayList<String>) getIntent().getSerializableExtra("key");


        Log.d("arraylist", "" + urls);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        userBut = (Button) findViewById(R.id.back_user);
        previous = (Button) findViewById(R.id.previous_video);
        advance = (Button) findViewById(R.id.proceed);

        ytPlayer = findViewById(R.id.youtube);


        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            // Implement two methods by clicking on red
            // error bulb inside onInitializationSuccess
            // method add the video link or the playlist
            // link that you want to play In here we
            // also handle the play and pause
            // functionality
            @Override
            public void onInitializationSuccess(
                    YouTubePlayer.Provider provider,
                    YouTubePlayer youTubePlayer, boolean b) {
                playboy = youTubePlayer;
                youTubePlayer.loadVideo(urls.get(url));
                youTubePlayer.play();
            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult
                                                        youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
            }
        };

        ytPlayer.initialize(api_key,listener);


        //Button Methods
        userBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousScreen(urls);
            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advanceScreen(urls);
            }
        });

    }

    /**
     * Plays the next video
     *
     * @param url_list list of youtube urls
     */
    public void advanceScreen(ArrayList<String> url_list) {
        url++;
        if (url < urls.size()) {
            playboy.loadVideo(urls.get(url));
            playboy.play();
        } else {
            Toast.makeText(getApplicationContext(), "This is the last video.",
                    Toast.LENGTH_SHORT).show();
            url = urls.size() - 1;
        }

    }

    /**
     * Plays the previous video
     *
     * @param url_list list of youtube urls
     */
    public void previousScreen(ArrayList<String> url_list) {
        url--;
        if (url > -1) {
            playboy.loadVideo(urls.get(url));
            playboy.play();
        } else {
            Toast.makeText(getApplicationContext(), "This is the first video.",
                    Toast.LENGTH_SHORT).show();
            url = 0;
        }
    }
}