package pe.edu.upc.pokedex.api

import pe.edu.upc.pokedex.model.Pokemon
import pe.edu.upc.pokedex.model.PokemonsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("api/v2/pokemon?offset=0&limit=1050")
    fun getPokemon(): Call<PokemonsResponse>
}