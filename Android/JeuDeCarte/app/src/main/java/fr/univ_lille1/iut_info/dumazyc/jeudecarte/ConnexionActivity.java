package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class ConnexionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.connexion);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        String pseudo = intent.getStringExtra("pseudo");
        String password = intent.getStringExtra("password");
        TextView tvpseudo = (TextView) findViewById(R.id.editPseudo);
        tvpseudo.setText(pseudo);
        TextView tvpassword = (TextView) findViewById(R.id.editMdp);
        tvpassword.setText(password);
    }

    public void cliqueBouttonConnexion(View v) {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(25);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void cliqueBouttonCreationCompte(View v) {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(25);
        Intent intent = new Intent(this, CreationProfilActivity.class);
        startActivity(intent);
        finish();
    }
}
