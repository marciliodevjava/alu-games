package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoGameJson
import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsulmoApi {

    fun buscarApi(id: String): InfoJogo? {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()


        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        var json = response.body()
        val gson = Gson()
        if (response.statusCode() == 404) return null
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        return meuInfoJogo
    }

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

        listaGamer.map {
            infoGameJson -> Gamer(infoGameJson.nome, infoGameJson.email, infoGameJson.dataNascimento,infoGameJson.usuario)
        }
        return listaGamer
    }
}