package com.example.android.quiz;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 2018-01-11.
 */

public class VideoActivity extends YouTubeBaseActivity{

    private static final String TAG = "VideoActivity";

        YouTubePlayerView mYouTubePlayerView;
        YouTubePlayer.OnInitializedListener mOnInitializedListener;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        Log.d (TAG, "onCreate: starting.");
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
@Override
public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.d (TAG, "onclick: Done initializing.");
        List<String> videolist = new ArrayList<>();
        videolist.add("NJFj1YCyN6w");
        videolist.add("351l62Yx0oI");
        videolist.add("1t8kAbUg4t4");
        videolist.add("sj43E1KIOf4");
        videolist.add("TVtyOQ8lP3c");
        youTubePlayer.loadVideos (videolist);
        //youTubePlayer.loadPlaylist ();
        //youTubePlayer.loadVideo("NJFj1YCyN6w");
        }

@Override
public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d (TAG, "onclick: Failed to initialize.");

        }
        };

        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);

        };
        }
