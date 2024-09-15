import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.transformarEmIdade() : Int {
    val formatter = DateTimeFormatter.ofLocalizedPattern("dd/MM/yyy")
    var idade = 0
    val dataNascimento = LocalDate.parse(this, formatter)
    val hoje = LocalDate.now()

    idade = Period.between(dataNascimento, hoje).years

    return idade
}