package pe.edu.upc.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Pokemon(
    @PrimaryKey(autoGenerate = true)
    var uid: Int?,

    @ColumnInfo(name = "orden")
    @SerializedName("orden")
    var orden: Int?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    var image: String?
)