package view;

import model.Docente;

public class DocenteView {
      public void mostrarDocente(Docente d) {
        System.out.println("=== Docente ===");
        System.out.println("ID           : " + d.getIdDocente());
        System.out.println("Nombre       : " + d.getNombre());
        System.out.println("Especialidad : " + d.getEspecialidad());
    }
}
