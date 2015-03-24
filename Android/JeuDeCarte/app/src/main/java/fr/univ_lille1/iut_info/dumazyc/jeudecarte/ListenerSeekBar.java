package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.widget.SeekBar;
import android.widget.TextView;

public class ListenerSeekBar implements SeekBar.OnSeekBarChangeListener {
    private int argent;
    private int mise;
    private TextView tvArgent;
    private TextView tvMise;

    public ListenerSeekBar(int argent, int mise, TextView tvArgent, TextView tvMise) {
        this.argent = argent;
        this.mise = mise;
        this.tvArgent = tvArgent;
        this.tvMise = tvMise;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvArgent.setText(argent-progress+" €");
        
        tvMise.setText(progress+" €");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
