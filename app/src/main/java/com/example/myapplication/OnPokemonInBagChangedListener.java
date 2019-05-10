package com.example.myapplication;

public interface OnPokemonInBagChangedListener {
    public void onPokemonInBagAdded(PokemonFirebase pokemonFirebase);
    public void onPokemonInBagRemoved(PokemonFirebase pokemonFirebase);
}
