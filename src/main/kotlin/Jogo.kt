import com.google.gson.annotations.SerializedName

class Jogo(
    @SerializedName("title") val titulo: String,
    @SerializedName("thumb") val capa: String,
    @SerializedName("steamAppID") var informacao: String
) {

    val descricao = informacao

    override fun toString(): String {
        return "\nMeu Jogo: \n" +
                "Titulo: $titulo \n" +
                "Capa:  $capa \n" +
                "Descricao: $descricao\n"
    }

}