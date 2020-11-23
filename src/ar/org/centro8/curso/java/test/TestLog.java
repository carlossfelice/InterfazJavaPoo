package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.utils.Log;

public class TestLog {
    public static void main(String[] args) {
        try {
            System.out.println(10/0);
        } catch (Exception e) {
            //Log.set(e);
            Log.setJ(e);
        }
    }
}
