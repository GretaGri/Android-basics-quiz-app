package com.example.android.quiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greta on 2018-01-11.
 */

public class VideoActivity extends YouTubeBaseActivity {

    private static final String TAG = "VideoActivity";

    // Declare variables.
    int points;
    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        // Get extras.
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        points = extras.getInt("points");

        // Start YouTube player.
        Log.d(TAG, "onCreate: starting.");
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onclick: Done initializing.");
                // Play different clip depending on points.
                if (points == 1 || points == 2) {
                    youTubePlayer.loadVideo("3jHr5JbTeRY");
                } else if (points == 3 || points == 4) {
                    youTubePlayer.loadVideo("sj43E1KIOf4");
                } else if (points == 5 || points == 6) {
                    youTubePlayer.loadVideo("1t8kAbUg4t4");
                } else if (points == 7 || points == 8) {
                    youTubePlayer.loadVideo("351l62Yx0oI");
                } else {
                    youTubePlayer.loadVideo("NJFj1YCyN6w");
                }
            }

            // Show log message if initialization of player fails.
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onclick: Failed to initialize.");
            }
        };
        // Initialize YouTube player, get ApiKey.
        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);

        // On landscape mode did not apply this code - button is not on landscape mode only player, otherwise app crashes.
        Configuration conf = getResources().getConfiguration();
        boolean isLandscape = (conf.orientation == Configuration.ORIENTATION_LANDSCAPE);
        if (!isLandscape) {
            ImageButton send = (ImageButton) findViewById(R.id.send_letter); // Locate button.
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND); // Create intent to send email with text set after letter button click.
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.letter_subject));
                    intent.putExtra(Intent.EXTRA_TEXT, getText(R.string.letter_text));

                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
            });
        }
    }
}
