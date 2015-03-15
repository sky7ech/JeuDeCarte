package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {
    ImageView imageCarte1;
    ImageView imageCarte2;
    ImageView imageCarteFlop1;
    ImageView imageCarteFlop2;
    ImageView imageCarteFlop3;
    ImageView imageCarteFlop4;
    ImageView imageCarteFlop5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageCarteFlop1 = (ImageView) findViewById(R.id.carteFlop1);
        imageCarteFlop2 = (ImageView) findViewById(R.id.carteFlop2);
        imageCarteFlop3 = (ImageView) findViewById(R.id.carteFlop3);
        imageCarteFlop4 = (ImageView) findViewById(R.id.carteFlop4);
        imageCarteFlop5 = (ImageView) findViewById(R.id.carteFlop5);
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

        return super.onOptionsItemSelected(item);
    }

    public List<Integer> getIdList() {
        List<Integer> idList = new ArrayList<Integer>();

        return idList;
    }

    public void redistributeCards(View view) {

        /*if(imageCarte1!=null) {
            ((BitmapDrawable) imageCarte1.getDrawable()).getBitmap().recycle();
        }
        if(imageCarte2!=null) {
            ((BitmapDrawable)imageCarte2.getDrawable()).getBitmap().recycle();
        }*/

        imageCarte1 = (ImageView) findViewById(R.id.carte1);
        imageCarte2 = (ImageView) findViewById(R.id.carte2);


        Random r = new Random();
        int valeur = r.nextInt(13) + 1;
        int couleur = r.nextInt(4) + 1;
        String nomDeLaCarte1 = "a" + valeur + "_" + couleur;
        System.out.print(nomDeLaCarte1);
        imageCarte1.setImageResource(getResources().getIdentifier(nomDeLaCarte1, "drawable", getPackageName()));
        String nomDeLaCarte2 = nomDeLaCarte1;
        while (nomDeLaCarte2.equals(nomDeLaCarte1)) {
            valeur = r.nextInt(13) + 1;
            couleur = r.nextInt(4) + 1;
            nomDeLaCarte2 = "a" + valeur + "_" + couleur;
        }
        System.out.println("   " + nomDeLaCarte2);
        imageCarte2.setImageResource(getResources().getIdentifier(nomDeLaCarte2, "drawable", getPackageName()));
    }

    @Override
    public void onDestroy() {

        System.gc();
        imageCarte1.setImageDrawable(null);
        imageCarte2.setImageDrawable(null);
        imageCarteFlop1.setImageDrawable(null);
        imageCarteFlop1.setImageDrawable(null);
        imageCarteFlop2.setImageDrawable(null);
        imageCarteFlop3.setImageDrawable(null);
        imageCarteFlop4.setImageDrawable(null);
        imageCarteFlop5.setImageDrawable(null);

        super.onDestroy();

    }
}
