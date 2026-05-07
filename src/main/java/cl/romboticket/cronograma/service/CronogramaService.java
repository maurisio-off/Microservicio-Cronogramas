package cl.romboticket.cronograma.service;

import cl.romboticket.cronograma.dto.CronogramaRequest;
import cl.romboticket.cronograma.dto.CronogramaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cl.romboticket.cronograma.model.Funcion;
import org.springframework.stereotype.Service;
import cl.romboticket.cronograma.repository.CronogramaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronogramaService {
    private final CronogramaRepository repository;

    // crear POST
    public CronogramaResponse agendarFuncion(CronogramaRequest request) {
        log.info("Iniciando registro de función para el evento ID: {}", request.getEventoId());

        Funcion funcion = new Funcion();
        funcion.setEventoId(request.getEventoId());
        funcion.setRecintoId(request.getRecintoId());
        funcion.setFechaHora(request.getFechaHora());

        funcion = repository.save(funcion);
        log.info("Función registrada con éxito. ID generado: {}", funcion.getFuncionId());

        return mapToResponse(funcion);
    }
    /* GET Todos
     * Usamos Streams de Java para transformar la lista de Entidades
     * en una lista de DTOs antes de devolverla  IMPORTANTE */
    public List<CronogramaResponse> listarTodo() {
        log.info("Consultando todas las funciones en la base de datos");
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    /* Get por ID orElseThrow arroja una excepción si el ID no existe, la cual  será atrapada por el GlobalExceptionHandler
     */
    public CronogramaResponse buscarPorId(Long id) {
        log.info("Buscando función con ID: {}", id);
        Funcion funcion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Función no encontrada en la base de datos!"));
        return mapToResponse(funcion);
    }

    // Actualizar PUT
    public CronogramaResponse actualizar(Long id, CronogramaRequest request) {
        log.info("Actualizando función ID: {}", id);
        Funcion funcionExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar: El ID de la función no existe"));

        // reemplazamos los datos antiguos con los nuevos que vienen del Request
        funcionExistente.setEventoId(request.getEventoId());
        funcionExistente.setRecintoId(request.getRecintoId());
        funcionExistente.setFechaHora(request.getFechaHora());

        // Al guardar una entidad que ya tiene ID, Hibernate hace un UPDATE en vez de INSERT
        funcionExistente = repository.save(funcionExistente);
        log.info("Función ID {} actualizada correctamente", id);

        return mapToResponse(funcionExistente);
    }

    // Eliminar por id DELETE
    public void eliminar(Long id) {
        log.warn("Solicitud para eliminar función ID: {}", id);
        if(!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El ID de la función no existe");
        }
        repository.deleteById(id);
        log.info("Función ID {} eliminada con éxito", id);
    }

    /*  MÉTODO AUXILIAR
     * Este método privado evita que repitamos las mismas 5 líneas
     * de código cada vez que necesitamos convertir una Entidad a DTO*/

    private CronogramaResponse mapToResponse(Funcion funcion) {
        CronogramaResponse response = new CronogramaResponse();
        response.setFuncionId(funcion.getFuncionId());
        response.setEventoId(funcion.getEventoId());
        response.setRecintoId(funcion.getRecintoId());
        response.setFechaHora(funcion.getFechaHora());
        return response;
    }

}
