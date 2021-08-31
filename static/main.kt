import kotlinx.browser.*
import org.w3c.dom.*


val board = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))

@JsName("jogar")
fun jogar() {
    val status = document.getElementById("status")
    if(status != null)
        status.innerHTML = "Vez de jogador 1(X)"
    val tabuleiro = document.getElementById("tabuleiro")
    if (tabuleiro!= null)
        tabuleiro.innerHTML = """
        <html>
        <table>
                <tr>
                    <td><button class="bts" id="b00" onclick="main.botaoPressionado(id)" value="0">00</button></td>
                    <td><button class="bts" id="b01" onclick="main.botaoPressionado(id)" value="0">01</button></td>
                    <td><button class="bts" id="b02" onclick="main.botaoPressionado(id)" value="0">02</button></td>
                </tr>
                <tr>
                    <td><button class="bts" id="b10" onclick="main.botaoPressionado(id)" value="0">10</button></td>
                    <td><button class="bts" id="b11" onclick="main.botaoPressionado(id)" value="0">11</button></td>
                    <td><button class="bts" id="b12" onclick="main.botaoPressionado(id)" value="0">12</button></td>
                </tr>
                <tr>
                    <td><button class="bts" id="b20" onclick="main.botaoPressionado(id)" value="0">20</button></td>
                    <td><button class="bts" id="b21" onclick="main.botaoPressionado(id)" value="0">21</button></td>
                    <td><button class="bts" id="b22" onclick="main.botaoPressionado(id)" value="0">22</button></td>
                </tr>
            </table>
        </html> 
        """
}

var testeglobal: Int = 1 // Sendo usada pra verificar a vez, depois trocar pelo nome do jogador

@JsName("botaoPressionado")
fun botaoPressionado(id:String){
    val status = document.getElementById("status")
    if(status != null)
        if(testeglobal == 1)
            status.innerHTML = "Vez de jogador 2(O)"
        else    
            status.innerHTML = "Vez de jogador 1(X)"
    val botao = document.getElementById(id) as HTMLButtonElement 
    if (botao.disabled == false)  // deixei a verificacao de nulo pq tava dando uns bugs
        if(testeglobal == 1){
            botao.innerHTML = "X"
            board[id[1].code- 48][id[2].code - 48] = 1
            testeglobal -= 1
            botao.disabled = true // desabilitar o botão
        } else {
            botao.innerHTML = "O"
            board[id[1].code - 48][id[2].code - 48] = -1
            testeglobal += 1
            botao.disabled = true // desabilitar o botão 
        }
    if(fimDeJogo()){
        desabilitaBts(0)
        val restart = document.getElementById("botaoRestart")
        if(restart != null)
            restart.innerHTML = """
                <button onclick="main.resetaBoard(0, 0)">Jogar novamente</button>
            """
    }
}

@JsName("resetaBoard")
fun resetaBoard(linha: Int, col: Int){
    if(col <= 2 && linha <=2){
        board[linha][col] = 0
        resetaBoard(linha + 1, col) 
    }
    else 
    	if(col <= 2) {
        	resetaBoard(0, col + 1) 
    } 
    jogar()
}
@JsName("fimDeJogo")
fun fimDeJogo(): Boolean{
    val status = document.getElementById("status")
    if(verifica()){       
        if(status != null){
            val s = status.innerHTML.split('(') 
            if(s[1][0].equals('X'))
                status.innerHTML = "O jogador 2 venceu!"
            else
                status.innerHTML = "O jogador 1 venceu!"
        }
        return true
    } else {
        if(verificaVelha(0)) {
            if(status != null)
                status.innerHTML = "Deu velha!"
        return true
        }
    }
    return false
}

@JsName("verifica")
fun verifica(): Boolean{
    if(verificaDiagonais())
        return true
    if(verificaLinhas(0, 0, 0))
        return true
    if(verificaColunas(0, 0, 0))
        return true
    return false
}

@JsName("verificaLinhas")
fun verificaLinhas(linha: Int, col: Int, x: Int): Boolean {
    if(x == 3 || x == -3)
        return true
    if(col <= 2 && linha <=2){
        val r = board[linha][col]
        return verificaLinhas(linha, col + 1, r + x) 
    }
    else if(linha <= 2) {
        return verificaLinhas(linha + 1, 0, 0) 
    } 
    return false
}

@JsName("verificaColunas")
fun verificaColunas(linha: Int, col: Int, x: Int): Boolean {
    if(x == 3 || x == -3)
        return true
    if(col <= 2 && linha <=2){
        val r = board[linha][col]
        return verificaColunas(linha + 1, col, r + x) 
    }
    else 
    	if(col <= 2) {
        	return verificaColunas(0, col + 1, 0) 
    } 
    return false
}

@JsName("verificaDiagonais")
fun verificaDiagonais(): Boolean {
    val d1: Int = board[0][0] + board[1][1] + board[2][2]
    val d2: Int = board[0][2] + board[1][1] + board[2][0]
    if(d1 == 3 || d1 == -3 || d2 == 3 || d2 == -3)
    	return true
    return false
}

@JsName("verificaVelha")
fun verificaVelha(i: Int): Boolean {    
    if(i < 9){
        val doc = document.getElementsByClassName("bts").item(i) 
        if(doc != null)
            if(doc.innerHTML != "X" && doc.innerHTML != "O")
                return false
        return verificaVelha(i + 1)
    }
    return true
}

@JsName("desabilitaBts")
fun desabilitaBts(i: Int){
    if(i < 9){
        val doc = document.getElementsByClassName("bts").item(i) as HTMLButtonElement
        if(!doc.disabled)
            doc.disabled = true
        desabilitaBts(i + 1)
    }
}

//@JsName("jarvis")
//fun jarvis(){

//}

