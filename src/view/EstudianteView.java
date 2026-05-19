package view;

import model.Estudiante;

public class EstudianteView {
        public void mostrarEstudiante(Estudiante e) {
        System.out.println("=== Estudiante ===");
        System.out.println("ID       : " + e.getIdEstudiante());
        System.out.println("Nombre   : " + e.getNombre());
        System.out.println("Apellido : " + e.getApellido());
        System.out.println("Email    : " + e.getEmail());
    }
}
    

