import kotlinx.browser.*
import org.w3c.dom.*



val board = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))

@JsName("jogar")
fun jogar(vsJarvis: Boolean) {
    testeglobal = 1
    val options = document.getElementById("botoes")
    if(options != null){
        options.innerHTML = ""
    }
    val status = document.getElementById("status")
    if(status != null)
        status.innerHTML = "Vez de jogador 1(X)"
    val tabuleiro = document.getElementById("tabuleiro")
    if (tabuleiro!= null){
        tabuleiro.innerHTML = """
        <html>
        <table>
                <tr>
                    <td><button class="bts" id="b00" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                    <td><button class="bts" id="b01" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                    <td><button class="bts" id="b02" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                </tr>
                <tr>
                    <td><button class="bts" id="b10" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                    <td><button class="bts" id="b11" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                    <td><button class="bts" id="b12" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                </tr>
                <tr>
                    <td><button class="bts" id="b20" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                    <td><button class="bts" id="b21" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                    <td><button class="bts" id="b22" onclick="main.botaoPressionado(id, $vsJarvis)" value="0">.</button></td>
                </tr>
            </table>
        </html> 
        """
    }
}

var testeglobal: Int = 1 

@JsName("botaoPressionado")
fun botaoPressionado(id:String, vsJarvis: Boolean){
    val status = document.getElementById("status")
    val botao = document.getElementById(id) as HTMLButtonElement
    val linha = id[1].code- 48
    val coluna = id[2].code - 48
    if (botao.disabled == false) 
        if(testeglobal == 1){
            println("Jogador 1 jogou")
            botao.innerHTML = "X"
            board[linha][coluna] = 1
            testeglobal = 0
            botao.disabled = true // desabilitar o botão
            if(status != null)
                status.innerHTML = "Vez de jogador 2(O)"
            if(vsJarvis && !fimDeJogo()){
                jarvis()
            }
        } else{
            println("Jogador 2 jogou")
            testeglobal = 1
            botao.innerHTML = "O"
            board[linha][coluna] = -1
            botao.disabled = true // desabilitar o botão 
            if(status != null)
                status.innerHTML = "Vez de jogador 1(X)"
        }
    if(fimDeJogo()){
        desabilitaBts(0)
        if(glob) {
            vencedor(linha, coluna, vsJarvis)
            glob = false
        }
        val restart = document.getElementById("botaoRestart")
        if(restart != null)
            restart.innerHTML = """
                <button onclick="main.resetaBoard(0, 0, $vsJarvis)">Jogar novamente</button>
                <button onclick="main.resetaBoardGOTOMENU(0, 0)">Voltar ao menu</button>
            """
    }
}
var glob: Boolean = true
@JsName("vencedor")
fun vencedor(linha: Int, coluna: Int, vsJarvis: Boolean){
    val status = document.getElementById("status")
    println("" + linha + coluna)
    if(status != null) {
        if(verifica()) {
            if(board[linha][coluna] == 1) {
                println("O jogador 1 venceu!")
                status.innerHTML = "O jogador 1 venceu!"
            }
            else{
                if(!vsJarvis)
                    status.innerHTML = "O jogador 2 venceu!"
                else    
                    status.innerHTML = "Jarvis ganhou!!!!!!"
            }
        }
        else   
            if(verificaVelha(0))
                status.innerHTML = "Deu Velha!"
    }
}

@JsName("resetaBoardGOTOMENU")
fun resetaBoardGOTOMENU(linha: Int, col: Int){
    if(col <= 2 && linha <=2){
        board[linha][col] = 0
        resetaBoardGOTOMENU(linha + 1, col) 
    }
    else 
    	if(col <= 2) {
        	resetaBoardGOTOMENU(0, col + 1) 
    } 
    reset()
    glob = true 
}

fun reset(){
    val options = document.getElementById("botoes")
    val tabuleiro = document.getElementById("tabuleiro")
    val status = document.getElementById("status")
    val restart = document.getElementById("botaoRestart")
    if(options != null){
        options.innerHTML = """
        <button class="botao" onclick="main.jogar(false); document.getElementById('player').play()">Jogar contra um amigo</button>
        <button class="botao" onclick="main.jogar(true); document.getElementById('player').play()">Jogar contra Jarvis</button>
        """
    }
    if(tabuleiro != null)
        tabuleiro.innerHTML = """ """
    if(status != null)
        status.innerHTML = """ """
    if(restart != null)
        restart.innerHTML = """ """ 
}

