package view;

import model.Materia;

public class MateriaView {
        public void mostrarMateria(Materia m) {
        System.out.println("=== Materia ===");
        System.out.println("ID       : " + m.getIdMateria());
        System.out.println("Nombre   : " + m.getNombreMateria());
        System.out.println("Créditos : " + m.getCreditos());
    }
}
