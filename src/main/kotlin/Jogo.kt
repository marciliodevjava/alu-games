data class Jogo(
    val titulo: String,
    val capa: String,
    var informacao: String
) {

    var descricao:String = informacao


    override fun toString(): String {
        return "\nMeu Jogo: \n" +
                "Titulo: $titulo \n" +
                "Capa:  $capa \n" +
                "Descricao: $descricao\n"
    }

}