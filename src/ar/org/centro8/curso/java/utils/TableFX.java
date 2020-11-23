package ar.org.centro8.curso.java.utils;
import java.lang.reflect.Field;
import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class TableFX<E>{
    public void cargar(TableView tbl,List<E>list){
        if(tbl==null) return;
        tbl.getColumns().clear();
        tbl.getItems().clear();
        if(list==null || list.isEmpty()) return;
        E e=list.get(0);
        for(Field f:e.getClass().getDeclaredFields()){
            TableColumn nombre = new TableColumn(f.getName());
            nombre.setCellValueFactory(new PropertyValueFactory(f.getName()));
            tbl.getColumns().add(nombre);
        }
        tbl.getItems().addAll(list);
    }
}