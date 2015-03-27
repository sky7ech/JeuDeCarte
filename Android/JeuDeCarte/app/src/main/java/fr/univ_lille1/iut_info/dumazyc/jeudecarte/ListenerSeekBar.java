package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Listener de la seekbar qui permet de relancer
 */
public class ListenerSeekBar implements SeekBar.OnSeekBarChangeListener {
    private User user;
    private int miseMinimale;

    private TextView tvArgent;
    private TextView tvMise;

    /**
     * Constructeur de la classe ListenerSeekBar
     *
     * @param miseMinimale la mise minimale
     * @param user         l'utilisateur concerné
     * @param tvArgent     TextView montrant l'argent de l'utilisateur
     * @param tvMise       TextView montrant la mise de l'utilisateur
     */
    public ListenerSeekBar(int miseMinimale, User user, TextView tvArgent, TextView tvMise) {
        this.miseMinimale = miseMinimale;
        this.user = user;
        this.tvArgent = tvArgent;
        this.tvMise = tvMise;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvArgent.setText(user.getArgentDispo() - progress - miseMinimale + " €");
        tvMise.setText(miseMinimale + progress + " €");
        user.setMiseTemporaire(miseMinimale + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
