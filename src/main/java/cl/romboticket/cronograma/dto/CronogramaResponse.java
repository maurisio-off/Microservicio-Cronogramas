package cl.romboticket.cronograma.dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class CronogramaResponse {

        private Long funcionId;
        private Long eventoId;
        private Long recintoId;
        private ZonedDateTime fechaHora;

}
