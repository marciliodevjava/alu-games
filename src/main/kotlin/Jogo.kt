class Jogo(val titulo: String, val capa: String, informacao: String) {

    val descricao = informacao

    override fun toString(): String {
        return "Meu Jogo: \n" +
                "Titulo: $titulo \n" +
                "Capa:  $capa \n" +
                "Descricao: $descricao"
    }

}