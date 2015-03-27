package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Activit&eacute; de la page d'accueil qui est compos&eacute;e d'un bouton
 */
public class AccueilActivity extends Activity {
    MediaPlayer m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.accueil);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        m = MediaPlayer.create(this, R.raw.start);

        m.start();

    }

    /**
     * Effectue l'action de redirection vers la page de ocnnexion
     */
    public void cliqueBouttonAccueil(View v) {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(25);
        m.release();
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }

}
