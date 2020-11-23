package ar.org.centro8.curso.java.repositories;
import ar.org.centro8.curso.java.entities.Alumno;
import ar.org.centro8.curso.java.entities.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class AlumnoR {
    private Connection conn;
    public AlumnoR(Connection conn) {
        this.conn = conn;
    }
    public void save(Alumno alumno){
        if(alumno==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                "insert into alumnos (nombre,apellido,edad,idCurso) values (?,?,?,?)",1);
            ps.setString(1,alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3,alumno.getEdad());
            ps.setInt(4, alumno.getIdCurso());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) alumno.setId(rs.getInt(1));
        } catch (Exception e) { System.out.println(e); }
    }
    public void delete(Alumno alumno){
        if(alumno==null) return;
        String query="delete from alumnos where id="+alumno.getId();
        try {
            conn.createStatement().execute(query);
        } catch (Exception e) { System.out.println(e); }
    }
    public void update(Alumno alumno){
        if(alumno==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                "update alumnos set nombre=?, apellido=?, edad=?, idCurso=? where id=?"
            );
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getIdCurso());
            ps.setInt(5, alumno.getId());
            ps.execute();
        } catch (Exception e) { System.out.println(e); }
    }
    public Alumno getById(int id){
        List<Alumno>lista=getByFiltro("id="+id);
        return (lista.isEmpty())?null:lista.get(0);
    }
    public List<Alumno> getAll(){return getByFiltro("1=1");}
    public List<Alumno> getByApellido(String apellido){
        return getByFiltro("apellido='"+apellido+"'");
    }
    public List<Alumno> getLikeApellido(String apellido){
        return getByFiltro("apellido like '%"+apellido+"%'");
    }
    public List<Alumno> getByApellidoNombre(String apellido,String nombre){
        return getByFiltro("apellido ='"+apellido+"' and nombre='"+nombre+"'");
    }
    public List<Alumno> getLikeApellidoNombre(String apellido, String nombre) { 
        return getByFiltro("apellido like '%"+apellido+"%' and "
                + "nombre like '%"+apellido+"%'"); 
    }
    public List<Alumno> getByCurso(Curso curso){ return getByCurso(curso.getId()); }
    public List<Alumno> getByCurso(int idCurso){ return getByFiltro("idCurso="+idCurso); }
    private List<Alumno> getByFiltro(String filtro){
        List<Alumno>lista=new ArrayList();
        String query="select * from alumnos where "+filtro;
        try (ResultSet rs=conn.createStatement().executeQuery(query);){
            while(rs.next()){
                lista.add(
                        new Alumno(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getInt("edad"),
                                rs.getInt("idCurso")
                        )
                );
            }
        } catch (Exception e) { System.out.println(e); }
        return lista;
    }
}
