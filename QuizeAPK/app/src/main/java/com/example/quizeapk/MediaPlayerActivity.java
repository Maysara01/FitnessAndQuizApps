package com.example.quizeapk;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MediaPlayerActivity extends AppCompatActivity {

    Button btnPlay, btnPause, btnBack;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // Initialisation des boutons
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnBack = findViewById(R.id.btnBack);

        // Initialisation du lecteur audio avec un fichier dans res/raw
        mediaPlayer = MediaPlayer.create(this, R.raw.music_sample); // Remplace par ton fichier audio

        // Bouton PLAY
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // Démarre la musique
                }
            }
        });

        // Bouton PAUSE
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); // Met en pause la musique
                }
            }
        });

        // Bouton BACK (ferme l'application)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Retour en arrière
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Libère la mémoire
            mediaPlayer = null;
        }
    }
}