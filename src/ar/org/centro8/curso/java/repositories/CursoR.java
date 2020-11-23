package ar.org.centro8.curso.java.repositories;
import ar.org.centro8.curso.java.entities.Curso;
import ar.org.centro8.curso.java.enums.Dias;
import ar.org.centro8.curso.java.enums.Turnos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class CursoR {
    private Connection conn;
    public CursoR(Connection conn) {
        this.conn = conn;
    }
    public void save(Curso curso){
        //No usar esta query
        //String query="insert into curso (titulo,profesor,dia,turno) values "
        //        + "('"+curso.getTitulo()+"','"+curso.getProfesor()+"',"
        //        + "'"+curso.getDia()+"','"+curso.getTurno()+"')";
        
        // A partir de jdk 4 existe la clase PreparedStatement
        if(curso==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                "insert into cursos (titulo,profesor,dia,turno) values (?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia().toString());
            ps.setString(4, curso.getTurno().toString());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) curso.setId(rs.getInt(1));
        } catch (Exception e) { System.out.println(e); }
    }
    public void remove(Curso curso){
        if(curso==null) return;
        String query="delete from cursos where id="+curso.getId();
        try {
            conn.createStatement().execute(query);
        } catch (Exception e) { System.out.println(e); }
    }
    public void update(Curso curso){
        if(curso==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                "update cursos set titulo=?, profesor=?, dia=?, turno=? where id=?"
            );
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia().toString());
            ps.setString(4, curso.getTurno().toString());
            ps.setInt(5, curso.getId());
            ps.execute();
        } catch (Exception e) { System.out.println(e); }
        
    }
    public Curso getById(int id){
        List<Curso>lista=getByFiltro("id="+id);
        return (lista.isEmpty())?null:lista.get(0);
    }
    public List<Curso>getAll(){
        return getByFiltro("1=1");
    }
    public List<Curso>getByTitulo(String titulo){
        return getByFiltro("titulo='"+titulo+"'");
    }
    public List<Curso>getLikeTitulo(String titulo){
        return getByFiltro("titulo like '%"+titulo+"%'");
    }
    public List<Curso>getByProfesor(String profesor){
        return getByFiltro("profesor='"+profesor+"'");
    }
    public List<Curso>getLikeProfesor(String profesor){
        return getByFiltro("profesor like '%"+profesor+"%'");
    }
    public List<Curso>getByDia(Dias dia){
        return getByFiltro("dia='"+dia.toString()+"'");
    }
    public List<Curso>getByTurno(Turnos turno){
        return getByFiltro("turno='"+turno.toString()+"'");
    }
    public List<Curso>getLikeTituloProfesor(String titulo,String profesor){
        return getByFiltro("titulo like '%"+titulo+"%' and profesor like '%"+profesor+"%'");
    }
    public List<Curso>getLikeTituloProfesorDiaTurno(
            String titulo,String profesor, Dias dia, Turnos turno){
        return getByFiltro("titulo like '%"+titulo+"%' and profesor like '%"+profesor+
                "%' and dia='"+dia.toString()+"' and turno='"+turno.toString()+"'");
    }
    private List<Curso>getByFiltro(String filtro){
        List<Curso> lista=new ArrayList();
        String query="select * from cursos where "+filtro;
        System.out.println(query);
        try (ResultSet rs=conn.createStatement().executeQuery(query)){
            while(rs.next()){
                lista.add(
                        new Curso(
                                rs.getInt("id"), 
                                rs.getString("titulo"), 
                                rs.getString("profesor"), 
                                Dias.valueOf(rs.getString("dia").toUpperCase()), 
                                Turnos.valueOf(rs.getString("turno").toUpperCase())
                        )
                );
            }
        } catch (Exception e) { System.out.println(e); }
        return lista;
    }
}
