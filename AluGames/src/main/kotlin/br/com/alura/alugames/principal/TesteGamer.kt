import br.com.alura.alugames.modelo.Gamer
import kotlin.random.Random

fun main () {
    val gamer1 = Gamer("Jacque", "jack@email.com")
    println(gamer1);

    val gamer2 = Gamer("Jeny", "jeny@gmail.com", "18/09/1992", "jenyblo")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "20/02/2000"
        it.usuario = "jacSkyWalker"

    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "jacque"

    println(gamer1)
}