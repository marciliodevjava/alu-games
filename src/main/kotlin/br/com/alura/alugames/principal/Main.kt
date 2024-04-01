package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsulmoApi
import java.util.*


fun main(args: Array<String>) {

    val leitura = Scanner(System.`in`)
    print("Digite um id para buscar: ")
    val id = leitura.nextLine()

    val buscarApi = ConsulmoApi()
    val informacaoJogo = buscarApi.buscarApi(id)
    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb, informacaoJogo.info.steamAppID)
        print(meuJogo)
    }

    resultado.onFailure {
        print("br.com.alura.alugames.modelo.Jogo não encontrado. Tente outro id.")
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
        print(meuJogo)
    }
    resultado.onSuccess {
        print("Buscar finalizada com sucesso.")
    }
}