package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.widget.SeekBar;
import android.widget.TextView;

public class ListenerSeekBar implements SeekBar.OnSeekBarChangeListener {
    private  User user;
    private int miseMinimale;

    private TextView tvArgent;
    private TextView tvMise;

    public ListenerSeekBar(int miseMinimale, User user, TextView tvArgent, TextView tvMise) {
        this.miseMinimale = miseMinimale;
       this.user= user;
        this.tvArgent = tvArgent;
        this.tvMise = tvMise;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvArgent.setText(user.getArgentDispo()-progress-miseMinimale+" €");
        tvMise.setText(miseMinimale+progress+" €");
        user.setMiseActuelle(miseMinimale+progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
