import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers


fun main(args: Array<String>) {
    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=146"))
        .build()

    val response = client.send(request, BodyHandlers.ofString())
    var json = response.body()

    //   print(json)
    val gson = Gson();

    val meuJogo = Jogo(
        "Batman: Arkham Asylum Game of the Year Edition",
        "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705",
        "Jogo de PC"
    )

    print(meuJogo)
    print("                      ")
    val novoJogo = Jogo(
        capa = "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705",
        informacao = "Jogo de PC",
        titulo = "Batman: Arkham Asylum Game of the Year Edition"
    )

    print(novoJogo)
}