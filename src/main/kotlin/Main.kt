import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


fun main(args: Array<String>) {

    val leitura = Scanner(System.`in`)
    print("Digite um id para buscar: ")
    val id = leitura.nextLine()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client.send(request, BodyHandlers.ofString())

//    try {
//        var json = response.body()
//        val gson = Gson()
//        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
//        val meuJogo = Jogo(
//            meuInfoJogo.info.title, meuInfoJogo.info.thumb, meuInfoJogo.info.steamAppID
//        )
//        print(meuJogo)
//    } catch (ex: Exception) {
//        print("Jogo não encontrado. Tente outro id.")
//    }

    var meuJogo:Jogo? = null

    val resultado = runCatching {
        var json = response.body()
        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb, meuInfoJogo.info.steamAppID)
        print(meuJogo)
    }

    resultado.onFailure {
        print("Jogo não encontrado. Tente outro id.")
    }

    resultado.onSuccess {
        print("Você que inserir uma descrição personalizada? S/N: ")
        val descri = leitura.nextLine()
        if (descri.equals("S", ignoreCase = true)){
            print("Insira a descição do filme pesonalizada que vc deseja inserir: ")
            val ds = leitura.nextLine()
            meuJogo?.descricao = ds
            print(meuJogo)
        } else {
            meuJogo?.descricao = meuJogo?.titulo.toString()
            print(meuJogo)
        }
    }
}