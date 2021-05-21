package com.company.gui;

import com.company.QueryProcessor;
import com.company.StockItem;
import com.company.TransactionProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverdueItemsListController implements Initializable {

    QueryProcessor qp = new QueryProcessor();
    TransactionProcessor tp = new TransactionProcessor();

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
    private TableColumn<StockItem, String > item_parametre;

    @FXML
    private TableColumn<StockItem, String> is_out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tp.AddStock("Titanic",1000,"Netflix",1);
        System.out.println("test");
        System.out.println(this.tp.getStockList().size());
        item_id.setCellValueFactory(new PropertyValueFactory<StockItem,Integer>("itemID"));
        item_title.setCellValueFactory(new PropertyValueFactory<StockItem,String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<StockItem,Float>("rentalPrice"));
        List<StockItem> values = new ArrayList<StockItem>(tp.getStockList().values());
        ObservableList<StockItem> items_list = FXCollections.observableArrayList(values);
        Items.setItems(items_list);
    }
}