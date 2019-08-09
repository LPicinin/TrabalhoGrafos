/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhografos;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Aluno
 */
public class TelaPrincipalController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<?> tbMA;
    @FXML
    private TableView<?> tbMI;
    @FXML
    private TableView<?> tbLista;
    @FXML
    private Pane painel;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtClkMouse(MouseEvent event) 
    {
        JFXButton jb = new JFXButton("0");
        jb.setLayoutX(event.getX());
        jb.setLayoutY(event.getY());
        painel.getChildren().add(jb);
    }
    
}
