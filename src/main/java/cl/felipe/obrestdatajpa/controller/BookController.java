package cl.felipe.obrestdatajpa.controller;

import cl.felipe.obrestdatajpa.entities.Book;
import cl.felipe.obrestdatajpa.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController{

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    //atributos
    private BookRepository bookRepository;
    //constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //Crud sobre la entidad Book

    /**
     * Buscar todos los libros
     * http://localhost:8081/api/books
     * @return
     */

    @GetMapping("/api/books")
    @Operation(summary = "Trae todos los libros")
    public List<Book> findAll(){
     //recuperar y devolver los libros de la base de datos
     return bookRepository.findAll();
    }


    /**
     * Buscar solo un libro en base de datos segun su id se agrega PathVariable para que tome el id
     * http://localhost:8081/api/books/1
     * http://localhost:8081/api/books/2
     * Request
     * Response
     * @param id
     * @return
     */
    @GetMapping("/api/books/{id}")
    @Operation(summary = "Busca un libro por clave primaria id LONG")
    public ResponseEntity<Book>findOneById(@Parameter(description ="Clave primaria tipo LONG")@PathVariable Long id){

        Optional<Book> bookOpt = bookRepository.findById(id);
        //opcion 1
        if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return  ResponseEntity.notFound().build();

        //opcion 2 en una linea de codigo
        //return bookOpt.orElse(null);
        //return bookOpt.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());


    }
/**
 * Crear un nuevo libro en base de datos
 * Metodo POST, no coliciona con finAll por que son diferentes metodos HTTP: GET vs POST
 * para
 */
    //crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    @Operation(summary = "Crea un nuevo libro")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        //Extraigo quien envia la peticion relacionado a la seguridad de navegacion
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recivido por parametros en la base de datos
        if(book.getId()!=null){//quiere decir que el id existe y por lo tanto no es una creacion
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); //el libro devuelto tiene una clave primaria
    }
/**
 * actualizar un libro existente en base de datos
 */
    @PutMapping("/api/books")
    @Operation(summary = "Actualizar un libro")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if(book.getId()==null){ // si no tiene id quiere decir que si es una creación
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();

        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        //El proceso de actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); //el libro devuelto tiene una clave primaria

    }



    //borrar un libro en base de datos a traves de un id

    @DeleteMapping("/api/books/{id}")
    @Operation(summary = "Borra un libro por clave primaria id LONG")
    public ResponseEntity<Book> delete(@Parameter(description ="Clave primaria tipo LONG")@PathVariable Long id){

        if(!bookRepository.existsById(id)){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    /**
     * Borra toda la bd
     * @return
     */
    @DeleteMapping("/api/books")
    @Operation(summary = "Borra todos los libros")
    public ResponseEntity<Book> deleteAll(){
        log.info("Rest Request for delette all books");//mensajes de flujo de ejecucion
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
