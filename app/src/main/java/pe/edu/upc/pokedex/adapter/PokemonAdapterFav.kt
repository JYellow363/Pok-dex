package pe.edu.upc.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prototype_pokemon.view.*
import kotlinx.android.synthetic.main.prototype_pokemon.view.tvPokemon
import kotlinx.android.synthetic.main.prototype_pokemon_fav.view.*
import org.jetbrains.anko.image
import pe.edu.upc.pokedex.R
import pe.edu.upc.pokedex.database.AppDatabase
import pe.edu.upc.pokedex.model.Pokemon
import pe.edu.upc.pokedex.util.UpdateFavoritesListener

class PokemonPrototypeFav(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindTo(pokemon: Pokemon) {
        itemView.tvPokemonFav.text = pokemon.name
        Picasso.get()
            .load(pokemon.image)
            .into(itemView.ivPokemonFav);
    }
}

class PokemonAdapterFav(private val pokemons: List<Pokemon>,
                        private val updateFavoritesListener: UpdateFavoritesListener):
    RecyclerView.Adapter<PokemonPrototypeFav>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPrototypeFav {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_pokemon_fav, parent, false)
        return PokemonPrototypeFav(view)
    }

    override fun onBindViewHolder(holder: PokemonPrototypeFav, position: Int) {
        holder.bindTo(pokemons[position])
        holder.itemView.btRemove.setOnClickListener {
            AppDatabase.getInstance(holder.itemView.context).getDao().delete(pokemons[position])
            updateFavoritesListener.updateFavorites()
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

}