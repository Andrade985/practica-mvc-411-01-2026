package controller;

import model.InscripcionCurso;
import view.InscripcionCursoView;

public class InscripcionCursoController {
      private InscripcionCurso     model;
    private InscripcionCursoView view;

    public InscripcionCursoController(InscripcionCurso model,
                                       InscripcionCursoView view) {
        this.model = model;
        this.view  = view;
    }
    public float  getNotaFinal()         { return model.getNotaFinal(); }
    public void   setNotaFinal(float n)  { model.setNotaFinal(n); }
    public String getEstado()            { return model.getEstado(); }
    public void   setEstado(String e)    { model.setEstado(e); }
    public void   actualizarVista()      { view.mostrarInscripcion(model); }
}

