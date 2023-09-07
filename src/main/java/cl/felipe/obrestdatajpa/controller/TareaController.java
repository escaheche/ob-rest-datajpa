package cl.felipe.obrestdatajpa.controller;

import cl.felipe.obrestdatajpa.entities.Tarea;
import cl.felipe.obrestdatajpa.repository.TareaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TareaController {
    private final Logger log = LoggerFactory.getLogger(TareaController.class);
    //atributos
    private TareaRepository tareaRepository;
    //constructores

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    //Crud sobre la entidad Tarea
    /**
     * Buscar todas las tareas
     * http://localhost:8081/api/tareas
     * @return
     */

    @GetMapping("/api/tareas")
    @Operation(summary = "Listar todos las tareas")
    public List<Tarea> findAll(){
        //recuperar y devolver los libros de la base de datos
        return tareaRepository.findAll();
    }


    /**
     * Crea una nuevo tarea en base de datos
     * Metodo POST, no coliciona con finAll por que son diferentes metodos HTTP: GET vs POST
     * para
     */
    //crear una nueva tarea en base de datos
    @PostMapping("/api/tareas")
    @Operation(summary = "Agregar una nueva tarea")
    public ResponseEntity<Tarea> create(@RequestBody Tarea tarea, @RequestHeader HttpHeaders headers){
        //Extraigo quien envia la peticion relacionado a la seguridad de navegacion
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recivido por parametros en la base de datos
        if(tarea.getId()!=null){//quiere decir que el id existe y por lo tanto no es una creacion
            log.warn("trying to create a tarea with id");
            System.out.println("trying to create a tarea with id");
            return ResponseEntity.badRequest().build();
        }
        if(tarea.getDescripcion()==""){ // si no tiene Descripcion
            log.warn("Falta descripcion");
            return ResponseEntity.badRequest().build();
        }
        if(tarea.getFechaCreacion()==null){ // si no tiene Fecha de creacion
            log.warn("Falta fecha creacion");
            return ResponseEntity.badRequest().build();
        }
        if(tarea.getVigente()==null){ // si no tiene vigencia
            log.warn("Falta la vigencia");
            return ResponseEntity.badRequest().build();
        }
        Tarea result = tareaRepository.save(tarea);
        return ResponseEntity.ok(result); //la tarea devuelta tiene una clave primaria
    }

    /**
     * actualizar una tarea existente en base de datos
     */
    @PutMapping("/api/tareas")
    @Operation(summary = "Editar una tarea")
    public ResponseEntity<Tarea> update(@RequestBody Tarea tarea){


        if(tarea.getId()==null){ // si no tiene id quiere decir que si es una creación
            log.warn("Trying to update a non existent tarea");
            return ResponseEntity.badRequest().build();
        }
        if(tarea.getDescripcion()==""){ // si no tiene Descripcion
            log.warn("Falta descripcion");
            return ResponseEntity.badRequest().build();
        }
        if(tarea.getFechaCreacion()==null){ // si no tiene Fecha de creacion
            log.warn("Falta fecha creacion");
            return ResponseEntity.badRequest().build();
        }
        if(tarea.getVigente()==null){ // si no tiene vigencia
            log.warn("Falta la vigencia");
            return ResponseEntity.badRequest().build();
        }

        if(!tareaRepository.existsById(tarea.getId())){
            log.warn("Trying to update a non existent tarea");
            return ResponseEntity.notFound().build();
        }

        //El proceso de actualizacion
        Tarea result = tareaRepository.save(tarea);
        return ResponseEntity.ok(result); //la tarea devuelta tiene una clave primaria

    }
    //borrar una tarea en base de datos a traves de un id

    @DeleteMapping("/api/tareas/{id}")
    @Operation(summary = "Remover una tarea por clave primaria id LONG")
    public ResponseEntity<Tarea> delete(@Parameter(description ="Clave primaria tipo LONG")@PathVariable Long id){

        if(!tareaRepository.existsById(id)){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        tareaRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
