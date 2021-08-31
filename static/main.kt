import kotlinx.browser.*
import org.w3c.dom.*


@JsName("jogar")
fun jogar() {
    println("funfando")
    val board = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))
    val tabuleiro = document.getElementById("tabuleiro")
    if (tabuleiro!= null)
        tabuleiro.innerHTML = """
        <html>
        <table>
                <tr>
                    <td><button id="b00" onclick="main.botaoPressionado(id)" value="0">00</button></td>
                    <td><button id="b01" onclick="main.botaoPressionado(id)" value="0">01</button></td>
                    <td><button id="b02" onclick="main.botaoPressionado(id)" value="0">02</button></td>
                </tr>
                <tr>
                    <td><button id="b10" onclick="main.botaoPressionado(id)" value="0">10</button></td>
                    <td><button id="b11" onclick="main.botaoPressionado(id)" value="0">11</button></td>
                    <td><button id="b12" onclick="main.botaoPressionado(id)" value="0">12</button></td>
                </tr>
                <tr>
                    <td><button id="b20" onclick="main.botaoPressionado(id)" value="0">20</button></td>
                    <td><button id="b21" onclick="main.botaoPressionado(id)" value="0">21</button></td>
                    <td><button id="b22" onclick="main.botaoPressionado(id)" value="0">22</button></td>
                </tr>
            </table>
        </html> 
        """
}

var testeglobal: Int = 1 // Sendo usada pra verificar a vez, depois trocar pelo nome do jogador

@JsName("botaoPressionado")
fun botaoPressionado(id:String, board: Array<Array<Int>>){
    val botao = document.getElementById(id) as HTMLButtonElement 
    if (botao != null && botao.disabled == false)  // deixei a verificacao de nulo pq tava dando uns bugs
        if(testeglobal == 1){
            botao.innerHTML = "X"
            val s: String = id.substring(1)
            
            testeglobal -= 1
            botao.disabled = true // desabilitar o botão
        } else {
            botao.innerHTML = "O"
            botao.value = "-1"
            testeglobal += 1
            botao.disabled = true // desabilitar o botão 
        }
    //verifica()
}

@JsName("verifica")
fun verifica(){
    //Hipótese: Atribuir o value dos botões à posição correspondente do tabuleiroArray, 
    //e depois somar o valor das linhas, colunas e diagonais dele. Ou seja, se o valor
    //de qualquer uma delas for 3, o jogador 1 vence, se for -3, o jogador 2 vence.
    //talvez eu esteja complicando demais e baste passar o array direto
}
