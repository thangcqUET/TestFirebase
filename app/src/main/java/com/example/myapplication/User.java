package com.example.myapplication;

import java.util.ArrayList;

public class User {
    public String name;
    public ArrayList<PokemonFirebase> pokemons;
    public User(){

    }
    public User(String name){
        this.name=name;
        pokemons=new ArrayList<PokemonFirebase>();
    }

}
