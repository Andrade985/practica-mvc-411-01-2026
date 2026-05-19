package view;

import model.Grupo;

public class GrupoView {
      public void mostrarGrupo(Grupo g) {
        System.out.println("=== Grupo ===");
        System.out.println("ID Grupo   : " + g.getIdGrupo());
        System.out.println("ID Materia : " + g.getIdMateria());
        System.out.println("ID Docente : " + g.getIdDocente());
        System.out.println("Aula       : " + g.getAula());
        System.out.println("Horario    : " + g.getHorario());
    }
}

