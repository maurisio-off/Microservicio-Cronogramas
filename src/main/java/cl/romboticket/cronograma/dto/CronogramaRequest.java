package cl.romboticket.cronograma.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CronogramaRequest {
    // @NotNull evita que te envíen un JSON sin el evento_id
    @NotNull(message = "El ID del evento no puede ser nulo")
    private Long eventoId;

    @NotNull(message = "El ID del recinto no puede ser nulo")
    private Long recintoId;

    // el @Future valida automáticamente que no puedan crear funciones en fecha pasad
    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha de la función debe ser en el futuro")
    private ZonedDateTime fechaHora;
}
