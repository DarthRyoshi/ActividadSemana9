package com.example.multimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;  // Variable global para manejar el audio
    private VideoView videoView;      // Para el VideoView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de la imagen con animación
        ImageView imageView = findViewById(R.id.imageView);
        Button animateButton = findViewById(R.id.animateButton);
        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
                imageView.startAnimation(fadeInAnimation);
            }
        });

        // Configuración de reproducción de audio
        Button playAudioButton = findViewById(R.id.playAudioButton);
        Button stopAudioButton = findViewById(R.id.stopAudioButton);

        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la reproducción del audio
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.karma_police);
                }
                mediaPlayer.start();
            }
        });

        stopAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detiene el audio si está en reproducción
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;  // Libera el recurso y reinicia el MediaPlayer
                }
            }
        });

        // Configuración de reproducción de video
        videoView = findViewById(R.id.videoView);
        Button playVideoButton = findViewById(R.id.playVideoButton);
        Button stopVideoButton = findViewById(R.id.stopVideoButton);

        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.black_myth);
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        });

        stopVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detener la reproducción de video
                if (videoView.isPlaying()) {
                    videoView.stopPlayback();
                    videoView.resume();  // Esto prepara el video para ser reproducido nuevamente
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera el recurso del audio si la actividad se destruye
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
