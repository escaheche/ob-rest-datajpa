package cl.felipe.obrestdatajpa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity//le digo que es una entidad en la bd
@Table(name = "Libros")//se usa para cambiar el nombre
public class Book {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private Integer paginas;
    private Double precio;
    private LocalDate fechaLanzamiento;
    private Boolean online;


    //constructores

    public Book() {
    }

    public Book(Long id, String titulo, String autor, Integer paginas, Double precio, LocalDate fechaLanzamiento, Boolean online) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.online = online;
    }
    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }


}
