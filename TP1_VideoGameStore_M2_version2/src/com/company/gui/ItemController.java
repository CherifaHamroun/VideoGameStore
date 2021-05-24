package com.company.gui;

import com.company.*;
import com.company.pipeandfilter.FilterTransactionProcessor;
import com.company.pipeandfilter.Snippet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemController implements Initializable {


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
    private TableView<StockItem> Items;

    @FXML
    private TableColumn<StockItem, Integer> item_id;

    @FXML
    private TableColumn<StockItem, String> item_title;

    @FXML
    private TableColumn<StockItem, Float> item_price;

    @FXML
    private TableColumn<StockItem, String> item_type;

    @FXML
    private TableColumn<StockItem, String> item_parametre;

    @FXML
    private TableColumn<StockItem, String> is_out;

    @FXML
    private TextField customer_check_name;

    @FXML
    private TextField item_check_title;

    @FXML
    private DatePicker due_date;

    public void overDueItems() throws Exception{
        try {
            Snippet.p1.dataIN("OverdueItems");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("overdueItemsList.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
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
    public void add_item(){
        String type ;
        if (type_input.getText().equalsIgnoreCase("film")){
            type = "1";
        }
        else {
            type ="0";
        }
        Snippet.p1.dataIN("AddStock"+ " "+title_input.getText()+ " "+ price_input.getText()+ " " + param_input.getText()+" "+ type);
    }

    public void CheckIN() {
        Snippet.p1.dataIN("CheckIn"+ " "+customer_check_name.getText()+ " "+ item_check_title.getText());
    }
    public void CheckOUT() {
        System.out.println(due_date.getValue());
        Snippet.p1.dataIN("CheckOut"+ " "+customer_check_name.getText()+ " "+ item_check_title.getText()+ " " + due_date.toString());
    }

    public void ndByActor(){
        Snippet.p1.dataIN("ndByActor" + " " + param_found_input);
    }
    public void FindByTitle(){
        Snippet.p1.dataIN("FindByTitle" + " " + param_found_input);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item_id.setCellValueFactory(new PropertyValueFactory<StockItem,Integer>("itemID"));
        item_title.setCellValueFactory(new PropertyValueFactory<StockItem,String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<StockItem,Float>("rentalPrice"));

        List<StockItem> values = new ArrayList<StockItem>(FilterTransactionProcessor.tran.getStockList().values());
        ObservableList<StockItem> items_list = FXCollections.observableArrayList(values);
        Items.setItems(items_list);

    }
}
