package ar.org.centro8.curso.java.entities;
import ar.org.centro8.curso.java.enums.Dias;
import ar.org.centro8.curso.java.enums.Turnos;
public class Curso {
    private int id;
    private String titulo;
    private String profesor;
    private Dias dia;
    private Turnos turno;
    public Curso() { }
    public Curso(String titulo, String profesor, Dias dia, Turnos turno) {
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }
    public Curso(int id, String titulo, String profesor, Dias dia, Turnos turno) {
        this.id = id;
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }
    @Override public String toString() {
        return id + "\t" + titulo + "\t" + profesor + "\t" + dia + "\t" + turno;
    }
    public int getId()                          { return id;                }
    public void setId(int id)                   { this.id = id;             }
    public String getTitulo()                   { return titulo;            }
    public void setTitulo(String titulo)        { this.titulo = titulo;     }
    public String getProfesor()                 { return profesor;          }
    public void setProfesor(String profesor)    { this.profesor = profesor; }
    public Dias getDia()                        { return dia;               }
    public void setDia(Dias dia)                { this.dia = dia;           }
    public Turnos getTurno()                    { return turno;             }
    public void setTurno(Turnos turno)          { this.turno = turno;       }
}