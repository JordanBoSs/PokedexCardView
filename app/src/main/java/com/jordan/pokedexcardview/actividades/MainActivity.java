package com.jordan.pokedexcardview.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jordan.pokedexcardview.R;
import com.jordan.pokedexcardview.adaptadores.Adaptador;
import com.jordan.pokedexcardview.modelos.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adaptador adapter;

    private List<Pokemon> ListaPokemon;
    private int countador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ListaPokemon = this.getAllPokemon();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        // Observa como pasamos el activity, con this. Podríamos declarar
        // Activity o Context en el constructor y funcionaría pasando el mismo valor, this
        adapter = new Adaptador(ListaPokemon, R.layout.recycler_view_item, this, new Adaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon, int position) {
                pokemon.addCantidad(1);
                adapter.notifyItemChanged(position);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        // No registramos para el menu contexto nada aquí, lo movemos al ViewHolder del adaptador
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.agregar:
                // Rescatamos el número de frutas para saber en que posición insertaremos
                int position = ListaPokemon.size();
                ListaPokemon.add(position, new Pokemon("Pokemon " + (++countador), "Pokemon agregado por el usuario", R.mipmap.logo, R.mipmap.logo, 0));
                adapter.notifyItemInserted(position);
                layoutManager.scrollToPosition(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Pokemon> getAllPokemon() {
        List<Pokemon> lista = new ArrayList<Pokemon>(){{
            add(new Pokemon("Bulbasaur", "Planta", R.mipmap.bulbasaur, R.mipmap.bulbasaur, 0));
            add(new Pokemon("Charmander", "Fuego", R.mipmap.charmander, R.mipmap.charmander, 0));
            add(new Pokemon("Squirtle", "Agua", R.mipmap.squirtle, R.mipmap.squirtle , 0));
            add(new Pokemon("Chikorita", "Planta", R.mipmap.chikorita, R.mipmap.chikorita , 0));
            add(new Pokemon("Cyndaquil", "Fuego", R.mipmap.cyndaquil, R.mipmap.cyndaquil , 0));
            add(new Pokemon("Totodile", "Agua", R.mipmap.totodile, R.mipmap.totodile , 0));
            add(new Pokemon("Treecko", "Planta", R.mipmap.treecko, R.mipmap.treecko , 0));
            add(new Pokemon("Torchic", "Fuego", R.mipmap.torchic, R.mipmap.torchic , 0));
            add(new Pokemon("Mudkip", "Agua", R.mipmap.mudkip, R.mipmap.mudkip , 0));
            add(new Pokemon("Turtwig", "Planta", R.mipmap.turtwig, R.mipmap.turtwig , 0));
            add(new Pokemon("Chimchar", "Fuego", R.mipmap.chimchar, R.mipmap.chimchar , 0));
            add(new Pokemon("Piplup", "Agua", R.mipmap.piplup, R.mipmap.piplup , 0));
            add(new Pokemon("Snivy", "Planta", R.mipmap.snivy, R.mipmap.snivy , 0));
            add(new Pokemon("Tepig", "Fuego", R.mipmap.tepig, R.mipmap.tepig , 0));
            add(new Pokemon("Oshawott", "Agua", R.mipmap.oshawott, R.mipmap.oshawott , 0));
            add(new Pokemon("Chespin", "Planta", R.mipmap.chespin, R.mipmap.chespin , 0));
            add(new Pokemon("Fennekin", "Fuego", R.mipmap.fennekin, R.mipmap.fennekin , 0));
            add(new Pokemon("Frokie", "Agua", R.mipmap.froakie, R.mipmap.froakie , 0));
            add(new Pokemon("Rowlet", "Planta", R.mipmap.rowlet, R.mipmap.rowlet , 0));
            add(new Pokemon("Litten", "Fuego", R.mipmap.litten, R.mipmap.litten , 0));
            add(new Pokemon("Popplio", "Agua", R.mipmap.popplio, R.mipmap.popplio , 0));
            add(new Pokemon("Grookey", "Planta", R.mipmap.grookey, R.mipmap.grookey , 0));
            add(new Pokemon("Scorbunny", "Fuego", R.mipmap.scorbunny, R.mipmap.scorbunny , 0));
            add(new Pokemon("Sobble", "Agua", R.mipmap.sobble, R.mipmap.sobble , 0));
        }};
        return lista;
    }
}