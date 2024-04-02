package br.com.alura.alugames.modelo

data class Jogo(
    val titulo: String,
    val capa: String,
    var informacao: String
) {

    var descricao:String = informacao


    override fun toString(): String {
        return "\nJogo: \n" +
                "Titulo: $titulo \n" +
                "Capa:  $capa \n" +
                "Descricao: $descricao\n"
    }

}