package com.company.gui;

import com.company.*;
import com.company.pipeandfilter.FilterTransactionProcessor;
import com.company.pipeandfilter.Snippet;
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
import java.util.*;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemController implements Initializable,Gestionnaire {

    public class Data {
        public Integer ID;
        public String title;
        public Float price;
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

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
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
    private TableColumn<Data, Float> item_price;

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


    public void add_item2(){
        String type ;
        if (type_input.getText().equalsIgnoreCase("film")){
            type = "1";
        }
        else {
            type ="0";
        }
        Snippet.p1.dataIN("AddStock"+ " "+title_input.getText()+ " "+ price_input.getText()+ " " + param_input.getText()+" "+ type);
    }
    public void add_item(ActionEvent e) throws IOException {
        String type = null;
        if (type_input.getText().equalsIgnoreCase("film")){
            type = "1";
            Snippet.p1.dataIN("AddStock"+ " "+title_input.getText()+ " "+ price_input.getText()+ " " + param_input.getText()+" "+ type);

        }
        else {
            if (type_input.getText().equalsIgnoreCase("jeux")) {
                type = "0";
                Snippet.p1.dataIN("AddStock"+ " "+title_input.getText()+ " "+ price_input.getText()+ " " + param_input.getText()+" "+ type);

            }
            else System.out.println("No Matching Type");
        }
        changeSceneButtonPushed(e,"itemList.fxml");

    }
    public void CheckIN(ActionEvent e) throws IOException {
        Snippet.p1.dataIN("CheckIn"+ " "+customer_check_name.getText()+ " "+ item_check_title.getText());
        changeSceneButtonPushed(e,"itemList.fxml");
    }
    public void CheckOUT(ActionEvent e) throws IOException {
        Snippet.p1.dataIN("CheckOut"+ " "+customer_check_name.getText()+ " "+ item_check_title.getText()+ " " + due_date.getValue().toString());
        changeSceneButtonPushed(e,"itemList.fxml");
    }


    public void goBack(ActionEvent e) throws IOException{
        changeSceneButtonPushed(e,"Menu.fxml");
    }
    public void FindByTitle(ActionEvent e) throws IOException{

        Snippet.p1.dataIN("FindByTitle" + " " + param_found_input.getText());
        changeSceneButtonPushed(e,"films_games.fxml");

    }
    public void ndByActor(ActionEvent e) throws IOException{

        Snippet.p1.dataIN("ndByActor" + " " + param_found_input.getText());
        changeSceneButtonPushed(e,"films_games.fxml");

    }
    public void overDueItems(ActionEvent e) throws IOException{

        Snippet.p1.dataIN("OverdueItems");
        changeSceneButtonPushed(e,"overdueItemsList.fxml");

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("OK");
        item_id.setCellValueFactory(new PropertyValueFactory<Data,Integer>("ID"));
        item_title.setCellValueFactory(new PropertyValueFactory<Data,String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<Data,Float>("price"));
        item_type.setCellValueFactory(new PropertyValueFactory<Data,String>("Type"));
        item_parametre.setCellValueFactory(new PropertyValueFactory<Data,String>("param"));
        is_out.setCellValueFactory(new PropertyValueFactory<Data,String>("isCheckedOut"));
        List<StockItem> values = new ArrayList<StockItem>(FilterTransactionProcessor.tran.getStockList().values());
        List<Data> l = new ArrayList<>();
        for (int i=0;i<values.size();i++){
            Data data = new Data();
            data.setID(values.get(i).getItemID());
            data.setTitle(values.get(i).getTitle());
            data.setPrice(values.get(i).getRentalPrice());
            String t = values.get(i).getClass().getName().replace("com.company.","");
            data.setType(t);
            System.out.println(t);
            if (t=="Film") {
                Film f = (Film)values.get(i);
                data.setParam(f.getActor());
            }
            else {
                if (t=="Jeux") {
                    Jeux j = (Jeux) values.get(i);
                    data.setParam(j.getPlatform());
                }
            }
            Snippet.p1.dataIN("IsCheckedOut"+" "+ data.getTitle());
            String isC = Snippet.p3.dataOUT();
            if (isC.equals("1") )isC = "Yes";
            else isC = "No";
            data.setIsCheckedOut(isC);
            l.add(data);
        }
        ObservableList<Data> items_list = FXCollections.observableArrayList(l);
        Items.setItems(items_list);
    }
}
