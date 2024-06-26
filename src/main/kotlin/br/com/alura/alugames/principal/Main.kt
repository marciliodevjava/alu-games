package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsulmoApi
import transformarIdade
import java.util.*


fun main(args: Array<String>) {

    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concuildo com sucesso. Dados do Gamer:")
    println(gamer)
    println("Idade do Gammer: " + gamer.dataNascimento?.transformarIdade())

    do {
        print("Digite um id para buscar: ")
        val id = leitura.nextLine()

        val buscarApi = ConsulmoApi()
        val informacaoJogo = buscarApi.buscarApi(id)
        var meuJogo: Jogo? = null

        val resultado = runCatching {
            if (informacaoJogo != null) {
                meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb, informacaoJogo.info.steamAppID)
                print(meuJogo)
            } else {
                return throw IllegalArgumentException("Jogo não encontrado")
            }
        }

        resultado.onFailure {
            println("Jogo não encontrado. Tente outro id.")
        }
        resultado.onSuccess {
            print("Você que inserir uma descrição personalizada? S/N: ")
            val descri = leitura.nextLine()
            if (descri.equals("S", ignoreCase = true)) {
                print("Insira a descição do filme pesonalizada que vc deseja inserir: ")
                val ds = leitura.nextLine()
                meuJogo?.descricao = ds
            } else {
                meuJogo?.descricao = meuJogo?.titulo.toString()
            }
            gamer.jogosBuscados.add(meuJogo)
        }

        print("Deseja continuar? S/N: ")
        val resposta = leitura.nextLine()
    } while (resposta.equals("S", ignoreCase = true))
    println("Jogos buscados: ")
    println(gamer.jogosBuscados)

    println("\nJogos por titulos: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("Batman", ignoreCase = true) ?: false
    }

    println("Jogos Filtrados")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista? S/N")
    val respota = leitura.nextLine()
    if (respota.equals("S", ignoreCase = true)) {
        do {
            println(gamer.jogosBuscados)
            println("Informe a posição do jogo que deseja excluir: ")
            val posicao = leitura.nextInt()
            val interador = posicao - 1
            gamer.jogosBuscados.removeAt(interador)
            println("Deseja excluir outro jogo? S/N")
            val ex = leitura.nextLine()
        } while (ex.equals("S", ignoreCase = true))
    }

    println("Jogos buscados: ")
    println(gamer.jogosBuscados)
    print("Buscar finalizada com sucesso.")
}