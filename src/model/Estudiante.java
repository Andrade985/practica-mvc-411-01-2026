package model;

public class Estudiante {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String email;

    public Estudiante(int idEstudiante, String nombre,
                      String apellido, String email) {
        this.idEstudiante = idEstudiante;
        this.nombre       = nombre;
        this.apellido     = apellido;
        this.email        = email;
    }
    public int    getIdEstudiante()          { return idEstudiante; }
    public void   setIdEstudiante(int id)    { this.idEstudiante = id; }
    public String getNombre()                { return nombre; }
    public void   setNombre(String n)        { this.nombre = n; }
    public String getApellido()              { return apellido; }
    public void   setApellido(String a)      { this.apellido = a; }
    public String getEmail()                 { return email; }
    public void   setEmail(String e)         { this.email = e; }
}
    

