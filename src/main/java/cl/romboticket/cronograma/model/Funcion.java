package cl.romboticket.cronograma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "funciones")
@Data
public class Funcion {
/* el @Id nos indica que es la Primary Key y la BD lo asigna aut*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcion_id")
    private Long funcionId;
    /* Usamos el Long y NO @ManyToOne porque es una Referencia Lógica
     * Las tablas de Evento y Recinto están en su respectivo microservicio
     */
    @Column(name = "evento_id", nullable = false)
    private Long eventoId;

    @Column(name = "recinto_id", nullable = false)
    private Long recintoId;
    /* ESCRIBIR EL PORQUÉ AQUI RECORDAR*/
    @Column(name = "fecha_hora", nullable = false)
    private ZonedDateTime fechaHora;
}
