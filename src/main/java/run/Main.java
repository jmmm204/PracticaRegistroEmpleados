package run;

import config.JPAUtil;
import entities.Cargo;
import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.dao.DCargo;
import repository.dao.DEmpleado;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DCargo cargoRepo = new DCargo();
        DEmpleado empleadoRepo = new DEmpleado();

        Cargo cargo1 = new Cargo(null, "Desarrollador java", 50000.0, null);
        Cargo cargo2 = new Cargo(null, "Analista QA", 45000.0, null);
        cargoRepo.create(cargo1);
        cargoRepo.create(cargo2);
        System.out.println("Los cargados han sido creados.");

        System.out.println("-- Listando todos los cargos --");
        List<Cargo> cargos = cargoRepo.getAll();
        cargos.forEach(c -> System.out.println(c.getNombre() + " Salario " + c.getSalario()));

        System.out.println("-- Actualizando un cargo --");
        Optional<Cargo> cargoAActualizar = cargoRepo.getById(1L);
        cargoAActualizar.ifPresent(c -> {
            c.setSalario(55000.0);
            cargoRepo.update(c);
            System.out.println("Cargo actualizado: " + c.getNombre() + " Nuevo Salario: " + c.getSalario());
        });

        System.out.println("-- Creando empleados y asignando cargo --");
        Optional<Cargo> devCargo = cargoRepo.getById(1L);
        if (devCargo.isPresent()) {
            Empleado empleado1 = new Empleado(null, "Juan", "Perez", devCargo.get());
            Empleado empleado2 = new Empleado(null, "Ana", "Gomez", devCargo.get());
            empleadoRepo.create(empleado1);
            empleadoRepo.create(empleado2);
            System.out.println("Empleados creados y asignados al cargo llamado 'Desarrollador Java'.");
        }

        System.out.println("-- Listando todos los empleados --");
        List<Empleado> empleados = empleadoRepo.getAll();
        empleados.forEach(e -> System.out.println(e.getNombre() + " " + e.getApellido() + " Cargo: " + e.getCargo().getNombre()));

        System.out.println("-- Eliminando un cargo --");
        cargoRepo.delete(2L);
        System.out.println("El cargo con ID 2 fue eliminado.");

        System.out.println("-- Lista final de cargos --");
        cargoRepo.getAll().forEach(c -> System.out.println(c.getNombre()));

        JPAUtil.close();
        System.out.println("Aplicación finalizada.");
    }
}