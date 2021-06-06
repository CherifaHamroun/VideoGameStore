package com.company.presentation;

import com.company.*;
import com.company.metier.ITransactionMetier;
import com.company.metier.TransactionMetier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemController implements Initializable,Gestionnaire {
ITransactionMetier tm = new TransactionMetier();
    public class Data {
        public Integer ID;
        public String title;
        public Double price;
        public String Type;
        public String param;
        public String isCheckedOut;
        public String getIsCheckedOut() {
            return isCheckedOut;
        }

        public void setIsCheckedOut(String isCheckedOut) {
            this.isCheckedOut = isCheckedOut;
        }

        public Integer getID() {
            return ID;
        }

        public void setID(Integer ID) {
            this.ID = ID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
    @FXML
    private TextField param_found_input;

    @FXML
    private TextField title_input;

    @FXML
    private TextField price_input;

    @FXML
    private TextField type_input;

    @FXML
    private TextField param_input;

    @FXML
    private TableView<Data> Items;

    @FXML
    private TableColumn<Data, Integer> item_id;

    @FXML
    private TableColumn<Data, String> item_title;

    @FXML
    private TableColumn<Data, Double> item_price;

    @FXML
    private TableColumn<Data, String> item_type;

    @FXML
    private TableColumn<Data, String> item_parametre;

    @FXML
    private TableColumn<Data, String> is_out;

    @FXML
    private TextField customer_check_name;

    @FXML
    private TextField item_check_title;

    @FXML
    private DatePicker due_date;

    public void add_item(ActionEvent e) throws IOException {
        int type=0;
        if (type_input.getText().equalsIgnoreCase("film")){
            type = 1;
        }
        else {
            if (type_input.getText().equalsIgnoreCase("jeux")) {
                type = 0;
            }
            else System.out.println("No Matching Type");
        }
        tm.addStock(title_input.getText(),Double.parseDouble(price_input.getText()),param_input.getText(),type);
        changeSceneButtonPushed(e,"/itemList.fxml");

    }
    public void CheckIN(ActionEvent e) throws IOException {
        tm.CheckIn(customer_check_name.getText(),item_check_title.getText());
        changeSceneButtonPushed(e,"/itemList.fxml");
    }
    public void CheckOUT(ActionEvent e) throws IOException {
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(due_date.getValue().atStartOfDay(defaultZoneId).toInstant());
        tm.CheckOut(customer_check_name.getText(),date,item_check_title.getText());
        changeSceneButtonPushed(e,"/itemList.fxml");
    }


    public void goBack(ActionEvent e) throws IOException{
        changeSceneButtonPushed(e,"/Menu.fxml");
    }
    public void FindByTitle(ActionEvent e) throws IOException{

        changeSceneButtonPushed(e,"/films_games.fxml");

    }
    public void ndByActor(ActionEvent e) throws IOException{

        changeSceneButtonPushed(e,"/films_games.fxml");

    }
    public void overDueItems(ActionEvent e) throws IOException{

        changeSceneButtonPushed(e,"/overdueItemsList.fxml");

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("OK");
        item_id.setCellValueFactory(new PropertyValueFactory<Data,Integer>("ID"));
        item_title.setCellValueFactory(new PropertyValueFactory<Data,String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<Data,Double>("price"));
        item_type.setCellValueFactory(new PropertyValueFactory<Data,String>("Type"));
        item_parametre.setCellValueFactory(new PropertyValueFactory<Data,String>("param"));
        is_out.setCellValueFactory(new PropertyValueFactory<Data,String>("isCheckedOut"));

    }
}
