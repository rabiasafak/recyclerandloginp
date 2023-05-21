package com.example.notes;

public class Item {
    String fakülte;
    String eposta;
    String dekan;
    int image;

    public String getFakülte() {
        return fakülte;
    }

    public void setFakülte(String fakülte) {
        this.fakülte = fakülte;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getDekan() {
        return dekan;
    }

    public void setDekan(String dekan) {
        this.dekan = dekan;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Item(String fakülte, String eposta, String dekan, int image) {
        this.fakülte = fakülte;
        this.eposta = eposta;
        this.dekan = dekan;
        this.image = image;
    }
}
