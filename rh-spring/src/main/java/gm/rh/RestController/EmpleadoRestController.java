package gm.rh.RestController;


import gm.rh.excepcion.RecursoNoEncontradoExepcion;
import gm.rh.modelo.Empleado;
import gm.rh.services.EmpleadoServicesImpl;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rhapp")
@CrossOrigin(value = "http://localhost:3000")
@Data
public class EmpleadoRestController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoRestController.class);

    private final EmpleadoServicesImpl empleadoServices;

    @GetMapping("/empleados")
    public List<Empleado> listaEmpleados(){
        var empleados = empleadoServices.listarEmpleados();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        return empleadoServices.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id){
        Empleado empleado = empleadoServices.buscarEmpleadoPorId(id);
        if (empleado == null){
            throw new RecursoNoEncontradoExepcion("No se encontro el id: " + id);
        } else return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado (@PathVariable Long id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = empleadoServices.buscarEmpleadoPorId(id);
        if (empleado == null){
            throw new RecursoNoEncontradoExepcion("El id recibido no existe: " + id);
        } else {
            empleado.setNombre(empleadoRecibido.getNombre());
            empleado.setDepartamento(empleadoRecibido.getDepartamento());
            empleado.setSueldo(empleadoRecibido.getSueldo());
            empleadoServices.guardarEmpleado(empleado);
            return ResponseEntity.ok(empleado);
        }
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoServices.buscarEmpleadoPorId(id);
        if (empleado == null){
            throw new RecursoNoEncontradoExepcion("El id recibido no existe: " + id);
        } else {
            empleadoServices.eliminarEmpleado(empleado);
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("Eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }


}
