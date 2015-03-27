package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter de la liste d'affichage des joueurs
 */
public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<User> listUser;

    /**
     * Constructeur de la classe CustomListAdapter
     *
     * @param context  l'activit&eacute; de la classe principale
     * @param listUser la liste des utilisateurs
     */
    public CustomListAdapter(Activity context,
                             List<User> listUser) {
        super(context, R.layout.list_adapter, listUser);
        this.context = context;
        this.listUser = listUser;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_adapter, null, true);
        TextView pseudo = (TextView) rowView.findViewById(R.id.pseudo);
        pseudo.setText(listUser.get(position).toString());
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        if (listUser.get(position).getcEstASonTourDeJouer()) {
            imageView.setImageResource(R.drawable.arroww);
        }
        TextView argent = (TextView) rowView.findViewById(R.id.argent);
        argent.setText(listUser.get(position).getArgentDispo() + "€");
        TextView statut = (TextView) rowView.findViewById(R.id.statut);
        if (listUser.get(position).getStatut() == 0)
            statut.setText("");
        if (listUser.get(position).getStatut() == 1)
            statut.setText("Petite Blind");
        if (listUser.get(position).getStatut() == 2)
            statut.setText("Grosse Blind");
        if (listUser.get(position).getStatut() == 3)
            statut.setText("S'est couché");
        if (listUser.get(position).getStatut() == 4)
            statut.setText("A suivi");
        if (listUser.get(position).getStatut() == 5)
            statut.setText("A relancé");
        TextView mise = (TextView) rowView.findViewById(R.id.mise);
        if (listUser.get(position).getStatut() == 1 && listUser.get(position).getMiseActuelle() == 0) {
            listUser.get(position).setMiseActuelle(15);
        } else if (listUser.get(position).getStatut() == 2 && listUser.get(position).getMiseActuelle() == 0) {
            listUser.get(position).setMiseActuelle(30);
        }
        mise.setText(listUser.get(position).getMiseActuelle() + "€");
        return rowView;
    }
}