package br.com.alura.alugames.modelo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

data class InfoGameJson(
    val nome: String,
    val email: String,
    val dataNascimento: String,
    val usuario: String
) {

    fun buscaGamers(): List<InfoGameJson> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())


        val json = response.body()
        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoGameJson>>() {}.type

        val listaGamer: List<InfoGameJson> = gson.fromJson(json, meuGamerTipo)
        return listaGamer
    }
}
