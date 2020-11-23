package ar.org.centro8.curso.java.guifx;
import ar.org.centro8.curso.java.connector.Connector;
import ar.org.centro8.curso.java.entities.Alumno;
import ar.org.centro8.curso.java.entities.Curso;
import ar.org.centro8.curso.java.repositories.AlumnoR;
import ar.org.centro8.curso.java.repositories.CursoR;
import ar.org.centro8.curso.java.utils.TableFX;
import ar.org.centro8.curso.java.utils.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
public class FXMLDocumentController implements Initializable {
    private AlumnoR ar;
    private CursoR cr;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtEdad;
    @FXML private ComboBox<Curso> cmbCursos;
    @FXML private Label lblInfo;
    @FXML private TableView<Alumno> tblAlumnos;
    @FXML private TextField txtBuscarApe;
    @Override public void initialize(URL url, ResourceBundle rb) {
        ar=new AlumnoR(Connector.getConnection());
        cr=new CursoR(Connector.getConnection());
        cargar();
    }    
    private void cargar(){
        //cargar cmbCursos
        cmbCursos.getItems().clear();
        cmbCursos.getItems().addAll(cr.getAll());
        cmbCursos.getSelectionModel().selectFirst();
        
        //cargar tblAlumnos
        TableFX<Alumno> table=new TableFX();
        table.cargar(tblAlumnos, ar.getAll());
    }
    @FXML private void agregar(ActionEvent event) {
        if(!validar()) return;
        Alumno alumno=new Alumno(
                txtNombre.getText(),
                txtApellido.getText(),
                Integer.parseInt(txtEdad.getText()),
                cmbCursos.getValue().getId()
        );
        ar.save(alumno);
        lblInfo.setText("Se ingreso el alumno id: "+alumno.getId());
        cargar();
        limpiar();
    }
    private void limpiar(){
        txtNombre.setText("");
        txtNombre.requestFocus();
        txtApellido.setText("");
        txtEdad.setText("");
        cmbCursos.getSelectionModel().selectFirst();
    }
    private boolean validar(){
        //valida nombre string entre 2 y 20 caracteres.
        if(!new Validator(txtNombre,lblInfo).length(2, 20))     return false;
        //valida apellido String entre 2 y 20 caracteres.
        if(!new Validator(txtApellido,lblInfo).length(2, 20))   return false;
        //valida edad entero entre 18 y 120 años.
        if(!new Validator(txtEdad,lblInfo).isInteger(18, 120))  return false;
        return true;
    }
    @FXML private void buscarApellido(KeyEvent event) {
        TableFX<Alumno>table=new TableFX();
        table.cargar(tblAlumnos, ar.getLikeApellido(txtBuscarApe.getText()));
    }
}