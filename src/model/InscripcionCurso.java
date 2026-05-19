package model;

public class InscripcionCurso {
      private int    idInscripcion;
    private int    idEstudiante;
    private int    idGrupo;
    private float  notaFinal;
    private String estado;

    public InscripcionCurso(int idInscripcion, int idEstudiante,
                             int idGrupo, float notaFinal, String estado) {
        this.idInscripcion = idInscripcion;
        this.idEstudiante  = idEstudiante;
        this.idGrupo       = idGrupo;
        this.notaFinal     = notaFinal;
        this.estado        = estado;
    }
    public int    getIdInscripcion()          { return idInscripcion; }
    public void   setIdInscripcion(int id)    { this.idInscripcion = id; }
    public int    getIdEstudiante()           { return idEstudiante; }
    public void   setIdEstudiante(int id)     { this.idEstudiante = id; }
    public int    getIdGrupo()                { return idGrupo; }
    public void   setIdGrupo(int id)          { this.idGrupo = id; }
    public float  getNotaFinal()              { return notaFinal; }
    public void   setNotaFinal(float n)       { this.notaFinal = n; }
    public String getEstado()                 { return estado; }
    public void   setEstado(String e)         { this.estado = e; }
}

