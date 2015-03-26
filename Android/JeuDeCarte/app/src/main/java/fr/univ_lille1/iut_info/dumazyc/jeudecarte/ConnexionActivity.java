package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


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
    public class AsyncGet extends AsyncTask<String, Long, String> {
        protected Downloader downloader ;
        public void onPreExecute() { super.onPreExecute();        }
        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }
        public String doInBackground(String... url) {
            try {
                downloader = new Downloader(url[0]);
                return downloader.get();
            } catch (IOException Exc) {
                return null ;
            }
        }
        public void onProgressUpdate(Long... values) {
        }
    }

    public void cliqueBouttonConnexion(View v) {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(25);
        String reponse ;
        AsyncGet asyncGet = new AsyncGet();
        Toast.makeText(this, "Run...", Toast.LENGTH_SHORT).show() ;
        asyncGet.execute("");
        try {
            reponse = asyncGet.get() ;
            Toast.makeText(this, reponse, Toast.LENGTH_LONG).show() ;
        } catch (Exception e) {

        }




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
