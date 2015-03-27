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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe permettant de cr&eacute;er un nouveau profil
 */
public class CreationProfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creation_profil);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Permet de cr&eacute;er de profil
     */
    public void cliqueBouttonCreer(View v) {
        TextView pseudo = (TextView) findViewById(R.id.editTextPseudo);
        TextView password = (TextView) findViewById(R.id.editTextPassword);
        TextView passwordVerif = (TextView) findViewById(R.id.editTextPasswordVerif);
        TextView email = (TextView) findViewById(R.id.editTextEmail);
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(25);
        List<TextView> l = new ArrayList<>();
        l.add(pseudo);
        l.add(password);
        l.add(passwordVerif);
        l.add(email);
        for (int i = 0; i < l.size(); i++) {
            l.get(i).setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        if (pseudo.getText().toString().equals("") || password.getText().toString().equals("") || passwordVerif.getText().toString().equals("") || email.getText().toString().equals("")) {
            Toast.makeText(this, "Vous devez renseigner toutes les informations !", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).getText().toString().equals("")) {
                    l.get(i).setBackgroundColor(getResources().getColor(R.color.error));
                }
            }
        } else if (!password.getText().toString().equals(passwordVerif.getText().toString())) {
            Toast.makeText(this, "Les mots de passes sont différents !", Toast.LENGTH_SHORT).show();
            l.get(1).setBackgroundColor(getResources().getColor(R.color.error));
            l.get(2).setBackgroundColor(getResources().getColor(R.color.error));
        } else {
            Pattern p = Pattern.compile("^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$");
            Matcher m = p.matcher(email.getText().toString());
            if (!m.matches()) {
                Toast.makeText(this, "Adresse mail invalide", Toast.LENGTH_SHORT).show();
                l.get(3).setBackgroundColor(getResources().getColor(R.color.error));
            } else {

                Toast.makeText(this, "Votre compte a été créé avec succès !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ConnexionActivity.class);
                intent.putExtra("pseudo", pseudo.getText().toString());
                intent.putExtra("password", password.getText().toString());
                startActivity(intent);
                finish();
            }
        }
    }

    /**
     * Permet d'annuler la cr&eacute;ation de profil
     */
    public void cliqueBouttonAnnuler(View v) {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(25);
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }
}