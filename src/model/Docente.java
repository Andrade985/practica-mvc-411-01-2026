package model;

public class Docente {
      private int    idDocente;
    private String nombre;
    private String especialidad;

    public Docente(int idDocente, String nombre, String especialidad) {
        this.idDocente    = idDocente;
        this.nombre       = nombre;
        this.especialidad = especialidad;
    }
    public int    getIdDocente()               { return idDocente; }
    public void   setIdDocente(int id)         { this.idDocente = id; }
    public String getNombre()                  { return nombre; }
    public void   setNombre(String n)          { this.nombre = n; }
    public String getEspecialidad()            { return especialidad; }
    public void   setEspecialidad(String e)    { this.especialidad = e; }
}
