package com.company.gui;
import com.company.pipeandfilter.FilterTransactionProcessor;
import com.company.pipeandfilter.Snippet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.company.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

public class ClientController implements Initializable {

    QueryProcessor qp = new QueryProcessor();
    TransactionProcessor tp = new TransactionProcessor();

    @FXML
    private TableView<Client> Customers;

    @FXML
    private TableColumn<Client, Integer> customer_id;

    @FXML
    private TableColumn<Client, String> customer_name;

    @FXML
    private TableColumn<Client, Float> customer_balance;
    @FXML
    private TextField client_name_input;

    ObservableList<Client> client_list;
    int index =-1;

    public void add_client(){
        Snippet.p1.dataIN("AddCustomer"+ " "+client_name_input.getText());
    }
    public void goBack(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer_id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("customerID"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        customer_balance.setCellValueFactory(new PropertyValueFactory<Client,Float>("accountBalance"));
        List<Client> values = new ArrayList<Client>(FilterTransactionProcessor.tran.getClientList().values());
        ObservableList<Client> client_list = FXCollections.observableArrayList(values);
        Customers.setItems(client_list);
    }
}
