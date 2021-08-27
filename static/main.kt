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
                    <td><button style="width: 150px;”>PUT SOME TEXT HERE!</button></td>
                    <td><button value="0"></button></td>
                    <td><button value="0"></button></td>
                </tr>
                <tr>
                    <td><button value="0"></button></td>
                    <td><button value="0"></button></td>
                    <td><button value="0"></button></td>
                </tr>
                <tr>
                    <td><button value="0"></button></td>
                    <td><button value="0"></button></td>
                    <td><button value="0"></button></td>
                </tr>
            </table>
        </html> 
        """
}

fun printa(){

}
