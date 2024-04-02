package br.com.alura.alugames.modelo

import java.util.*
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {
    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }
    var idInterno: String? = null
        private set

    var jogosBuscados = mutableListOf<Jogo>()

    constructor(nome: String, email: String, dataNAscimento: String, usuario: String) :
            this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    init {
        if (nome.isNullOrBlank()) {
            throw IllegalArgumentException("Nome está em branco.")
        }
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)\n"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)
        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("email inválido.")
        }
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer o seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite o seu e-mail: ")
            val email = leitura.nextLine()
            println("Deseja completar o seu cadastro com usuário e data de nascimento? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("S", ignoreCase = true)) {
                println("Digite a sua data de nascimento: ")
                val data = leitura.nextLine()
                println("Digite o seu nome de usuário:")
                val usuario = leitura.nextLine()
                return Gamer(nome, email, data, usuario)
            }
            return Gamer(nome, email)
        }
    }
}
