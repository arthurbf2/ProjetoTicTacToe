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
    val status = document.getElementById("status") 
    if (tabuleiro!= null)
        tabuleiro.innerHTML = """
        <html>
        <table>
                <tr>
                    <td><button id="b00">00</button></td>
                    <td><button id="b01">01</button></td>
                    <td><button id="b02">02</button></td>
                </tr>
                <tr>
                    <td><button id="b10">10</button></td>
                    <td><button id="b11">11</button></td>
                    <td><button id="b12">12</button></td>
                </tr>
                <tr>
                    <td><button id="b20">20</button></td>
                    <td><button id="b21">21</button></td>
                    <td><button id="b22">22</button></td>
                </tr>
            </table>
        </html> 
        """
}

fun printa(){

}
