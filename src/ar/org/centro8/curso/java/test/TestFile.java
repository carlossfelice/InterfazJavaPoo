package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.utils.FileText;
import java.util.ArrayList;
import java.util.List;

public class TestFile {
    public static void main(String[] args) {
        String file="res/texto.txt";
        FileText fileText=new FileText(file);
        
        fileText.setText("Curso de Java.\n");
        fileText.appendText("Primavera.\n");
        fileText.appendText("Verano.\n");
        fileText.appendText("Otoño.\n");
        fileText.appendText("Invierno.\n");
        fileText.addLine("Rojo.");
        fileText.addLine("Verde.");
        fileText.addLine("Azul.");
        fileText.appendText("Otoño.\n");
        fileText.addLine("Rojo.");
        
        List<String>lista=new ArrayList();
        lista.add("Lunes");
        lista.add("Martes");
        lista.add("Miércoles");
        lista.add("Jueves");
        lista.add("Viernes");
        lista.add("Sábado");
        lista.add("Domingo");
        fileText.addLines(lista);
        
        fileText.removeLine("Lunes");
        
        //System.out.println(fileText.getText());
        //fileText.print();
        //fileText.getLines().forEach(System.out::println);
        //fileText.getLinkedHashSetLines().forEach(System.out::println);
        fileText.getTreeSetLines().forEach(System.out::println);
        
        
    }
}
