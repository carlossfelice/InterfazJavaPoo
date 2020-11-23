package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connector.Connector;
import ar.org.centro8.curso.java.entities.Alumno;
import ar.org.centro8.curso.java.entities.Curso;
import ar.org.centro8.curso.java.enums.Dias;
import ar.org.centro8.curso.java.enums.Turnos;
import ar.org.centro8.curso.java.repositories.AlumnoR;
import ar.org.centro8.curso.java.repositories.CursoR;
import java.util.*;

public class TestRepository {
    public static void main(String[] args) {
        CursoR cr=new CursoR(Connector.getConnection());
        Curso curso=new Curso("HTML","Rivera",Dias.LUNES,Turnos.TARDE);
        cr.save(curso);
        System.out.println(curso);
     
        cr.remove(cr.getById(3));
        
        curso=cr.getById(4);
        if(curso!=null){
            curso.setProfesor("Garcia");
            curso.setTitulo("C#");
            cr.update(curso);
        }
        
        //cr.getLikeProfesor("ri").forEach(System.out::println);
        //cr.getLikeTituloProfesorDiaTurno("", "", Dias.LUNES, Turnos.NOCHE)
        //        .forEach(System.out::println);
        cr.getAll().forEach(System.out::println);
        
        
        /*
        crear un programa en Java (import java.util.*;) que pide al usuario ingresar un nombre de un color en inglés y la aplicación tiene que traducirla al español
        Máximo 10 colores distintos
        */
        /*
        Map<String,String>mapaColores=new HashMap();
        mapaColores.put("red", "rojo");
        mapaColores.put("blue", "azul");
        mapaColores.put("yellow", "amarillo");
        mapaColores.put("green", "verde");
        System.out.println("Ingrese su color en ingles:");
        String color=new Scanner(System.in).nextLine();
        System.out.println("color:"+mapaColores.get(color.toLowerCase()));
        */
        
        System.out.println("**************************************************");
        AlumnoR ar=new AlumnoR(Connector.getConnection());
        Alumno alumno=new Alumno("Laura","Mendez",31,1);
        ar.save(alumno);
        System.out.println(alumno);
        
        ar.delete(ar.getById(3));
        
        alumno=ar.getById(5);
        if(alumno!=null){
            alumno.setNombre("Roxana");
            alumno.setApellido("Villegas");
            ar.update(alumno);
        }
        
        ar.getAll().forEach(System.out::println);
        
    }
}
