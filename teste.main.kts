@file:Repository("https://jcenter.bintray.com")
@file:DependsOn("org.springframework.boot:spring-boot-starter-web:2.4.9")
@file:DependsOn("org.springframework.boot:spring-boot-starter-tomcat:2.4.9")

package meuServidor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
open class Aplicacao {


    @GetMapping("oi.html")
    fun ola() : String {
        return """
        <html>
            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="static/style.css">
                <title>TicTacToe</title>
                <script src="https://cdn.jsdelivr.net/npm/kotlin@1.5.21/kotlin.min.js"></script>
                <script src="main.js"></script>
            </head>
            
            <body onload="main.jogar()">

                <div id="status">
                </div>
                <div id="tabuleiro">
                </div>
            </body>
            
        </html>
        """
    }

}


runApplication<Aplicacao>(*args)


