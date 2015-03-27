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
    List<String> listScoreValeur;
    List<User> listUserGagnants;
    User userActuel;

    public ComparateurDeScore(ArrayList<User> listUser, ArrayList<String> listCarte) {
        this.listUser = listUser;
        this.listCarte = listCarte;
        this.listCarreau = new ArrayList<>();
        this.listCoeur = new ArrayList<>();
        this.listPique = new ArrayList<>();
        this.listTrefle = new ArrayList<>();
        this.listScore = new ArrayList<>();
        this.listValeur = new Integer[13];
        this.listUserGagnants = new ArrayList<>();
        this.listScoreValeur = new ArrayList<>();
        for (int i = 0; i < listValeur.length; i++) {
            listValeur[i] = 0;
        }
    }

    public User quiEstLeGagnant() {
        for (int i = 0; i < listUser.size(); i++) {
            userActuel = listUser.get(i);
            System.out.println("........................................" + listUser.get(i).getPseudo());
            mettreFlopDansListe();
            mettreCartesJoueurDansListe(listUser.get(i));
            Collections.sort(listCarreau);
            Collections.sort(listCoeur);
            Collections.sort(listPique);
            Collections.sort(listTrefle);
            if (testQuinteFlush() != 0) {
                listScore.add(1);
                listScoreValeur.add(testQuinteFlush() + "");
            } else if (testCarre() != 0) {
                listScore.add(2);
                listScoreValeur.add(testCarre() + "");
            } else if (!testFull().equals("")) {
                listScore.add(3);
                listScoreValeur.add(testFull());
            } else if (!testCouleur().equals("")) {
                listScore.add(4);
                listScoreValeur.add(testCouleur());
            } else if (testSuite() != 0) {
                listScore.add(5);
                listScoreValeur.add(testSuite() + "");
            } else if (testBrelan() != 0) {
                listScore.add(6);
                listScoreValeur.add(testBrelan() + "");
            } else if (!testDoublePaire().equals("")) {
                listScore.add(7);
                listScoreValeur.add(testDoublePaire());
            } else if (testPaire() != 0) {
                listScore.add(8);
                listScoreValeur.add(testPaire() + "");
            } else {
                listScore.add(9);
                listScoreValeur.add(testHauteCarte() + "");
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
            listValeur[Integer.parseInt(listCarte.get(i).substring(1, listCarte.get(i).indexOf("_"))) - 1]++;
            for (int j = 0; j < 4; j++) {
                if (listCarte.get(i).substring(listCarte.get(i).indexOf("_")).equals(i + 1)) {
                    getList(i).add(Integer.parseInt(listCarte.get(i).substring(1, listCarte.get(i).indexOf("_"))));
                }
            }
        }
    }

    private void mettreCartesJoueurDansListe(User user) {
        listValeur[Integer.parseInt(user.getCarte1().substring(1, user.getCarte1().indexOf("_"))) - 1]++;
        listValeur[Integer.parseInt(user.getCarte2().substring(1, user.getCarte2().indexOf("_"))) - 1]++;
        for (int i = 0; i < 4; i++) {
            if (user.getCarte1().substring(user.getCarte1().indexOf("_")).equals(i + 1)) {
                getList(i).add(Integer.parseInt(user.getCarte1().substring(1, user.getCarte1().indexOf("_"))));
            }
            if (user.getCarte2().substring(user.getCarte2().indexOf("_")).equals(i + 1)) {
                getList(i).add(Integer.parseInt(user.getCarte2().substring(1, user.getCarte2().indexOf("_"))));
            }
        }
    }

    private int testQuinteFlush() {
        for (int j = 0; j < 4; j++) {
            int tmp = 0;
            for (int i = 0; i < getList(j).size() - 1; i++) {
                if ((getList(j).get(i) + getList(j).get(i + 1)) / 2 == getList(j).get(i) + 0.5) {
                    tmp++;
                    if (tmp == 4) {
                        return i;
                    }
                } else {
                    tmp = 0;
                }
            }
        }

        return 0;
    }

    private int testCarre() {
        for (int i = listValeur.length - 1; i > -1; i--) {
            if (listValeur[i].equals(4)) {
                return i;
            }
        }
        return 0;
    }

    private String testFull() {
        if (testBrelan() > 0 && testPaire() > 0) {
            return testBrelan() + "_" + testPaire();
        }
        return "";
    }

    private String testCouleur() {
        for (int i = 0; i < 4; i++) {
            if (getList(i).size() >= 5) {
                int valeur1 = Integer.parseInt(userActuel.getCarte1().substring(1, userActuel.getCarte1().indexOf("_")));
                int valeur2 = Integer.parseInt(userActuel.getCarte2().substring(1, userActuel.getCarte2().indexOf("_")));
                if (valeur1 > valeur2) {
                    return valeur1 + "_" + valeur2;
                } else {
                    return valeur2 + "_" + valeur1;
                }
            }
        }
        return "";
    }

    private int testSuite() {
        int tmp = 0;
        for (int i = 0; i < listValeur.length; i++) {
            if (listValeur[i] > 0) {
                tmp++;
                if (tmp == 5) {
                    return i;
                }
            } else {
                tmp = 0;
            }

        }
        return 0;
    }

    private int testBrelan() {
        for (int i = listValeur.length - 1; i > -1; i--) {
            if (listValeur[i].equals(3)) {
                return i;
            }
        }
        return 0;
    }

    private String testDoublePaire() {
        String reponse = "";
        int tmp = 0;
        for (int i = listValeur.length - 1; i > -1; i--) {
            if (listValeur[i].equals(2)) {
                tmp++;
                if (tmp == 1) {
                    reponse += i + "_";
                }
                if (tmp == 2) {
                    reponse += i + "";
                    return reponse;
                }
            }
        }
        return "";
    }

    private int testPaire() {
        for (int i = listValeur.length - 1; i > -1; i--) {
            if (listValeur[i].equals(2)) {
                return i;
            }
        }
        return 0;
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

    private int testHauteCarte() {
        for (int i = listValeur.length - 1; i > -1; i--) {
            if (listValeur[i].equals(1)) {
                return i;
            }
        }
        return 0;
    }

    private int gagnant() {
        int maxValeur = 9;
        int maxIndice = 0;
        List<Integer> listMaxIndice = new ArrayList<>();
        for (int i = 0; i < listScore.size(); i++) {
            if (listScore.get(i) < maxValeur) {
                listUserGagnants = new ArrayList<>();
                listMaxIndice = new ArrayList<>();
                listUserGagnants.add(listUser.get(i));
                maxValeur = listScore.get(i);
                maxIndice = i;
                listMaxIndice.add(i);
            } else if (listScore.get(i).equals(maxValeur)) {
                listUserGagnants.add(listUser.get(i));
                listMaxIndice.add(i);
            }
        }
        for (int i = 0; i < listScore.size(); i++) {
            System.out.println("............" + listScore.get(i).toString());

        }
        for (int i = 0; i < listUserGagnants.size(); i++) {
            System.out.println("............" + listUserGagnants.get(i).toString());

        }
        if (listUserGagnants.size() == 1) {
            return maxIndice;
        }
        List<Integer> listMaxIndice2 = new ArrayList<>();
        maxIndice = 1;
        for (int i = 0; i < listMaxIndice.size(); i++) {//as -> 0
            System.out.println("..............." + listScoreValeur.get(listMaxIndice.get(i)));
            if (listScoreValeur.get(listMaxIndice.get(i)).length() < 3) {
                if ((maxIndice == 0 && Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i))) == 0) || Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i))) == maxIndice) {
                    maxIndice = Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)));

                } else if (Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i))) == 0 || (Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i))) > maxIndice && maxIndice != 0)) {
                    listMaxIndice2 = new ArrayList<>();
                    maxIndice = Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)));
                    listMaxIndice2.add(listMaxIndice.get(i));
                }
            } else {
                if ((maxIndice == 0 && Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)).substring(0,listScoreValeur.get(listMaxIndice.get(i)).indexOf("_"))) == 0) || Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)).substring(0,listScoreValeur.get(listMaxIndice.get(i)).indexOf("_"))) == maxIndice) {
                    maxIndice = Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)).substring(0,listScoreValeur.get(listMaxIndice.get(i)).indexOf("_")));

                } else if (Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)).substring(0,listScoreValeur.get(listMaxIndice.get(i)).indexOf("_"))) == 0 || (Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)).substring(0,listScoreValeur.get(listMaxIndice.get(i)).indexOf("_"))) > maxIndice && maxIndice != 0)) {
                    listMaxIndice2 = new ArrayList<>();
                    maxIndice = Integer.parseInt(listScoreValeur.get(listMaxIndice.get(i)).substring(0,listScoreValeur.get(listMaxIndice.get(i)).indexOf("_")));
                    listMaxIndice2.add(listMaxIndice.get(i));
                }
            }
        }
        if (listMaxIndice2.size() == 1) {
            return listMaxIndice2.get(0);
        }
        return 0;
    }
}
