package com.example.myapplication;

public interface OnPokemonChangedListener {
    public void onPokemonAdded(PokemonFirebase pokemonFirebase);
    public void onPokemonRemoved(PokemonFirebase pokemonFirebase);
}
