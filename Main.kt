//baseado em investigaçao postuma
import java.util.Scanner

//Estrutura para representar os suspeitos
data class Suspeito(
    val nome: String,
    val alibi: String,
    val fichaCriminal: String? //pode ser nulo se nao tiver antecendentes
)

fun main() {
    var pontosEvidencia = 0
    var jogoAtivo = true

    //Criando suspeitos (um deles nao possui ficha criminal ou seja null)
    val suspeitos = listOf(
        Suspeito("Vírgilia", "Estava em meu casarão com Lobos Neves", null),
        Suspeito("Quincas Borba", "Filosofando sobre o Humanitismo na praia", "Pertubação de ordem e insanidade pública"),
        Suspeito("Capitu", "Estava em casa cuidando dos olhos ressaca", "Suspeita de adultério e dissimulação sob investigação")
    )

    println("--- A INVESTIGAÇÃO PÓSTUMA (RIO NOIR- 1937)---")
    println("Você recebeu uma carta intrigante do próprio Brás Cubas... escrita antes de sua própria morte")
    println("Ele quer quue você descubra quem o assasinou nas ruas do Rio de Janeiro.\n")

    while (jogoAtivo){
        println("Escolha sua proxima ação:")
        println("1. Investigar o local da morte (Beco Carioca)")
        println("2. Interrogar suspeitos machadinos")
        println("3. Consultar o Bloco de Notas")
        println("4. Conversar com Brás Cubas no Limbo (Dar o Veredito)")
        println("digite a opção:")

        val opcao = readlnOrNull()

        when(opcao){
            "1" -> pontosEvidencia = investigarCena (pontosEvidencia)
            "2" -> interrogarSuspeitos (suspeitos, pontosEvidencia)
            "3" -> exibirStatus(pontosEvidencia)
            "4" ->{
                jogoAtivo= false
                encerrarCaso(pontosEvidencia)
            }
            else -> println("Concentre-se, detetive! O tempo esta correndo.\n")


        }

    }
}

fun investigarCena (evidenciasAtuais: Int): Int{
    println("\n Você caminha sob a neblina do Rio antigo...")
    println("5. Procurar por um frasco de remédio ou veneno perto do corpo (+30 Evidências)")
    println("6. Examinar o empalastro experimental de Brás Cubas abandodnado (+60 Evidências)")
    println("Oque desejas examinar ?")

    val escolha = readlnOrNull()
    var novasEvidencias = 0

    if(escolha == "5"){
        novasEvidencias= 30
        println("Você encontrou vestígios de uma subistância suspeita")
    } else if (escolha == "6"){
        novasEvidencias= 60
        println("O famoso Emplastro Brás Cubas foi sabotado com toxinas arteriais !")
    }else{
        println("Você perdeu tempo observando o movimento dos bondes e não achou nada")
    }

    val total= evidenciasAtuais + novasEvidencias
    println("Evidencias coletadas! Total atual: $total pontos. \n")
    return total
}
fun interrogarSuspeitos(listaSuspeitos: List<Suspeito>, evidencias: Int){
    println("\n Selecione um suspeito para pressionar:")
    for ((index, suspeito) in listaSuspeitos.withIndex()) {
        println("${index +1}. ${suspeito.nome}")

    }
    println("Quem deseja interrogar ?")
    val escolhaIdx = readlnOrNull()?.toIntOrNull()

    if (escolhaIdx == null || escolhaIdx < 1|| escolhaIdx > listaSuspeitos.size){
        println("Esse suspeito não está na lista de hoje. \n")
        return
    }

    val alvo = listaSuspeitos [escolhaIdx - 1]
    println("\n Iniciando interrogatório com ${alvo.nome}...")
    println("Álibi: \"${alvo.alibi}\"")

    //Null Safety manipulando suspeitos com ficha limpa (como Vírgilia)
    val antecedentes= alvo.fichaCriminal?.uppercase()?: "SEM ANTECEDENTES. ALTA SOCIEDADE CARIOCA"
    println("Ficha Crimial: $antecedentes")

    //Condicionais cruzadas caso a evidência seja maior que 80
    if (evidencias> 80 && alvo.fichaCriminal != null) {
        println("AVISO CONTRADIÇÃO DETECTADA! Com $evidencias pontos de evidências e o historico de '${alvo.fichaCriminal}',o álibi desmonorou! O culpado mentiu!")
    } else if (evidencias > 80 && alvo.fichaCriminal ==null) {
        println("AVISO Você tem muitas provas ($evidencias potnos), mas a influência e ficha limpa de ${alvo.nome} tornam a acusação direta difícil sem uma testuma ocular")
    } else{
        println("Evidencias insuficientes (Abaixo de 80 pontos). O suspeito responde com ironia machadiana e mantém o álibi intacto")
    }
    println()

}

fun exibirStatus(evidencias: Int){
    println("\n--- Quadro de Investigação---")
    println("Pontos de Evidência: $evidencias / 100")
    println(if (evidencias>= 80) "Status: pronto para desvendar a contradição" else "Status: Busque maiss pistas no Beco")
    println("----------------------------\n")
}

fun encerrarCaso (evidencias: Int){
    println("\n Você entra no Limbo para se encontrar com o espiríto do defunto autor...")
    if (evidencias >= 90) {
        println("Brás Cubas aplude ironicamente: 'Você resolveu o mistério, detetive! Posso descansar em paz sem deixar esse segredo aos vermes'Você venceu o jogo")
    }else {
        println("Brás Cubas suspira :'As pistas não batem... Você falhou em quebrar o ciclo'")
        println("AVISO: O LOOP TEMPORAL REINICIA! Você acorda às 9h da manhã do mesmo dia...")
    }
    println("Fim da sessão")
}

