package cl.felipe.obrestdatajpa.repository;

import cl.felipe.obrestdatajpa.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
