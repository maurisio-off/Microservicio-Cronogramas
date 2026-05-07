package cl.romboticket.cronograma.controller;

import cl.romboticket.cronograma.dto.CronogramaRequest;
import cl.romboticket.cronograma.dto.CronogramaResponse;
import cl.romboticket.cronograma.service.CronogramaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cronogramas")
@RequiredArgsConstructor
public class CronogramaController {

    // Aquí le decimos que la variable se llamará "service"
    private final CronogramaService service;


    // Post: http://localhost:8085/api/cronogramas
    @PostMapping
    public ResponseEntity<CronogramaResponse> agendarFecha(@Valid @RequestBody CronogramaRequest request) {
        log.info("Petición REST recibida para crear nueva función");
        CronogramaResponse response = service.agendarFuncion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Retorna 201 Created
    }


    // Get: http://localhost:8085/api/cronogramas
    @GetMapping
    public ResponseEntity<List<CronogramaResponse>> listar() {
        log.info("Petición REST recibida para listar funciones");
        return ResponseEntity.ok(service.listarTodo()); // Retorna 200 OK
    }


    // Get 1: http://localhost:8085/api/cronogramas/3
    /* @PathVariable toma el número "X" de la URL y lo mete en la variable 'id'*/
    @GetMapping("/{id}")
    public ResponseEntity<CronogramaResponse> obtenerUno(@PathVariable Long id) {
        log.info("Petición REST recibida para obtener función {}", id);
        return ResponseEntity.ok(service.buscarPorId(id)); // Retorna 200 OK
    }

    // Actualizar PUT: http://localhost:8085/api/cronogramas/3
    /* Combinamos @PathVariable (para saber QUÉ actualizar)
     * con @RequestBody (para saber los NUEVOS DATOS)*/

    @PutMapping("/{id}")
    public ResponseEntity<CronogramaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody CronogramaRequest request) {
        log.info("Petición REST recibida para actualizar función {}", id);
        return ResponseEntity.ok(service.actualizar(id, request)); // Retorna 200 OK
    }

    // Eliminar DELETE: http://localhost:8085/api/cronogramas/3
    /*Retornamos 'noContent().build()' que equivale a un HTTP 204.
      *Es el estándar REST para indicar que se borró con éxito y no hay datos que devolver.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Petición REST recibida para eliminar función {}", id);
        service.eliminar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
