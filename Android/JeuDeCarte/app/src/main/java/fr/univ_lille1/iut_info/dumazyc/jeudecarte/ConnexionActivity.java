package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Clément Dumazy on 21/03/2015.
 */
public class ConnexionActivity  extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.connexion);
        }

        public void cliqueBouttonConnexion(View v) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
}
