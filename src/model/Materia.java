package model;

public class Materia {
     private int    idMateria;
    private String nombreMateria;
    private int    creditos;

    public Materia(int idMateria, String nombreMateria, int creditos) {
        this.idMateria     = idMateria;
        this.nombreMateria = nombreMateria;
        this.creditos      = creditos;
    }
    public int    getIdMateria()              { return idMateria; }
    public void   setIdMateria(int id)        { this.idMateria = id; }
    public String getNombreMateria()          { return nombreMateria; }
    public void   setNombreMateria(String n)  { this.nombreMateria = n; }
    public int    getCreditos()               { return creditos; }
    public void   setCreditos(int c)          { this.creditos = c; }
}
