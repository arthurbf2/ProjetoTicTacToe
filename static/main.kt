import kotlinx.browser.*
import org.w3c.dom.*

val board = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))

@JsName("jogar")
fun jogar(vsJarvis: Boolean) {
    vezJogada = 1
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
        <table class="table-jogo">
                <tr>
                    <td><button class="bts" id="b00" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                    <td><button class="bts" id="b01" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                    <td><button class="bts" id="b02" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                </tr>
                <tr>
                    <td><button class="bts" id="b10" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                    <td><button class="bts" id="b11" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                    <td><button class="bts" id="b12" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                </tr>
                <tr>
                    <td><button class="bts" id="b20" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                    <td><button class="bts" id="b21" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                    <td><button class="bts" id="b22" onclick="main.botaoPressionado(id, $vsJarvis)" value="0"><img src="imagens/fundo_botao.png"></button></td>
                </tr>
            </table>
        </html> 
        """
    }
}

var vezJogada: Int = 1 

@JsName("botaoPressionado")
fun botaoPressionado(id:String, vsJarvis: Boolean){   // Recebe a ID do botão clicado e uma boolean correspondente ao modo de jogo escolhido.
    val status = document.getElementById("status")
    val botao = document.getElementById(id) as HTMLButtonElement
    val linha = id[1].code- 48
    val coluna = id[2].code - 48
    if (botao.disabled == false) 
        if(vezJogada == 1){
            botao.innerHTML = "X"
            board[linha][coluna] = 1
            vezJogada = 0
            botao.disabled = true // desabilitar o botão
            if(status != null)
                status.innerHTML = "Vez de jogador 2(O)"
            if(vsJarvis && !fimDeJogo()){   //Caso o modo seja o single-player, a função de jogada da IA (que é tratada como 'player2') é chamada
                jarvis()
            }
        } else{
            vezJogada = 1
            botao.innerHTML = "O"
            board[linha][coluna] = -1
            botao.disabled = true // desabilitar o botão 
            if(status != null)
                status.innerHTML = "Vez de jogador 1(X)"
        }
    if(fimDeJogo()){
        desabilitaBts(0)
        if(status != null){
            if(status.innerHTML.contains("!") == false)
                vencedor(linha, coluna, vsJarvis)
        }
        val restart = document.getElementById("botaoRestart")
        if(restart != null)
            restart.innerHTML = """
                <button onclick="main.resetaBoard(0, 0, $vsJarvis)">Jogar novamente</button>
                <button onclick="main.resetaBoardGOTOMENU(0, 0)">Voltar ao menu</button>
            """
    }
}

@JsName("vencedor")   // Altera o HTML ao final da partida
fun vencedor(linha: Int, coluna: Int, vsJarvis: Boolean){
    val status = document.getElementById("status")
    if(status != null) {
        if(verifica()) {
            if(board[linha][coluna] == 1) {
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

@JsName("resetaBoardGOTOMENU") // Função chamada para voltar ao Menu
fun resetaBoardGOTOMENU(linha: Int, col: Int){
    if(col <= 2 && linha <=2){
        board[linha][col] = 0
        resetaBoardGOTOMENU(linha + 1, col) 
    }
    else 
    	if(col <= 2) 
        	resetaBoardGOTOMENU(0, col + 1) 
        else
            reset()
}

fun reset(){
    val options = document.getElementById("botoes")
    val tabuleiro = document.getElementById("tabuleiro")
    val status = document.getElementById("status")
    val restart = document.getElementById("botaoRestart")
    val listSongs = document.getElementById("listSongs")
    if(options != null){
        options.innerHTML = """
        <button class="botao" onclick="main.jogar(false); document.getElementById('player').play()">Jogar contra um amigo</button>
        <button class="botao" onclick="main.jogar(true); document.getElementById('player').play()">Jogar contra Jarvis</button>
        <button class="botao" onclick="main.soundTrack()">Trilha sonora</button>
        """
    }
    if(tabuleiro != null)
        tabuleiro.innerHTML = """ """
    if(status != null)
        status.innerHTML = """ """
    if(restart != null)
        restart.innerHTML = """ """ 
    if(listSongs != null)
        listSongs.innerHTML = """ """

}

@JsName("resetaBoard") // Função chamada para jogar novamente no mesmo modo de jogo
fun resetaBoard(linha: Int, col: Int, vsJarvis:Boolean){
    if(col <= 2 && linha <=2){
        board[linha][col] = 0
        resetaBoard(linha + 1, col, vsJarvis) 
    }
    else 
    	if(col <= 2) 
        	resetaBoard(0, col + 1, vsJarvis) 
        else
             jogar(vsJarvis)
}

@JsName("fimDeJogo") // Função que verifica as possibilidades de finalização do jogo
fun fimDeJogo(): Boolean{
    return (verifica() || verificaVelha(0))
}

 /* As posições do tabuleiro recebem o valor de 0, caso vazias; 1, caso contenha um X;
     e -1 caso contenham O. Portanto, uma linha/coluna/diagonal que tenha valor de -3 ou 3 
     é uma linha/coluna/diagonal completa.
    */

@JsName("verifica") //Função que verifica as possibilidades de vitória
fun verifica(): Boolean{
    if(verificaDiagonais())
        return true
    if(verificaLinhas(0, 0, 0))
        return true
    if(verificaColunas(0, 0, 0))
        return true
    return false
}


@JsName("verificaLinhas") // Verifica o somatório das linhas e retorna true caso exista uma vitória em uma das linhas do tabuleiro
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

@JsName("verificaColunas") // Verifica o somatório das colunas e retorna true caso exista uma vitória em uma das colunas do tabuleiro
fun verificaColunas(linha: Int, col: Int, x: Int): Boolean {
    if(x == 3 || x == -3)
        return true
    if(col <= 2 && linha <=2){
        val r = board[linha][col]
        return verificaColunas(linha + 1, col, r + x) 
    }
    else if(col <= 2) {
        return verificaColunas(0, col + 1, 0) 
    } 
    return false
}

@JsName("verificaDiagonais") // Verifica o somatório das diagonais e retorna true caso exista uma vitória em uma das diagonais do tabuleiro
fun verificaDiagonais(): Boolean {
    val d1: Int = board[0][0] + board[1][1] + board[2][2]
    val d2: Int = board[0][2] + board[1][1] + board[2][0]
    if(d1 == 3 || d1 == -3 || d2 == 3 || d2 == -3)
    	return true
    return false
}

@JsName("verificaVelha")   // Verifica a possibilidade de empate
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

@JsName("desabilitaBts")   // Desabilita botões caso ainda estejam vazios ao final da partida
fun desabilitaBts(i: Int){
    if(i < 9){
        val doc = document.getElementsByClassName("bts").item(i) as HTMLButtonElement
        if(!doc.disabled)
            doc.disabled = true
        desabilitaBts(i + 1)
    }
}

@JsName("jarvis")  // IA batizada de Jarvis utilizada no modo single-player.
fun jarvis(){
    var possiblePositions: MutableList<String> = mutableListOf()  
    possiblePositions = verificaEspacos(0, 0, possiblePositions)
    if(possiblePositions.size > 0){
        val firstPosition:String = possiblePositions.removeAt(0)
        var bestMoveID = firstPosition
        var score:Int = jarvisBoardAnalysis(firstPosition)
        for(i in possiblePositions){
                var actualScore: Int = jarvisBoardAnalysis(i)
                if (actualScore > score){
                    score = actualScore
                    bestMoveID = i
                }
        }
 
        bestMoveID = "b" + bestMoveID
        val move = document.getElementsByClassName("bts").item(auxiliarCasting(bestMoveID)) as HTMLButtonElement
        move.click()
    }
}

@JsName("auxiliarCasting")  
fun auxiliarCasting(s: String): Int{
    /*
    Por algum motivo alguns castings não funcionavam direito na conversão para JavaScript. 
    Essa foi a maneira de contornar o problema.
    */
    for(i in 0..9){
        val doc = document.getElementsByClassName("bts").item(i)
        if(doc != null)
            if(doc.id.equals(s))
                return i
    }
    return 0
}


@JsName("verificaEspacos")   // Retorna uma lista de posições clicáveis para serem analisadas pela IA.
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
 
@JsName("jarvisBoardAnalysis")   // Analisa as posições vazias para escolher a próxima jogada de Jarvis através de um sistema de score
fun jarvisBoardAnalysis(id: String):Int{
    val linha:Int = id[0].code - 48
    val col:Int = id[1].code - 48
    var score:Int = 0
    if(positionIsVitalToWin(linha, col))
        score += 15
    if(positionsIsVitalToStopPlayer(linha, col))
        score += 10
    if(linha == 1 && col == 1){
        score += 6
        if(positionHasEnemyAtDiagonal(linha, col))
            score -=1
    } else if(linha == col || (linha == 0 && col == 2) || (linha == 2 && col == 0)){
        if(positionHasEnemyAtDiagonal(linha, col)) 
            score -=1
    }
    if(positionHasEnemyAtLine(linha, 0)){
        score -= 1
    }
    if(positionHasEnemyAtColumn(0, col)){
        score -= 1
    }

    return score
}

@JsName("positionsIsVitalToStopPlayer")  // Se o jogador ganhar ao escolher a posição, ela é vital para a IA impedir seu adversário
fun positionsIsVitalToStopPlayer(linha: Int, col: Int): Boolean{  
    val r1 = contabilizaLinha(linha, 0, 0)
    val r2 = contabilizaColuna(0, col, 0)
    val r3 = contabilizaDiagonal(linha, col)
    if(r1 == 2){  
        return true
    }
    if(r2 == 2 )
        return true
    if(r3 == 2)
        return true
    return false
}

@JsName("positionsIsVitalToWin")  // Se a IA ganhar ao escolher a posição, ela é vital para vencer
fun positionIsVitalToWin(linha: Int, col: Int): Boolean{  
    val r1 = contabilizaLinha(linha, 0, 0)
    val r2 = contabilizaColuna(0, col, 0)
    val r3 = contabilizaDiagonal(linha, col)
    if(r1 == -2){  
        return true
    }
    if(r2 == -2)
        return true
    if( r3 == -2)
        return true
    return false
}


@JsName("contabilizaDiagonal") // Retorna o somatório das diagonais
fun contabilizaDiagonal(linha: Int, col: Int): Int{  
    if(linha == 1 && col == 1){
        val x = board[0][0] + board[2][2]
        val z = board[0][2] + board[2][0]
        if(x == 2 || x == -2)
            return x
        else if(z == 2 || z == -2)
            return z
        return 0
    } else if(linha == 0 && col == 0)
        return board[1][1] + board[2][2]
    else if(linha == 2 && col == 2)
        return board[1][1] + board[0][0]
    else if(linha == 0 && col == 2)
        return board[1][1] + board[2][0]
    else if(linha == 2 && col == 0)
        return board[1][1] + board[0][2]  
    else
        return 0  
}

@JsName("contabilizaLinha")
fun contabilizaLinha(linha: Int, col: Int, res: Int): Int{   // Retorna o somatório da linha
    if(col <= 2){
        val x = board[linha][col] 
        return contabilizaLinha(linha, col + 1, x + res)
    }
    return res
}

@JsName("contabilizaColuna")
fun contabilizaColuna(linha: Int, col: Int, res: Int): Int { // Retorna o somatório da coluna
    if(linha <= 2){
        val x = board[linha][col]
        return contabilizaColuna(linha + 1, col, x + res)
    }
    return res
}

@JsName("positionHasEnemyAtLine") // Verifica se existe alguma casa já escolhida pelo player na mesma linha
fun positionHasEnemyAtLine(linha: Int, col: Int):Boolean{
    if(board[linha][col] == 1)
        return true
    else if(col < 2)
        return positionHasEnemyAtLine(linha, col + 1)         
    return false

}

@JsName("positionHasEnemyAtColumn") // Verifica se existe alguma casa já escolhida pelo player na mesma coluna
fun positionHasEnemyAtColumn(linha: Int, col: Int):Boolean{
    if(board[linha][col] == 1)
        return true
    else if(linha < 2)
        return positionHasEnemyAtColumn(linha+1, col) 
    return false

}

@JsName("positionHasEnemyAtDiagonal") // Verifica se existe alguma casa já escolhida pelo player na mesma diagonal
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

@JsName("soundTrack")  // Chama o menu de escolha de músicas
fun soundTrack(){
    val options = document.getElementById("botoes")
    if(options != null){
        options.innerHTML = """
        """
    }
    val listSongs = document.getElementById("listSongs")
    val songControl = document.getElementById("song-control")
    if(listSongs != null){
        listSongs.innerHTML = """
            <h2>Escolha uma música</h2>
            <ul>
                <li><button value="beneathTheMask" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Beneath The Mask - Lyn</button></li>
                <li><button value="cantStop" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Can't Stop - RHCP</button></li>
                <li><button value="caravan" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Caravan - Duke Ellington</button></li>
                <li><button value="dayBreak" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Day Break - Michael Haggins</button></li>
                <li><button value="fernando" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Fernando - ABBA</button></li>
                <li><button value="ironMan" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Iron Man - Black Sabbath</button></li>
                <li><button value="kowaretaSekaiNoUta" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Kowareta Sekai - Keiichi Okabe</button></li>
                <li><button value="mammaMia" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Mamma Mia - ABBA</button></li>
                <li><button value="misirlou" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Misirlou - Dick Dale</button></li>
                <li><button value="moonageDaydream" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Moonage Daydream - David Bowie</button></li>
                <li><button value="myFavoriteThings" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">My Favorite Things - John Coltrane</button></li>
                <li><button value="noGood" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">No Good - Ben Matthews</button></li>
                <li><button value="samurai" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Samurai - Djavan</button></li>
                <li><button value="spaceOddity" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">Space Oddity - David Bowie</button></li>
                <li><button value="startedAJoke" onclick="main.songChosen(value); document.getElementById('player').pause(); document.getElementById('player').play()">I Started A Joke - Bee Gees</button></li>
            </ul>
        """
    }
    if(songControl != null){
        songControl.innerHTML = """
        """
    }

}

@JsName("songChosen")  // Modifica a música a ser tocada
fun songChosen(song:String){
    val songControl = document.getElementById("song-control")
    val restart = document.getElementById("botaoRestart")
    val musica = document.getElementById("musica")
    if(musica != null){
        musica.innerHTML = """
            <audio preload="none" id="player" loop="loop" >
                <source src="midia/$song.mp3" type="audio/mp3"/>
            </audio>
        """
    }
    if(songControl != null){
        songControl.innerHTML = """
        <button onclick="document.getElementById('player').play()"></button>
        <button onclick="document.getElementById('player').pause()"></button>
        <button onclick="document.getElementById('player').volume+=0.1"></button>
        <button onclick="document.getElementById('player').volume-=0.1"></button>
        """
    }
    if(restart != null){
    restart.innerHTML="""
    <button onclick="main.resetaBoardGOTOMENU(0, 0)">Voltar ao menu</button>
    """
    }
}