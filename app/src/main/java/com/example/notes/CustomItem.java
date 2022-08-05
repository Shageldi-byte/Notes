package com.example.notes;

public class CustomItem {
    String nokat;
    int surat;

    public CustomItem(String nokat, int surat) {
        this.nokat = nokat;
        this.surat = surat;
    }

    public String getNokat() {
        return nokat;
    }

    public int getSurat() {
        return surat;
    }
}
