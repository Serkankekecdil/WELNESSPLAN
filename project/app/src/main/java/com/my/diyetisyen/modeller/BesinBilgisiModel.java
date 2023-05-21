package com.my.diyetisyen.modeller;

public class BesinBilgisiModel {
    String ad;
    String kalori;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKalori() {
        return kalori;
    }

    public void setKalori(String kalori) {
        this.kalori = kalori;
    }

    public BesinBilgisiModel(String ad, String kalori) {
        this.ad = ad;
        this.kalori = kalori;
    }
}
