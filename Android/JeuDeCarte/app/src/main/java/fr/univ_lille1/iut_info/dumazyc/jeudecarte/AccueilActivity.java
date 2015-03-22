package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class AccueilActivity extends Activity {
    MediaPlayer m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.accueil);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        m = MediaPlayer.create(this,R.raw.start);
        m.start();

    }

    public void cliqueBouttonAccueil(View v) {
        m.release();
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }

}
