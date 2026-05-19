package controller;

import model.Docente;
import view.DocenteView;

public class DocenteController {
       private Docente     model;
    private DocenteView view;

    public DocenteController(Docente model, DocenteView view) {
        this.model = model;
        this.view  = view;
    }
    public String getNombre()              { return model.getNombre(); }
    public void   setNombre(String n)      { model.setNombre(n); }
    public String getEspecialidad()        { return model.getEspecialidad(); }
    public void   setEspecialidad(String e){ model.setEspecialidad(e); }
    public void   actualizarVista()        { view.mostrarDocente(model); }
}

