package com.my.diyetisyen.modeller;

public class MesajModel {
    String tarih;
    String yazi;

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getYazi() {
        return yazi;
    }

    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    public MesajModel(String tarih, String yazi) {
        this.tarih = tarih;
        this.yazi = yazi;
    }
}
