package cl.felipe.obrestdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController//devuelve datos
public class HellowController {

    //Ejemplo 1 básico que devuelve un string
    @GetMapping("/hola")
    public String holaMundo(){
        return "Hola mundo que tal vamos!! Hasta luego";
    }

    //Ejemplo 2 no es recomendado enviar html desde Java
    @GetMapping("/bootstrap")
    public String bootstrap(){
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap desde Spring</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
                  </head>
                  <body>
                    <h1>Hello, world! DESDE SPRING BOOT</h1>
                    <a class ="btn btn-primary" href="https://www.google.com">Google</a>
                    
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
                  </body>
                </html>
                
                """;
    }

}
