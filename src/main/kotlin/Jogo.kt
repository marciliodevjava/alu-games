data class Jogo(
    val titulo: String,
    val capa: String,
    var informacao: String
) {

    val descricao = informacao

    override fun toString(): String {
        return "\nMeu Jogo: \n" +
                "Titulo: $titulo \n" +
                "Capa:  $capa \n" +
                "Descricao: $descricao\n"
    }

}