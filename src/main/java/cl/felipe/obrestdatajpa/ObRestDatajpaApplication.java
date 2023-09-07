package cl.felipe.obrestdatajpa;

import cl.felipe.obrestdatajpa.entities.Book;
import cl.felipe.obrestdatajpa.entities.Tarea;
import cl.felipe.obrestdatajpa.repository.BookRepository;
import cl.felipe.obrestdatajpa.repository.TareaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);
		TareaRepository repository1=context.getBean(TareaRepository.class);

		//Crud
		//crear un libro
		Book book = new Book(null,"Homo Deus","Yuval Noah", 450, 29.99, LocalDate.of(2018,12,1),true);
		Book book1 = new Book(null,"Homo Sapiens","Yuval Noah", 450, 19.99, LocalDate.of(2013,12,1),true);

		//crear una tarea
		Tarea tarea = new Tarea(null,"Lavar la loza",LocalDate.of(2023,9,7),true);
		Tarea tarea1 = new Tarea(null,"Planchar la ropa",LocalDate.of(2023,9,7),true);
		//almacenar un libro
		System.out.println("Num de libros en base de datos: "+repository.findAll().size());

		repository.save(book);
		repository.save(book1);
		//almacenar las tareas
		repository1.save(tarea);
		repository1.save(tarea1);

		//recuperar todos los libros
		System.out.println("Num de libros en base de datos: "+repository.findAll().size());

		//borrar un libro
		//repository.deleteById(1L);
		System.out.println("Num de libros en base de datos: "+repository.findAll().size());


	}

}
