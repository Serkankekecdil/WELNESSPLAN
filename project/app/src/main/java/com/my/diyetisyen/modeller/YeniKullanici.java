package com.my.diyetisyen.modeller;

public class YeniKullanici {


    String adSoyad;
    String email;

    Boolean diyetisyenMi;

    public Boolean getDiyetisyenMi() {
        return diyetisyenMi;
    }

    public void setDiyetisyenMi(Boolean diyetisyenMi) {
        this.diyetisyenMi = diyetisyenMi;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public YeniKullanici(String adSoyad, String email, Boolean diyetisyenMi) {
        this.adSoyad = adSoyad;
        this.email = email;
        this.diyetisyenMi = diyetisyenMi;
    }
}
