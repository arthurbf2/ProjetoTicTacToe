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


}


runApplication<Aplicacao>(*args)


