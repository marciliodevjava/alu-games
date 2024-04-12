package br.com.alura.alugames.principal

import br.com.alura.alugames.servicos.ConsulmoApi


fun main() {
    val consulmo = ConsulmoApi()
    val listaGamers = consulmo.buscaGamers()
    println(listaGamers)
}
