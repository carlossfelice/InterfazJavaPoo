package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connector.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnector {
    public static void main(String[] args) throws Exception{
        Connection conn=Connector.getConnection();
        Statement statement=conn.createStatement();
        String query="insert into cursos (titulo,profesor,dia,turno) values "
                + "('Java','Ríos','Lunes','Noche')";
        statement.execute(query);
        
        Connector.getConnection().createStatement().execute(
                "insert into cursos (titulo,profesor,dia,turno) values "
                        + "('PHP','Gomez','Martes','Tarde')"
        );
        
        
        //select
        ResultSet rs=Connector.getConnection().createStatement().executeQuery(
                "select * from cursos"
        );
        while(rs.next()){
            System.out.println(
                    rs.getInt("id")+"\t"+
                    rs.getString("titulo")+"\t"+
                    rs.getString("profesor")+"\t"+
                    rs.getString("dia")+"\t"+
                    rs.getString("turno")
            );
        }
        
        
    }
}