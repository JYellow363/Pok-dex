package pe.edu.upc.pokedex.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.pokedex.model.Pokemon

@Dao
interface PokemonDao {

    @Query("select * from pokemon")
    fun getAll() : List<Pokemon>

    @Insert
    fun insert(vararg jokes: Pokemon)

    @Delete
    fun delete(vararg jokes: Pokemon)
}