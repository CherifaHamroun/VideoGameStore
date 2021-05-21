package com.company.gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.company.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
        try{
            this.tp.AddCustomer(client_name_input.getText());
            JOptionPane.showMessageDialog(null,"Client ajouté !");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tp.AddCustomer("Katia");
        this.tp.AddCustomer("Cherifa");
        this.tp.AddCustomer("Yacine");
        System.out.println("test");
        System.out.println(this.tp.getClientList().size());
        customer_id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("customerID"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        customer_balance.setCellValueFactory(new PropertyValueFactory<Client,Float>("accountBalance"));
        List<Client> values = new ArrayList<Client>(tp.getClientList().values());
        ObservableList<Client> client_list = FXCollections.observableArrayList(values);
        Customers.setItems(client_list);
    }
}