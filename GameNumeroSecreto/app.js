let listaNumerosSorteados = []
let numeroLimite = 10
let numeroMinimo = 1
let numeroMaximo = 10
let numeroSecreto = gerarNumeroAleatorio()
let numeroTentativas = 1

function exibirTextoNaTela(tag, texto){
    let campo = document.querySelector(tag)
    campo.innerHTML = texto
    responsiveVoice.speak(texto, 'Brazilian Portuguese Female', {rate:1.2})
}

function exibirMensagemInicial(){
    exibirTextoNaTela('h1', 'Jogo do número secreto')
    exibirTextoNaTela('p', `Informe um número entre ${numeroMinimo} e ${numeroMaximo}:`)
}

exibirMensagemInicial();

function verificarChute() {
    let numeroChute = document.querySelector('input').value

    if (numeroChute == numeroSecreto ) {
        exibirTextoNaTela('h1', 'Acertou!')

        let palavraTentativa = numeroTentativas > 1 ? 'tentativas': 'tentativa'
        let mensagemTentativas = `Parabéns! Você descobriu o número secreto com ${numeroTentativas} ${palavraTentativa}.`

        exibirTextoNaTela('p', mensagemTentativas)

        document.getElementById('reiniciar').removeAttribute('disabled')
    } else {
        if (numeroChute > numeroSecreto) {
            exibirTextoNaTela('p', `Tente novamente! O número secreto é menor que ${numeroChute}.`)
        } else {
            exibirTextoNaTela('p', `Tente novamente! O número secreto é maior que ${numeroChute}.`)
        }

        numeroTentativas ++
        limparCampo()
    }
}

function gerarNumeroAleatorio() {
    let numeroGerado = parseInt(Math.random() * numeroLimite + 1)
    let quantidadeItensLista = listaNumerosSorteados.length

    if (quantidadeItensLista == numeroLimite) {
        listaNumerosSorteados = []
    }

    if (listaNumerosSorteados.includes(numeroGerado)) {
        return gerarNumeroAleatorio()
    } else {
        listaNumerosSorteados.push(numeroGerado)
        console.log(listaNumerosSorteados)
        return numeroGerado
    }
}

function limparCampo() {
    numeroChute = document.querySelector('input')
    numeroChute.value = ''
}

function reiniciarJogo() {
    numeroSecreto = gerarNumeroAleatorio()
    limparCampo()
    numeroTentativas = 1
    exibirMensagemInicial()
    document.getElementById('reiniciar').setAttribute('disabled', true)
}