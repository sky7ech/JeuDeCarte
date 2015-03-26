package fr.univ_lille1.iut_info.dumazyc.jeudecarte;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparateurDeScore {
    List<User> listUser;
    List<String> listCarte;
    List<Integer> listCoeur;
    List<Integer> listCarreau;
    List<Integer> listPique;
    List<Integer> listTrefle;
    Integer[] listValeur;
    List<Integer> listScore;

    public ComparateurDeScore(ArrayList<User> listUser, ArrayList<String> listCarte) {
        this.listUser = listUser;
        this.listCarte = listCarte;
        this.listCarreau = new ArrayList<>();
        this.listCoeur = new ArrayList<>();
        this.listPique = new ArrayList<>();
        this.listTrefle = new ArrayList<>();
        this.listScore = new ArrayList<>();
        this.listValeur = new Integer[13];
        for (int i = 0; i < listValeur.length; i++) {
            listValeur[i] = 0;
        }
    }

    public User quiEstLeGagnant() {
        for (int i = 0; i < listUser.size(); i++) {
            System.out.println("........................................"+listUser.get(i).getPseudo());
            mettreFlopDansListe();
            mettreCartesJoueurDansListe(listUser.get(i));
            Collections.sort(listCarreau);
            Collections.sort(listCoeur);
            Collections.sort(listPique);
            Collections.sort(listTrefle);
            if (testQuinteFlush()) {
                listScore.add(1);
            } else if (testCarre()) {
                listScore.add(2);
            } else if (testFull()) {
                listScore.add(3);
            } else if (testCouleur()) {
                listScore.add(4);
            } else if (testSuite()) {
                listScore.add(5);
            } else if (testBrelan()) {
                listScore.add(6);
            } else if (testDoublePaire()) {
                listScore.add(7);
            } else if (testPaire()) {
                listScore.add(8);
            } else {
                listScore.add(9);
            }
        }
        return listUser.get(gagnant());
    }

    private void mettreFlopDansListe() {
        this.listCarreau = new ArrayList<>();
        this.listCoeur = new ArrayList<>();
        this.listPique = new ArrayList<>();
        this.listTrefle = new ArrayList<>();
        this.listValeur = new Integer[13];
        for (int i = 0; i < listValeur.length; i++) {
            listValeur[i] = 0;
        }
        for (int i = 0; i < listCarte.size(); i++) {
            listValeur[Integer.parseInt(listCarte.get(i).substring(1, listCarte.get(i).indexOf("_")))-1]++;
            for (int j = 0; j < 4; j++) {
                if (listCarte.get(i).substring(listCarte.get(i).indexOf("_")).equals(i + 1)) {
                    getList(i).add(Integer.parseInt(listCarte.get(i).substring(1, listCarte.get(i).indexOf("_"))));
                }
            }
        }
    }

    private void mettreCartesJoueurDansListe(User user) {
        listValeur[Integer.parseInt(user.getCarte1().substring(1, user.getCarte1().indexOf("_")))-1]++;
        listValeur[Integer.parseInt(user.getCarte2().substring(1, user.getCarte2().indexOf("_")))-1]++;
        for (int i = 0; i < 4; i++) {
            if (user.getCarte1().substring(user.getCarte1().indexOf("_")).equals(i + 1)) {
                getList(i).add(Integer.parseInt(user.getCarte1().substring(1, user.getCarte1().indexOf("_"))));
            }
            if (user.getCarte2().substring(user.getCarte2().indexOf("_")).equals(i + 1)) {
                getList(i).add(Integer.parseInt(user.getCarte2().substring(1, user.getCarte2().indexOf("_"))));
            }
        }
    }

    private boolean testQuinteFlush() {
        for (int j = 0; j < 4; j++) {
            int tmp = 0;
            for (int i = 0; i < getList(j).size() - 1; i++) {
                if ((getList(j).get(i) + getList(j).get(i + 1)) / 2 == getList(j).get(i) + 0.5) {
                    tmp++;
                    if (tmp == 4) {
                        return true;
                    }
                } else {
                    tmp = 0;
                }
            }
        }

        return false;
    }

    private boolean testCarre() {
        for (int i = 0; i < listValeur.length; i++) {
            if (listValeur[i].equals(4)) {
                return true;
            }
        }
        return false;
    }

    private boolean testFull() {
        if (testBrelan() && testPaire()) {
            return true;
        }
        return false;
    }

    private boolean testCouleur() {
        for (int i = 0; i < 4; i++) {
            if (getList(i).size() >= 5) {
                return true;
            }
        }
        return false;
    }

    private boolean testSuite() {
        int tmp = 0;
        for (int i = 0; i < listValeur.length; i++) {
            if (listValeur[i] > 0) {
                tmp++;
                if (tmp == 5) {
                    return true;
                }
            } else {
                tmp = 0;
            }

        }
        return false;
    }

    private boolean testBrelan() {
        for (int i = 0; i < listValeur.length; i++) {
            if (listValeur[i].equals(3)) {
                return true;
            }
        }
        return false;
    }

    private boolean testDoublePaire() {
        int tmp = 0;
        for (int i = 0; i < listValeur.length; i++) {
            if (listValeur[i].equals(2)) {
                tmp++;
                if (tmp == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean testPaire() {
        for (int i = 0; i < listValeur.length; i++) {
            if (listValeur[i].equals(2)) {
                return true;
            }
        }
        return false;
    }


    private List<Integer> getList(int i) {
        if (i == 0)
            return listCoeur;
        if (i == 1)
            return listCarreau;
        if (i == 2)
            return listPique;
        if (i == 3)
            return listTrefle;
        return null;
    }
    private int gagnant(){
        int maxValeur=9;
        int maxIndice=0;
        for(int i =0; i<listScore.size();i++){
            if (listScore.get(i)<maxValeur){
                maxIndice = i;
            }
        }
        for(int i =0; i<listScore.size();i++) {
            System.out.println("............"+listScore.get(i).toString());
        }
        return maxIndice;
    }
}
