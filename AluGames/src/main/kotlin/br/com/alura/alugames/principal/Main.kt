package br.com.alura.alugames.principal
import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro realizado com sucesso! Dados cadastrados:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite o código do jogo para buscar:")
        val busca = leitura.nextLine()
        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }

        resultado.onFailure{
            println("br.com.alura.alugames.modelo.Jogo inexistente. Tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")

            val opcao = leitura.nextLine()

            if (opcao.equals("S", true)){
                println("Insira a descrição personalizada para o jogo")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("S", true))

    println("Jogos buscado:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por título: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Título: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }

    println("\n Jogos filtrados: ")
    println(jogosFiltrados)

    println("\n Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()

    if (opcao.equals("s", true)) {
        println(gamer.jogosBuscados)

        println("\n Informe a posição do jogo que deseja excluir: ")
        val position = leitura.nextInt()

        gamer.jogosBuscados.removeAt(position)
    } else {
        println(gamer.jogosBuscados)
    }

    println("\n Lista atualizada: ")
    println(gamer.jogosBuscados)

    println("Busca realizada com sucesso!")
}