package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {
    List<String> listeNomCartes;
    List<ImageView> listeImageView;
    List<String> listUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create();

    }

    public void create() {

        setContentView(R.layout.activity_main);

        ImageView imageCarte1 = (ImageView) findViewById(R.id.carte1);
        ImageView imageCarte2 = (ImageView) findViewById(R.id.carte2);
        ImageView imageCarteFlop1 = (ImageView) findViewById(R.id.carteFlop1);
        ImageView imageCarteFlop2 = (ImageView) findViewById(R.id.carteFlop2);
        ImageView imageCarteFlop3 = (ImageView) findViewById(R.id.carteFlop3);
        ImageView imageCarteFlop4 = (ImageView) findViewById(R.id.carteFlop4);
        ImageView imageCarteFlop5 = (ImageView) findViewById(R.id.carteFlop5);
        listeImageView = new ArrayList<>();
        listeImageView.add(imageCarte1);
        listeImageView.add(imageCarte2);
        listeImageView.add(imageCarteFlop1);
        listeImageView.add(imageCarteFlop2);
        listeImageView.add(imageCarteFlop3);
        listeImageView.add(imageCarteFlop4);
        listeImageView.add(imageCarteFlop5);

        initialiserPaquetCarte();
        listUser = new ArrayList<>();

        listUser.add((new User("tmp1", 1000, 20)).toString());
        listUser.add((new User("tmp2", 500000, 1000)).toString());
        listUser.add((new User("tmp3", 1000000, 1000000)).toString());
        listUser.add((new User("tmp4", 1000, 20)).toString());
        listUser.add((new User("tmp5", 1000, 20)).toString());
        listUser.add((new User("tmp6", 1000, 20)).toString());
        ListView listView = (ListView) findViewById(R.id.listUser);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUser);
        listView.setAdapter(itemsAdapter);
        TextView tw = (TextView) findViewById(R.id.nomUtilisateur);
        tw.setText(listUser.get(0).substring(0, 5));
    }

    private void initialiserPaquetCarte() {
        this.listeNomCartes = new ArrayList<>();
        String nomDeLaCarte;
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 5; j++) {
                nomDeLaCarte = "a" + i + "_" + j;
                listeNomCartes.add(nomDeLaCarte);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_connexion) {
            setContentView(R.layout.connexion);
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(25);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void connexion() {
        setContentView(R.layout.connexion);
    }

    public void redistributeCards(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);

        /*if(imageCarte1!=null) {
            ((BitmapDrawable) imageCarte1.getDrawable()).getBitmap().recycle();
        }
        if(imageCarte2!=null) {
            ((BitmapDrawable)imageCarte2.getDrawable()).getBitmap().recycle();
        }*/

        Random r = new Random();
        int random;
        for (int i = 0; i < 7; i++) {

            random = r.nextInt(listeNomCartes.size());
            listeImageView.get(i).setImageResource(getResources().getIdentifier(listeNomCartes.get(random), "drawable", getPackageName()));
            listeNomCartes.remove(random);
        }

    }

    @Override
    public void onDestroy() {
        System.gc();
        for (int i = 0; i < listeImageView.size(); i++) {
            listeImageView.get(i).setImageDrawable(null);
        }
        super.onDestroy();

    }
}
