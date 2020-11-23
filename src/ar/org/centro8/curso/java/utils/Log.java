package ar.org.centro8.curso.java.utils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Log {
    private static String file="log.csv";
    public static void set(Exception e){
        LocalDateTime ldt=LocalDateTime.now();
        String user=System.getProperty("user.name");
        InetAddress inet=null;
        try {
            inet=InetAddress.getLocalHost();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        String reg=ldt+";"+user+";"+inet+";"+e;
        new FileText(file).addLine(reg);
        System.out.println("*************************************");
        System.out.println("*         Ocurrio un error!         *");
        System.out.println("*************************************");
    }
    public static void setJ(Exception e){
        set(e);
        JOptionPane.showMessageDialog(null, "Ocurrio un error!");
    }
}
