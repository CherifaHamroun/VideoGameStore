package com.company.gui;
import com.company.pipeandfilter.FilterTransactionProcessor;
import com.company.pipeandfilter.Snippet;
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
        Snippet.p1.dataIN("AddCustomer"+ " "+client_name_input.getText());
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
