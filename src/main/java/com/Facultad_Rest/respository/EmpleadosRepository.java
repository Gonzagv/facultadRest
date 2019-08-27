package com.Facultad_Rest.respository;


import com.Facultad_Rest.model.CargoEnum;
import com.Facultad_Rest.model.Empleado;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmpleadosRepository {

    Map<String, Empleado> listaEmpleados = new HashMap<String, Empleado>();

    public Map<String, Empleado> mostrarEmpleados(){
        return listaEmpleados;
    }

    public Empleado buscarEmpleado(String dni){
        if (listaEmpleados.containsKey(dni)) {
            return listaEmpleados.get(dni);
        }else{
            return null;
        }
    }

    public Empleado agregarEmpleado(Empleado empleado){
        if(!listaEmpleados.containsKey(empleado.getDni())) {
            listaEmpleados.put(empleado.getDni(), empleado);
            return empleado;
        }else{
            return null;
        }
    }

    public Empleado modificarEmpleado(String dni,Empleado empleado){
        if(listaEmpleados.containsKey(dni)){
            listaEmpleados.put(empleado.getDni(), empleado);
            return empleado;
        }else{
            return null;
        }
    }

    public Empleado borrarEmpleado(String dni) {
        if (listaEmpleados.containsKey(dni)) {
            return listaEmpleados.remove(dni);
        } else {
            return null;
        }
    }

    public Collection<Empleado> listaPorCargo(CargoEnum cargo){
        return listaEmpleados.values().stream().filter(empleado -> empleado.getCargo().equals(cargo))
                .collect(Collectors.toList());
    }

    public Map<String, Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(Map<String, Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
}