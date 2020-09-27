package pe.edu.upc.pokedex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_favorites.*
import pe.edu.upc.pokedex.R
import pe.edu.upc.pokedex.adapter.PokemonAdapter
import pe.edu.upc.pokedex.adapter.PokemonAdapterFav
import pe.edu.upc.pokedex.database.AppDatabase
import pe.edu.upc.pokedex.model.Pokemon
import pe.edu.upc.pokedex.util.UpdateFavoritesListener

class FavoritesFragment : Fragment(), UpdateFavoritesListener {

    private lateinit var pokemons: List<Pokemon>
    lateinit var pokemonAdapterFav: PokemonAdapterFav

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        loadItems()
    }

    private fun loadItems() {
        pokemons = AppDatabase.getInstance(context!!).getDao().getAll()
        pokemonAdapterFav = PokemonAdapterFav(pokemons, this)
        rvFavorites.adapter = pokemonAdapterFav
        rvFavorites.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun updateFavorites(){
        loadItems()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FavoritesFragment()
    }
}