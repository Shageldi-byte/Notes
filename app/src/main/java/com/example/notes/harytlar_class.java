package com.example.notes;

public class harytlar_class {
    String id;
    String haryt_ady;
    String baha;
    String sany;
    String jemi;

    public harytlar_class(String id, String haryt_ady, String baha, String sany, String jemi) {
        this.id = id;
        this.haryt_ady = haryt_ady;
        this.baha = baha;
        this.sany = sany;
        this.jemi = jemi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHaryt_ady() {
        return haryt_ady;
    }

    public void setHaryt_ady(String haryt_ady) {
        this.haryt_ady = haryt_ady;
    }

    public String getBaha() {
        return baha;
    }

    public void setBaha(String baha) {
        this.baha = baha;
    }

    public String getSany() {
        return sany;
    }

    public void setSany(String sany) {
        this.sany = sany;
    }

    public String getJemi() {
        return jemi;
    }

    public void setJemi(String jemi) {
        this.jemi = jemi;
    }
}
