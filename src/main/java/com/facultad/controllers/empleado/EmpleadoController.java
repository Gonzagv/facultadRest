package com.facultad.controllers.empleado;

import com.facultad.exceptions.empleado.EmpleadoNoExisteException;
import com.facultad.services.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    //Busca empleados en la base a partir de su Dni.

    @GetMapping("/empleados/{dni}")
    public ResponseEntity buscarEmpleado(@PathVariable String dni) {
        try {
            return new ResponseEntity(empleadoService.obtenerEmpleado(dni), HttpStatus.OK);
        } catch (EmpleadoNoExisteException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Busca empleados con sueldo entre dos valores min y max.

    @GetMapping("/empleados/salario")
    public ResponseEntity BuscarPorSalario(@RequestParam(required = false) Double salarioMin, Double salarioMax) {
        return new ResponseEntity(empleadoService.obtenerEmpleadosPorSalario(salarioMin, salarioMax), HttpStatus.OK);
    }

    //Borra empleado a partir de su dni.

    @DeleteMapping("/empleados/{dni}")
    public ResponseEntity borrarEmpleado(@PathVariable String dni) {
        try {
            return new ResponseEntity(empleadoService.borrarEmpleado(dni), HttpStatus.OK);
        } catch (EmpleadoNoExisteException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Busca empleados a partir de cualquier parametro o parametros ingresados.

    @GetMapping("/empleados")
    public ResponseEntity obtenerEmpleadosPor(@RequestParam(required = false) Map<String, String> allParams) {
        return new ResponseEntity(empleadoService.buscarEmpleados(allParams), HttpStatus.OK);
    }
}
