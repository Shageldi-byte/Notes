package com.example.notes;

public class CustomResminama {
    String id;
    String resminama;
    int surat;
    String baha;
    String sany;

    public CustomResminama(String resminama, int surat,String baha,String sany,String id) {
        this.resminama = resminama;
        this.surat = surat;
        this.baha=baha;
        this.sany=sany;
        this.id=id;
    }

    public String getResminama() {
        return resminama;
    }

    public int getSurat() {
        return surat;
    }

    public String getBaha() {
        return baha;
    }

    public String getSany() {
        return sany;
    }

    public String getId() {
        return id;
    }
}
