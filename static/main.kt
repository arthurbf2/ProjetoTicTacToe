import kotlinx.browser.*
import org.w3c.dom.*


@JsName("jogar")
fun jogar() {
    println("funfando")
    val tabuleiroArray: Array<IntArray> = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )
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

var testeglobal: Int = 1

@JsName("botaoPressionado")
fun botaoPressionado(id:String){
    val botao = document.getElementById(id)
    if (botao != null)
        if(testeglobal == 1){
            botao.innerHTML = "X"
            testeglobal -= 1
        } else {
            botao.innerHTML = "O"
            testeglobal += 1
        }
}
