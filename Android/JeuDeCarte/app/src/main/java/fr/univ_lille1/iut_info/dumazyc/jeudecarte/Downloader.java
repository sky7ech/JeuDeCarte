package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

import android.net.http.AndroidHttpClient;
import android.os.Build;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;

public class Downloader {
    public String reponse ;
    public Downloader(String url) throws ClientProtocolException, IOException {
        reponse = null;
        HttpGet requete = new HttpGet(url);
        AndroidHttpClient client = AndroidHttpClient.newInstance(Build.MODEL);
        try {
            reponse = client.execute(requete, new BasicResponseHandler());
        } finally {
            if (client != null) client.close();
        }
    }
    public String get() {
        return reponse ;
    }
}
