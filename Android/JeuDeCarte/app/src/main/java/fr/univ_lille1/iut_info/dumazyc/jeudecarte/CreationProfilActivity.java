package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class CreationProfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creation_profil);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void cliqueBouttonCreer(View v) {
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }
}
