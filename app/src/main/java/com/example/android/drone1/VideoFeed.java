package com.example.android.drone1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class VideoFeed extends AppCompatActivity {


        EditText addrField;
        Button btnConnect;
        //VideoView streamView;
        //MediaController mediaController;
        private VideoView myVideoView;
        private int position = 0;
    // private ProgressBar progress;
        private MediaController mediaControls;
        public static String s;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video_feed);
            if (mediaControls == null) {
                mediaControls = new MediaController(VideoFeed.this);
            }

            // Find your VideoView in your video_main.xml layout
            myVideoView = (VideoView) findViewById(R.id.streamview);
            addrField = (EditText) findViewById(R.id.addr);
            btnConnect = (Button) findViewById(R.id.connect);
            // streamView = (VideoView)findViewById(R.id.streamview);

            btnConnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s = addrField.getEditableText().toString();
                    //playStream(s);
                    try {
                        //Uri.Builder builder = new Uri.Builder();
                       // builder.scheme("rtsp")
                                //.encodedAuthority(s);
                        //
                        // .appendPath("turtles")
                        //     .appendPath("types")
                        //   .appendQueryParameter("type", "1")
                        // .appendQueryParameter("sort", "relevance")
                        //.fragment("section-name");
                        myVideoView.setMediaController(mediaControls);
                       // String myurl = builder.build().toString();
                        //Toast.makeText(getApplicationContext(),myurl,Toast.LENGTH_LONG).show();
                        myVideoView.setVideoURI(Uri.parse("rtsp://192.168.43.1:8081/user=&password=&channel=1&stream=0.sdp?"));

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    myVideoView.requestFocus();
                    myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        // Close the progress bar and play the video
                        public void onPrepared(MediaPlayer mp) {
                            // progressDialog.dismiss();
                            myVideoView.seekTo(position);
                            if (position == 0) {
                                myVideoView.start();
                            } else {
                                myVideoView.pause();
                            }
                        }
                    });


                }
            });

            /*try {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("rtsp")
                        .authority(s);
                       //
                // .appendPath("turtles")
                   //     .appendPath("types")
                     //   .appendQueryParameter("type", "1")
                       // .appendQueryParameter("sort", "relevance")
                        //.fragment("section-name");
                myVideoView.setMediaController(mediaControls);
                String myurl =builder.build().toString();
                myVideoView.setVideoURI(Uri.parse(myurl));

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            myVideoView.requestFocus();
            myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                    // progressDialog.dismiss();
                    myVideoView.seekTo(position);
                    if (position == 0) {
                        myVideoView.start();
                    } else {
                        myVideoView.pause();
                    }
                }
            });

        }

       /* private void playStream(String src){
            Uri UriSrc = Uri.parse(src);
            if(UriSrc == null){
                Toast.makeText(this,
                        "UriSrc == null", Toast.LENGTH_LONG).show();
            }else{
                streamView.setVideoURI(UriSrc);
                mediaController = new MediaController(this);
                streamView.setMediaController(mediaController);
                streamView.start();

                Toast.makeText(this,
                        "Connect: " + src,
                        Toast.LENGTH_LONG).show();
            }
        }*/
        }
       @Override
       public void onSaveInstanceState(Bundle savedInstanceState) {
           super.onSaveInstanceState(savedInstanceState);
           savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
           myVideoView.pause();
       }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }
       /* @Override
        protected void onDestroy() {
            super.onDestroy();
            streamView.stopPlayback();
        }*/

}
