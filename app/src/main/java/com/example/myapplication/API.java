package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Random;

public class API {
    Double first_longitude,last_longitude,first_latitude,last_latitude;
    String[] pokemons={"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot","Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew","Sandslash","Nidoran F","Nidorina","Nidoqueen","Nidoran M","Nidorino","Nidoking","Clefairy","Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras","Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra","Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash","Slowpoke","Slowbro","Magnemite","Magneton","Farfetch d","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix","Drowsee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marrowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan","Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee","Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};
    int[] power={318,405,525,309,405,534,314,405,530,195,205,395,195,205,395,251,349,479,253,413,262,442,288,438,320,485,300,450,275,365,505,273,365,505,323,483,299,505,270,435,245,455,320,395,490,285,405,305,450,265,405,290,440,320,500,305,455,350,555,300,385,510,310,400,500,305,405,505,300,390,490,335,515,300,390,495,410,500,315,490,325,465,352,310,460,325,475,325,500,305,525,310,405,500,385,328,483,325,475,330,480,325,520,320,425,455,455,385,340,490,345,485,450,435,490,295,440,320,450,340,520,460,500,455,490,495,500,490,200,540,535,288,325,525,525,525,395,355,495,355,495,515,540,580,580,580,300,420,600,680,600};

    DatabaseReference dbRef;
    API(DatabaseReference dbRef){
        this.dbRef=dbRef;
        first_longitude=21.043729;
        first_latitude=105.771409;
        last_longitude=21.019138;
        last_latitude=105.806473;
    }
    API(DatabaseReference dbRef, Double first_longitude,Double first_latitude,Double last_longitude,Double last_latitude){
        this.dbRef=dbRef;
        this.first_latitude=first_latitude;
        this.first_longitude=first_longitude;
        this.last_latitude=last_latitude;
        this.last_longitude=last_longitude;
    }
    /**
     * Get pokemons in map
     */
    public OnPokemonChangedListener mPokemonListener;

    public void getPokemon(){
        dbRef.child("pokemonInMap").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                dataSnapshot.getKey();
                PokemonFirebase pokemonFirebase=dataSnapshot.getValue(PokemonFirebase.class);
                pokemonFirebase.setKey(dataSnapshot.getKey());
//                Log.v("pokemon","key "+dataSnapshot.getKey());
                mPokemonListener.onPokemonAdded(pokemonFirebase);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                PokemonFirebase pokemonFirebase=dataSnapshot.getValue(PokemonFirebase.class);
//                Log.v("pokemon","onChildChanged "+pokemonFirebase.getName());
//                mListener.onPokemonAdded(pokemonFirebase);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                PokemonFirebase pokemonFirebase=dataSnapshot.getValue(PokemonFirebase.class);
                Log.v("pokemon","onChildRemoved "+pokemonFirebase.getName());
                mPokemonListener.onPokemonRemoved(pokemonFirebase);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setOnPokemonChangedListener(OnPokemonChangedListener mListener){
        this.mPokemonListener=mListener;
    }

    /**
     * Create user
     * @param idFb id Facebook User
     * @param name name Facebook User
     */
    public void createUser(String idFb,String name){
        //kiem tra id co chua
        //neu chua thif tao-> true
        //neu roi -> false

        User user=new User(name);
        dbRef.child("user").child(idFb).setValue(user);
    }

    /**
     * Get Pokemon in Bag of User
     * if Bag of User on Firebase is changed, Bag in app would be changed
     */
    public OnPokemonInBagChangedListener mPokemonInBagListener;
    public void getPokemonInBag(String idFb){
        dbRef.child("user").child(idFb).child("pokemons").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                PokemonFirebase pokemonFirebase= dataSnapshot.getValue(PokemonFirebase.class);

                mPokemonInBagListener.onPokemonInBagAdded(pokemonFirebase);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void setOnPokemonInBagChangedListener(OnPokemonInBagChangedListener mPokemonInBagListener){
        this.mPokemonInBagListener=mPokemonInBagListener;
    }

    /**
     * Update Firebase when caught Pokemon
     */
    public void caughtPokemon(PokemonFirebase pokemonFirebase, String idFb){
        PokemonFirebase pokemonFirebaseNotLocation = new PokemonFirebase(null,pokemonFirebase.getIndex(),pokemonFirebase.getName(),pokemonFirebase.getPower(),null,null);
        dbRef.child("pokemonInMap").child(pokemonFirebase.getKey()).removeValue();
        dbRef.child("user").child(idFb).child("pokemons").push().setValue(pokemonFirebase);

        Random rd=new Random();
        int index = Math.abs(rd.nextInt()%150) ;
        Double latitude=rd.nextDouble()*(last_latitude-first_latitude)+first_latitude;
        Double longitude=rd.nextDouble()*(last_longitude-first_longitude)+first_longitude;
        PokemonFirebase pokemonFirebaseNew = new PokemonFirebase(null,index,pokemons[index],power[index],latitude,longitude);
        dbRef.child("pokemonInMap").push().setValue(pokemonFirebaseNew);
    }
}
