package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    API api=new API(myRef);
    ListView listViewPokemon;
    ArrayList<String> pokemons;
    ArrayAdapter adapter=null;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewPokemon= (ListView) findViewById(R.id.listPokemon);
        pokemons=new ArrayList<String>();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,pokemons);
        listViewPokemon.setAdapter(adapter);
        button=(Button) findViewById(R.id.createUser);
        editText=(EditText) findViewById(R.id.idUser);

//        api.setOnPokemonChangedListener(new OnPokemonChangedListener() {
//            @Override
//            public void onPokemonAdded(PokemonFirebase pokemonFirebase) {
//                pokemons.add(pokemonFirebase.getName());
////                Log.v("key",pokemonFirebase.getKey());
////                api.caughtPokemon(pokemonFirebase,"id2");
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onPokemonRemoved(PokemonFirebase pokemonFirebase) {
//                pokemons.remove(pokemonFirebase.getName());
//                adapter.notifyDataSetChanged();
//            }
//        });

        api.setOnPokemonInBagChangedListener(new OnPokemonInBagChangedListener() {
            @Override
            public void onPokemonInBagAdded(PokemonFirebase pokemonFirebase) {
                pokemons.add(pokemonFirebase.getName());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPokemonInBagRemoved(PokemonFirebase pokemonFirebase) {

            }
        });
//        api.getPokemon();
        api.getPokemonInBag("id1");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.createUser(editText.getText().toString(),"name");
            }
        });


    }
}
