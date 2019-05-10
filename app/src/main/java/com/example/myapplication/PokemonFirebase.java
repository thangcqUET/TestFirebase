package com.example.myapplication;

public class PokemonFirebase {
    private int index;
    private String name;
    private int power;
    private Double latitude;
    private Double longitude;
    private String key=null;
    public PokemonFirebase(){};

    public PokemonFirebase(String key,int index, String name, int power, Double latitude, Double longitude) {
        this.index = index;
        this.name = name;
        this.power = power;
        this.latitude = latitude;
        this.longitude = longitude;
        this.key=key;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key=key;
    }
    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
