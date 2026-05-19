package model;

public class Grupo {
        private int    idGrupo;
    private int    idMateria;
    private int    idDocente;
    private String aula;
    private String horario;

    public Grupo(int idGrupo, int idMateria, int idDocente,
                 String aula, String horario) {
        this.idGrupo   = idGrupo;
        this.idMateria = idMateria;
        this.idDocente = idDocente;
        this.aula      = aula;
        this.horario   = horario;
    }
    public int    getIdGrupo()            { return idGrupo; }
    public void   setIdGrupo(int id)      { this.idGrupo = id; }
    public int    getIdMateria()          { return idMateria; }
    public void   setIdMateria(int id)    { this.idMateria = id; }
    public int    getIdDocente()          { return idDocente; }
    public void   setIdDocente(int id)    { this.idDocente = id; }
    public String getAula()               { return aula; }
    public void   setAula(String a)       { this.aula = a; }
    public String getHorario()            { return horario; }
    public void   setHorario(String h)    { this.horario = h; }
}
