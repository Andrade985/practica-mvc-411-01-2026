package controller;

import model.Estudiante;
import view.EstudianteView;

public class EstudianteController {
      private Estudiante model;
    private EstudianteView view;

    public EstudianteController(Estudiante model, EstudianteView view) {
        this.model = model;
        this.view  = view;
    }
    public String getNombre()           { return model.getNombre(); }
    public void   setNombre(String n)   { model.setNombre(n); }
    public String getApellido()         { return model.getApellido(); }
    public void   setApellido(String a) { model.setApellido(a); }
    public String getEmail()            { return model.getEmail(); }
    public void   setEmail(String e)    { model.setEmail(e); }
    public void   actualizarVista()     { view.mostrarEstudiante(model); }
} 

