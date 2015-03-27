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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe contenant l'activit&eacute; du jeu de poker.
 */
public class MainActivity extends Activity {
    private Integer numeroTour = 0;
    private List<String> listeNomCartes;
    private List<ImageView> listeImageView;
    private ArrayList<User> listUser;
    private Dialog d;
    private Integer miseMinimale = 30;
    private ArrayList<String> listCarte;
    private User userGagnant;
    private Integer petiteBlind = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        create();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    /**
     * Permet de lancer un nouveau tour (redistribuage des cartes, mise du pot &agrave; 0,attribution des blinds...)
     */
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

        if (listUser == null) {
            listUser = new ArrayList<>();
            listUser.add(new User("tmp1", 10000, 0, true));
            listUser.add(new User("tmp2", 10000, 0, false));
            listUser.add(new User("tmp3", 10000, 0, false));
            listUser.add(new User("tmp4", 10000, 0, false));
            listUser.add(new User("tmp5", 10000, 1, false));
            listUser.add(new User("tmp6", 10000, 2, false));
        } else {


            listUser.get((petiteBlind + 1) % listUser.size()).setStatut(1);
            listUser.get((petiteBlind + 2) % listUser.size()).setStatut(2);
            listUser.get((petiteBlind + 3) % listUser.size()).setcEstASonTourDeJouer(true);
            petiteBlind++;
        }
        ListView listView = (ListView) findViewById(R.id.listUser);
        CustomListAdapter itemsAdapter;
        itemsAdapter = new CustomListAdapter(this, listUser);
        listView.setAdapter(itemsAdapter);
        TextView tv = (TextView) findViewById(R.id.pot);
        tv.setText("45 €");
        TextView tw = (TextView) findViewById(R.id.miseMin);
        tw.setText("30 €");
        distribuerCarte();
        reloadListJoueur();
    }

    /**
     * Permet d'initialiser la liste des noms de cartes.
     */
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

    /**
     * Distribue les cartes al&eacute;atoirement aux joueurs et sur le flop
     */
    public void distribuerCarte() {
        listCarte = new ArrayList<>();
        Random r = new Random();
        int random;
        for (int i = 0; i < 5; i++) {
            random = r.nextInt(listeNomCartes.size());
            listCarte.add(listeNomCartes.get(random));
            listeNomCartes.remove(random);
        }
        for (int i = 0; i < listUser.size(); i++) {
            random = r.nextInt(listeNomCartes.size());
            listUser.get(i).setCarte1(listeNomCartes.get(random));
            listeNomCartes.remove(random);
            random = r.nextInt(listeNomCartes.size());
            listUser.get(i).setCarte2(listeNomCartes.get(random));
            listeNomCartes.remove(random);
        }
    }

    /**
     * Permet de savoir c'est au tour de quel utilisateur de jouer.
     *
     * @return l'utilisateur &agrave; qui c'est le tour de jouer
     */
    public User cEstLeTourDeQui() {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getcEstASonTourDeJouer()) {
                return listUser.get(i);
            }
        }
        return null;
    }

    /**
     * Permet de savoir quel est l'indice d'un utilisateur dans la liste listUser
     *
     * @param user l'utilisateur dont on veut savoir l'indice
     * @return l'indice de l'utilisateur dans la liste listUser
     */
    public int cEstLIndiceDeQui(User user) {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).equals(user)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onDestroy() {
        MediaPlayer m = MediaPlayer.create(this, R.raw.stop);
        m.start();
        super.onDestroy();
    }

    /**
     * Permet de lancer le Dialog qui permet &agrave; l'utilisateur de choisir de combien il veut relancer.
     */
    public void relancer(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);
        int argent = cEstLeTourDeQui().getArgentDispo();
        d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.custom_dialog_layout);
        TextView tvArgentDispo = (TextView) d.findViewById(R.id.tvArgent);
        tvArgentDispo.setText(argent - getMiseMinimaleJoueurEnCours() + " €");
        TextView tvRelance = (TextView) d.findViewById(R.id.tvRelance);
        tvRelance.setText(getMiseMinimaleJoueurEnCours() + " €");
        Button button = (Button) d.findViewById(R.id.dialogButtonOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cEstLeTourDeQui().setMiseActuelle(cEstLeTourDeQui().getMiseActuelle() + cEstLeTourDeQui().getMiseTemporaire());
                    //cEstLeTourDeQui().setMiseActuelle(cEstLeTourDeQui().getMiseTemporaire());

                } catch (Exception e) {
                    cEstLeTourDeQui().setMiseActuelle(miseMinimale);

                    passeAuProchainTour(4);
                    d.dismiss();
                } finally {
                    if (miseMinimale < cEstLeTourDeQui().getMiseActuelle()) {
                        miseMinimale = cEstLeTourDeQui().getMiseActuelle();
                        passeAuProchainTour(5);
                        d.dismiss();

                    }
                }


            }
        });
        SeekBar sk = (SeekBar) d.findViewById(R.id.seekBar);
        sk.setMax(cEstLeTourDeQui().getArgentDispo() - getMiseMinimaleJoueurEnCours());
        sk.setOnSeekBarChangeListener(new ListenerSeekBar(getMiseMinimaleJoueurEnCours(), cEstLeTourDeQui(), tvArgentDispo, tvRelance));
        d.show();
    }

    /**
     * Permet de rafraichir l'affichage de la liste des joueurs.
     */
    public void reloadListJoueur() {
        ListView listView = (ListView) findViewById(R.id.listUser);
        CustomListAdapter itemsAdapter;
        itemsAdapter = new CustomListAdapter(this, listUser);
        listView.setAdapter(itemsAdapter);
        TextView t = (TextView) findViewById(R.id.pot);
        t.setText(getPot() + " €");
        TextView tv = (TextView) findViewById(R.id.miseMin);
        tv.setText(miseMinimale + " €");
        listeImageView.get(0).setImageResource(getResources().getIdentifier(cEstLeTourDeQui().getCarte1(), "drawable", getPackageName()));
        listeImageView.get(1).setImageResource(getResources().getIdentifier(cEstLeTourDeQui().getCarte2(), "drawable", getPackageName()));


    }

    /**
     * Permet de conna&icirc;tre la valeur du pot
     *
     * @return valeur du pot
     */
    public Integer getPot() {
        Integer pot = 0;
        for (int i = 0; i < listUser.size(); i++) {
            pot += listUser.get(i).getMiseActuelle();
        }
        return pot;
    }

    /**
     * M&eacute;thode qui permet &agrave; l'utilisateur de se coucher
     */
    public void cliqueBoutonSeCoucher(View view) {
        passeAuProchainTour(3);
    }

    /**
     * M&eacute;thode qui permet &agrave; l'utilisateur de suivre
     */
    public void cliqueBoutonSuivre(View view) {
        cEstLeTourDeQui().setMiseActuelle(cEstLeTourDeQui().getMiseActuelle() + this.getMiseMinimaleJoueurEnCours());
        passeAuProchainTour(4);
    }

    /**
     * Permet de tester si le tour ou la partie est termin&eacute;.
     *
     * @param statut action du joueur
     */
    public void passeAuProchainTour(int statut) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);
        cEstLeTourDeQui().setStatut(statut);
        User user = cEstLeTourDeQui();
        user.setcEstASonTourDeJouer(false);
        selectionneLeProchainJoueur(user);
        if (partieTerminee()) {
            ArrayList<User> tmp = new ArrayList<>();
            for (int i = 0; i < listUser.size(); i++) {
                if ((listUser.get(i).getStatut() == 4 || listUser.get(i).getStatut() == 5) && listUser.get(i).getMiseActuelle().equals(miseMinimale)) {
                    tmp.add(listUser.get(i));
                }
            }
            userGagnant = new ComparateurDeScore(tmp, listCarte).quiEstLeGagnant();

            Toast.makeText(this, "Le gagnant du pot est " + userGagnant, Toast.LENGTH_LONG).show();
            numeroTour = 0;
            effectuerTour();
            reloadListJoueur();
        } else if (tourTermine()) {
            numeroTour++;
            if (numeroTour == 4) {
                ArrayList<User> tmp = new ArrayList<>();
                for (int i = 0; i < listUser.size(); i++) {
                    if ((listUser.get(i).getStatut() == 4 || listUser.get(i).getStatut() == 5) && listUser.get(i).getMiseActuelle().equals(miseMinimale)) {
                        tmp.add(listUser.get(i));
                    }
                }
                userGagnant = new ComparateurDeScore(tmp, listCarte).quiEstLeGagnant();

                Toast.makeText(this, "Le gagnant du pot est " + userGagnant, Toast.LENGTH_LONG).show();
                numeroTour = 0;
            }
            effectuerTour();
            reloadListJoueur();
        } else {
            reloadListJoueur();
        }
    }

    /**
     * Permet de savoir si le tour est termin&eacute;
     *
     * @return true si le tour est termin&eacute;, false sinon
     */
    private boolean tourTermine() {
        int nombreDePersonne = 0;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getStatut() == 3 || listUser.get(i).getArgentDispo() == 0 || ((listUser.get(i).getStatut() == 4 || listUser.get(i).getStatut() == 5) && listUser.get(i).getMiseActuelle().equals(miseMinimale))) {
                nombreDePersonne++;
            }
        }
        return nombreDePersonne == listUser.size();
    }

    /**
     * Permet de savoir quel utilisateur sera le prochain &agrave; jouer.
     *
     * @param user utilisateur qui a termin&eacute; son tour
     */
    public void selectionneLeProchainJoueur(User user) {
        int indiceUser = (cEstLIndiceDeQui(user) + 1) % listUser.size();
        if (partieTerminee()) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getStatut() != 3) {
                    indiceUser = i;
                }
            }
        } else {
            while (listUser.get(indiceUser).getStatut() == 3 || listUser.get(indiceUser).getArgentDispo() == 0) {
                indiceUser = (indiceUser + 1) % listUser.size();
            }
        }
        listUser.get(indiceUser).setcEstASonTourDeJouer(true);
    }

    /**
     * Permet de savoir si la partie est termin&eacute;e
     *
     * @return true si la partie est termin&eacute;e, false sinon
     */
    public boolean partieTerminee() {
        int nombreDePersonneCouchee = 0;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getStatut() == 3 || listUser.get(i).getArgentDispo() == 0) {
                nombreDePersonneCouchee++;
            }
        }
        return nombreDePersonneCouchee == listUser.size();
    }

    /**
     * Permet de savoir quelle est la mise minimale du joueur qui est en train de jouer.
     *
     * @return la mise minimale
     */
    public Integer getMiseMinimaleJoueurEnCours() {
        Integer miseMinimaleJoueur = 0;
        if (miseMinimale > cEstLeTourDeQui().getMiseActuelle()) {
            miseMinimaleJoueur = miseMinimale - cEstLeTourDeQui().getMiseActuelle();
        }
        return miseMinimaleJoueur;
    }

    /**
     * Permet de passer au tour suivant
     */
    public void effectuerTour() {
        for (int i = 0; i < listUser.size(); i++) {
            if ((listUser.get(i).getStatut() == 4 || listUser.get(i).getStatut() == 5) && listUser.get(i).getMiseActuelle().equals(miseMinimale)) {
                listUser.get(i).setStatut(0);
            }
        }
        if (numeroTour == 0) {
            userGagnant.setArgentDispo(userGagnant.getArgentDispo() + getPot());
            for (int i = 0; i < listUser.size(); i++) {
                listUser.get(i).setMiseActuelle(0);
                listUser.get(i).setcEstASonTourDeJouer(false);
                listUser.get(i).setStatut(0);
                if (listUser.get(i).getArgentDispo().equals(0)) {
                    listUser.remove(i);
                }

            }
            miseMinimale = 30;
            create();
        } else if (numeroTour == 1) {
            listeImageView.get(2).setImageResource(getResources().getIdentifier(listCarte.get(0), "drawable", getPackageName()));
            listeImageView.get(3).setImageResource(getResources().getIdentifier(listCarte.get(1), "drawable", getPackageName()));
            listeImageView.get(4).setImageResource(getResources().getIdentifier(listCarte.get(2), "drawable", getPackageName()));
        } else if (numeroTour == 2) {
            listeImageView.get(5).setImageResource(getResources().getIdentifier(listCarte.get(3), "drawable", getPackageName()));
        } else if (numeroTour == 3) {
            listeImageView.get(6).setImageResource(getResources().getIdentifier(listCarte.get(4), "drawable", getPackageName()));
        }
    }
}