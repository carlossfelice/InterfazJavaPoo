package ar.org.centro8.curso.java.utils;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Validator {
    private JTextField  swTxt=null;
    private JLabel      swLbl=null;
    private TextField   fxTxt=null;
    private Label       fxLbl=null;
    public Validator(JTextField swTxt, JLabel swLbl) {
        this.swTxt = swTxt;
        this.swLbl = swLbl;
    }
    public Validator(TextField fxTxt, Label fxLbl) {
        this.fxTxt = fxTxt;
        this.fxLbl = fxLbl;
    }
    private String getText(){
        if(swTxt!=null) return swTxt.getText();
        if(fxTxt!=null) return fxTxt.getText();
        return null;
    }
    private void requestFocus(){
        if(swTxt!=null) {
            swTxt.requestFocus();
            swTxt.selectAll();
        }
        if(fxTxt!=null) {
            fxTxt.requestFocus();
            fxTxt.selectAll();
        }
    }
    private void setText(String text){
        if(swLbl!=null) swLbl.setText(text);
        if(fxLbl!=null) fxLbl.setText(text);
    }
    public boolean length(int length){
        if(getText().length()==length) return true;
        setText("La Longitud del texto debe ser "+length+" caracteres.");
        requestFocus();
        return false;
    }
    public boolean length(int min, int max){
        if(getText().length()>=min && getText().length()<=max) return true;
        setText("La Longitud del texto debe ser entre "+min+" y "+max+" caracteres.");
        requestFocus();
        return false;
    }
    public boolean isInteger(){
        try {
            Integer.parseInt(getText());
            return true;
        } catch (NumberFormatException e) {
            setText("Debe contener un número entero.");
            requestFocus();
            return false;
        }
    }
    public boolean isInteger(int min,int max){
        if(!isInteger()) return false;
        int valor=Integer.parseInt(getText());
        if(valor>=min && valor<=max) return true;
        setText("Debe contener un número entero entre "+min+" y "+max+".");
        requestFocus();
        return false;
    }
}