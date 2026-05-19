package controller;

import model.Materia;
import view.MateriaView;

public class MateriaController {
     private Materia     model;
    private MateriaView view;

    public MateriaController(Materia model, MateriaView view) {
        this.model = model;
        this.view  = view;
    }
    public String getNombreMateria()         { return model.getNombreMateria(); }
    public void   setNombreMateria(String n) { model.setNombreMateria(n); }
    public int    getCreditos()              { return model.getCreditos(); }
    public void   setCreditos(int c)         { model.setCreditos(c); }
    public void   actualizarVista()          { view.mostrarMateria(model); }
}

