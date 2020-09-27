package pe.edu.upc.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prototype_pokemon.view.*
import org.jetbrains.anko.image
import pe.edu.upc.pokedex.R
import pe.edu.upc.pokedex.database.AppDatabase
import pe.edu.upc.pokedex.model.Pokemon

class PokemonPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindTo(pokemon: Pokemon) {
        itemView.tvPokemon.text = pokemon.name
        Picasso.get()
            .load(pokemon.image)
            .into(itemView.ivPokemon);
    }
}

class PokemonAdapter(private val pokemons: List<Pokemon>): RecyclerView.Adapter<PokemonPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_pokemon, parent, false)
        return PokemonPrototype(view)
    }

    override fun onBindViewHolder(holder: PokemonPrototype, position: Int) {
        pokemons[position].orden = position + 1
        pokemons[position].image =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+
                    (pokemons[position].orden).toString()+".png"
        holder.bindTo(pokemons[position])
        holder.itemView.btAddFavorites.setOnClickListener {
            AppDatabase.getInstance(holder.itemView.context).getDao().insert(pokemons[position])
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

}