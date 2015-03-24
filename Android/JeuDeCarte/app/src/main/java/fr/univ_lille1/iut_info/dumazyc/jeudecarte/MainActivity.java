package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends Activity {
    List<String> listeNomCartes;
    List<ImageView> listeImageView;
    List<User> listUser;
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        create();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        listUser.add(new User("tmp1", 1000, 150, 0, true));
        listUser.add(new User("tmp2", 500000, 150, 0, false));
        listUser.add(new User("tmp3", 1000000, 700, 0, false));
        listUser.add(new User("tmp4", 1000, 900, 0, false));
        listUser.add(new User("tmp5", 1000, 0, 1, false));
        listUser.add(new User("tmp6", 1000, 0, 2, false));
        ListView listView = (ListView) findViewById(R.id.listUser);
        ArrayAdapter<String> itemsAdapter = new CustomListAdapter(this, listUser);

        listView.setAdapter(itemsAdapter);
        TextView tw = (TextView) findViewById(R.id.nomUtilisateur);
        tw.setText(listUser.get(0).toString());
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

    public void redistributeCards(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);
        Random r = new Random();
        int random;
        for (int i = 0; i < 7; i++) {
            random = r.nextInt(listeNomCartes.size());
            listeImageView.get(i).setImageResource(getResources().getIdentifier(listeNomCartes.get(random), "drawable", getPackageName()));
            listeNomCartes.remove(random);
        }
    }

    public User cEstLeTourDeQui() {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getcEstASonTourDeJouer()) {
                return listUser.get(i);
            }
        }
        return null;
    }

    @Override
    public void onDestroy() {
        /*System.gc();
        for (int i = 0; i < listeImageView.size(); i++) {
            listeImageView.get(i).setImageDrawable(null);
        }*/
        MediaPlayer m = MediaPlayer.create(this, R.raw.stop);
        m.start();
        super.onDestroy();

    }

    public void relancer(View view) {
        int argent = cEstLeTourDeQui().getArgentDispo();
        int mise = 0;
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);
        d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.custom_dialog_layout);
        TextView tvArgentDispo = (TextView) d.findViewById(R.id.tvArgent);
        tvArgentDispo.setText(argent + " €");
        TextView tvRelance = (TextView) d.findViewById(R.id.tvRelance);
        tvRelance.setText(mise + " €");
        Button button = (Button) d.findViewById(R.id.dialogButtonOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        SeekBar sk = (SeekBar) d.findViewById(R.id.seekBar);
        sk.setMax(1000);
        sk.setOnSeekBarChangeListener(new ListenerSeekBar(argent, mise, tvArgentDispo, tvRelance));
        d.show();
    }
}