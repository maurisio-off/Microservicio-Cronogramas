package cl.romboticket.cronograma.repository;

import cl.romboticket.cronograma.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaRepository extends JpaRepository<Funcion, Long> {

}
