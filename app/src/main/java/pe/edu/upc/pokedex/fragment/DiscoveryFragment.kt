package pe.edu.upc.pokedex.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_discovery.*
import pe.edu.upc.pokedex.MainActivity.Companion.TAG
import pe.edu.upc.pokedex.R
import pe.edu.upc.pokedex.adapter.PokemonAdapter
import pe.edu.upc.pokedex.api.PokemonApi
import pe.edu.upc.pokedex.database.AppDatabase
import pe.edu.upc.pokedex.model.Pokemon
import pe.edu.upc.pokedex.model.PokemonsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscoveryFragment : Fragment() {

    private lateinit var pokemons: List<Pokemon>
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        loadItems()
    }

    private fun loadItems() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi = retrofit.create(PokemonApi::class.java)
        val request = pokeApi.getPokemon()

        request.enqueue(object : Callback<PokemonsResponse> {
            override fun onResponse(
                call: Call<PokemonsResponse>,
                response: Response<PokemonsResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemonsResponse = response.body()
                    pokemons = pokemonsResponse!!.results
                    pokemonAdapter = PokemonAdapter(pokemons)
                    rvDiscovery.adapter = pokemonAdapter
                    rvDiscovery.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<PokemonsResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }
        }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DiscoveryFragment()
    }
}