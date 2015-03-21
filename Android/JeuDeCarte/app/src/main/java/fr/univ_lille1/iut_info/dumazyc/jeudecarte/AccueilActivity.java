package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Clément Dumazy on 21/03/2015.
 */
public class AccueilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
    }

    public void cliqueBouttonAccueil(View v) {
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }

}
