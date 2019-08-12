/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
/**
 *
 * @author Aluno
 */
public class Util 
{
    public static void criaColunasDinamicas(TableView t, List<String> cols)
    {
        t.getColumns().clear();
        int qtcolunas = cols.size();
        /**
         * ********************************
         * adiciona as colunas dinamicamente* ********************************
         */
        for (int i = 0; i < qtcolunas; i++)
        {
            final int j = i;
            TableColumn col = new TableColumn(cols.get(i));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>()
            {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param)
                {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            t.getColumns().addAll(col);
        }
    }

    public static void addLinha(TableView tabela, List dados)
    {
        /**
         * ******************************
         * adiciona dados no ObservableList * ******************************
         */
        ObservableList<List> data = FXCollections.observableArrayList();
        data.addAll(dados);
        tabela.getItems().add(data);
    }
}
