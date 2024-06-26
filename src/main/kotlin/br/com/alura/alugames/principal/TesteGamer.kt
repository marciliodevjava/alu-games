import br.com.alura.alugames.modelo.Gamer

fun main() {
    val gamer1 = Gamer(
        "Jacque",
        "jacque@email.com"
    )
    print(gamer1)

    val gamer2 = Gamer(
        "Jeni",
        "jeni@email.com",
        "19/19/1992",
        "jeniblo"
    )
    print(gamer2)

    gamer1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario = "jacqueskywalker"
    }.also {
        print(gamer1.idInterno)
    }

    print(gamer1)
    gamer1.usuario = "jacque"
    print(gamer1)
}