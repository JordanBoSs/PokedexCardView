package com.jordan.pokedexcardview.adaptadores;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jordan.pokedexcardview.R;
import com.jordan.pokedexcardview.modelos.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Pokemon> ListaPokemon;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    public Adaptador(List<Pokemon> pokemon, int layout, Activity activity, OnItemClickListener listener) {
        this.ListaPokemon = pokemon;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(ListaPokemon.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return ListaPokemon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        //lo que veremos en la card view
        public TextView TvNombre, TvDescripcion, TvCantidad;
        public ImageView IvFondo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvNombre = (TextView) itemView.findViewById(R.id.textViewName);
            TvCantidad = (TextView) itemView.findViewById(R.id.textViewQuantity);
            TvDescripcion = (TextView) itemView.findViewById(R.id.textViewDescription);
            IvFondo = (ImageView) itemView.findViewById(R.id.imageViewBackground);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.delete:
                    // Observa que como estamos dentro del adaptador, podemos acceder
                    // a los métodos propios de él como notifyItemRemoved o notifyItemChanged
                    ListaPokemon.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reiniciar:
                    ListaPokemon.get(getAdapterPosition()).reiniciarCantidad();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            // Recogemos la posición con el método getAdapterPosition
            Pokemon pokemonSeleccionado = ListaPokemon.get(this.getAdapterPosition());
            // Establecemos título e icono para cada elemento, mirando en sus propiedades
            contextMenu.setHeaderTitle(pokemonSeleccionado.getNombre());
            contextMenu.setHeaderIcon(pokemonSeleccionado.getImgnIcon());
            // Inflamos el menú
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu, contextMenu);
            // Por último, añadimos uno por uno, el listener onMenuItemClick para
            // controlar las acciones en el contextMenu, anteriormente lo manejábamos
            // con el método onContextItemSelected en el activity
            for (int i = 0; i < contextMenu.size(); i++)
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }

        public void bind(final Pokemon pokemon, final OnItemClickListener listener) {

            this.TvNombre.setText(pokemon.getNombre());
            this.TvDescripcion.setText(pokemon.getDescripcion());
            this.TvCantidad.setText(pokemon.getCantidad() + "");
            // Lógica aplicada para la limitación de la cantidad en cada elemento fruta
            if (pokemon.getCantidad() == pokemon.MAXIMO) {
                TvCantidad.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
                TvCantidad.setTypeface(null, Typeface.BOLD);
            } else {
                TvCantidad.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
                TvCantidad.setTypeface(null, Typeface.NORMAL);
            }
            // Cargamos la imagen con Picasso
            Picasso.with(activity).load(pokemon.getImgnFondo()).fit().into(this.IvFondo);
            // Añadimos el listener click para cada elemento fruta
            this.IvFondo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(pokemon, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Pokemon pokemon, int position);
    }
}
