package controller;

import model.Grupo;
import view.GrupoView;

public class GrupoController {
    private Grupo modelo;
    private GrupoView vista;

    public GrupoController(Grupo modelo, GrupoView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void actualizarVista() {
        vista.mostrarGrupo(modelo);
    }
}
    

