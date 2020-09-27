package pe.edu.upc.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    @SerializedName("count")
    var count:Int,

    @SerializedName("next")
    var next:String,

    @SerializedName("previous")
    var previous:String,

    @SerializedName("results")
    var results: List<Pokemon>
)