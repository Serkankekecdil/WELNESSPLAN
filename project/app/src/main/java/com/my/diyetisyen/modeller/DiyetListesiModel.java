package com.my.diyetisyen.modeller;

public class DiyetListesiModel {
    String pazartesi;
    String sali;
    String carsamba;
    String persembe;
    String cuma;
    String cumartesi;
    String pazar;

    public String getPazartesi() {
        return pazartesi;
    }

    public void setPazartesi(String pazartesi) {
        this.pazartesi = pazartesi;
    }

    public String getSali() {
        return sali;
    }

    public void setSali(String sali) {
        this.sali = sali;
    }

    public String getCarsamba() {
        return carsamba;
    }

    public void setCarsamba(String carsamba) {
        this.carsamba = carsamba;
    }

    public String getPersembe() {
        return persembe;
    }

    public void setPersembe(String persembe) {
        this.persembe = persembe;
    }

    public String getCuma() {
        return cuma;
    }

    public void setCuma(String cuma) {
        this.cuma = cuma;
    }

    public String getCumartesi() {
        return cumartesi;
    }

    public void setCumartesi(String cumartesi) {
        this.cumartesi = cumartesi;
    }

    public String getPazar() {
        return pazar;
    }

    public void setPazar(String pazar) {
        this.pazar = pazar;
    }

    public DiyetListesiModel(String pazartesi, String sali, String carsamba, String persembe, String cuma, String cumartesi, String pazar) {
        this.pazartesi = pazartesi;
        this.sali = sali;
        this.carsamba = carsamba;
        this.persembe = persembe;
        this.cuma = cuma;
        this.cumartesi = cumartesi;
        this.pazar = pazar;
    }
}