@JsName("resetaBoard")
fun resetaBoard(linha: Int, col: Int, vsJarvis:Boolean){
    if(col <= 2 && linha <=2){
        board[linha][col] = 0
        resetaBoard(linha + 1, col, vsJarvis) 
    }
    else 
    	if(col <= 2) {
        	resetaBoard(0, col + 1, vsJarvis) 
    } 
    jogar(vsJarvis)
    glob = true
}

@JsName("fimDeJogo")
fun fimDeJogo(): Boolean{
    return (verifica() || verificaVelha(0))
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

@JsName("jarvis")
fun jarvis(){
    var possiblePositions: MutableList<String> = mutableListOf()
    //var bestMoveID:String = ""
    var score:Int = 0
    possiblePositions = verificaEspacos(0, 0, possiblePositions)
    if(possiblePositions.size > 0){
        var bestMoveID = possiblePositions[0]
        for(i in possiblePositions){
            if (jarvisBoardAnalysis(i) >= score)
               bestMoveID = i
        }
        bestMoveID = "b" + bestMoveID
        val move = document.getElementsByClassName("bts").item(auxiliar(bestMoveID)) as HTMLButtonElement
        move.click()
    }
}

@JsName("auxiliar")
fun auxiliar(s: String): Int{
    for(i in 0..9){
        val doc = document.getElementsByClassName("bts").item(i)
        if(doc != null)
            if(doc.id.equals(s))
                return i
    }
    return 0
}


@JsName("verificaEspacos")
fun verificaEspacos(linha: Int, col: Int, list: MutableList<String>): MutableList<String> {
    if(col <= 2 && linha <=2){
        if(board[linha][col] == 0){
            list.add("" + linha + col)
        }
        return verificaEspacos(linha + 1, col, list) 
    }
    else if(col <= 2) {
        	return verificaEspacos(0, col + 1, list) 
    } else
        return list
}

@JsName("jarvisBoardAnalysis")
fun jarvisBoardAnalysis(id: String):Int{
    val linha:Int = id[0].code - 48
    val col:Int = id[1].code - 48
    var score:Int = 0
    if(linha == 1 && col == 1){
        score += 2
        if(positionHasEnemyAtDiagonal(linha, col))
            score -=2
    } else if(linha == col || (linha == 0 && col == 2) || (linha == 2 && col == 0)){
        score += 1
        if(positionHasEnemyAtDiagonal(linha, col))
            score -=2
    } else{
        if(positionHasEnemyAtLine(linha, 0) || positionHasEnemyAtColumn(0, col)){
            score -= 2
        } 
    }

    return score

}

@JsName("positionHasEnemyAtLine")
fun positionHasEnemyAtLine(linha: Int, col: Int):Boolean{
    if(board[linha][col] == 1)
        return true
    else if(col < 2)
        return positionHasEnemyAtLine(linha, col + 1)         
    return false

}

@JsName("positionHasEnemyAtColumn")
fun positionHasEnemyAtColumn(linha: Int, col: Int):Boolean{
    if(board[linha][col] == 1)
        return true
    else if(linha < 2)
        return positionHasEnemyAtColumn(linha+1, col) 
    return false

}

@JsName("positionHasEnemyAtDiagonal")
fun positionHasEnemyAtDiagonal(linha: Int, col: Int): Boolean {
    if (linha == 1 || col == 1){
        if(board[0][0] == 1 || board[2][2] == 1 || board[0][2] == 1 || board[2][0] == 1)
            return true
        else
            return false
    }   
    else if (linha == col){
        if(board[0][0] == 1 || board[1][1] == 1 || board[2][2] == 1)
            return true
        else
            return false
    }else{
        if(board[0][2] == 1 || board[1][1] == 1 || board[2][0] == 1)
            return true
        else
            return false
    }
}
