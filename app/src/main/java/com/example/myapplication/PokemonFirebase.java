package com.example.myapplication;

public class PokemonFirebase {
    /**
     * chi so cua Pokemon, xac dinh day la loai pokemon nao
     * Muc dich:1. khoa cho pokemon, 2. index o day tuong duong voi index trong hinh anh pokemon, de truy xuat hinh anh de in ra
     */
    private int index;
    private String name;
    private int power;
    private Double latitude;
    private Double longitude;
    /**
     * key: chi so cua Pokemon In Map, xac dinh la con Pokemon nao tren map
     * khi xoa can biet do la con Pokemon nao de xoa
     * level phia tren khong can quan tam den thuoc tinh nay
     */
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
