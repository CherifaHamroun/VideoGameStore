package com.company.presentation;
import com.company.metier.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientController implements Initializable,Gestionnaire{

    @FXML
    private TableView<ClientEntity> Customers;

    @FXML
    private TableColumn<ClientEntity, Integer> customer_id;

    @FXML
    private TableColumn<ClientEntity, String> customer_name;

    @FXML
    private TableColumn<ClientEntity, Double> customer_balance;
    @FXML
    private TextField client_name_input;
    @FXML
    private TextField client_solde_input;
    ObservableList<ClientEntity> client_list;
    int index =-1;
    ITransactionMetier tr = new TransactionMetier();
    IQueryMetier qm = new QueryMetier();
    public void OnActionGoBack(ActionEvent e) throws IOException {
        changeSceneButtonPushed(e,"/Menu.fxml");
    }
    public void add_client(ActionEvent e) throws IOException {
        tr.addCustomer(client_name_input.getText(),Double.parseDouble(client_solde_input.getText()));
        changeSceneButtonPushed(e,"/clientList.fxml");
    }
    public void goBack(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
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
        customer_id.setCellValueFactory(new PropertyValueFactory<ClientEntity,Integer>("customerID"));
        customer_name.setCellValueFactory(new PropertyValueFactory<ClientEntity,String>("name"));
        customer_balance.setCellValueFactory(new PropertyValueFactory<ClientEntity,Double>("accountBalance"));
        List<ClientEntity> values = qm.getClients();
        ObservableList<ClientEntity> client_list = FXCollections.observableArrayList(values);
        Customers.setItems(client_list);
    }
}
