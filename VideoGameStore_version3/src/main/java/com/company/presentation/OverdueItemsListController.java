package com.company.presentation;
import com.company.metier.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverdueItemsListController implements Initializable,Gestionnaire {
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
    private TableColumn<Data, String > item_parametre;
    IQueryMetier qm = new QueryMetier();
    @FXML
    private TableColumn<Data, String> is_out;
    public void OnActionGoBack(ActionEvent e) throws IOException {
        changeSceneButtonPushed(e,"/itemList.fxml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item_id.setCellValueFactory(new PropertyValueFactory<Data, Integer>("ID"));
        item_title.setCellValueFactory(new PropertyValueFactory<Data, String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<Data, Float>("price"));
        item_type.setCellValueFactory(new PropertyValueFactory<Data, String>("Type"));
        item_parametre.setCellValueFactory(new PropertyValueFactory<Data, String>("param"));
        is_out.setCellValueFactory(new PropertyValueFactory<Data, String>("isCheckedOut"));
        List<RentedItemEntity> values = new ArrayList<RentedItemEntity>(qm.overdueItems());
        ObservableList<RentedItemEntity> over = FXCollections.observableArrayList(values);
        List<Data> l = new ArrayList<>();
        for (int i=0;i<values.size();i++){
            Data data = new Data();
            data.setID(values.get(i).getItemId());
            String[] s= qm.getTypeParam(values.get(i).getItemId()).split(";");
            data.setParam(s[1]);
            data.setType(s[0]);
            StockItemEntity stk = qm.findItemById(values.get(i).getItemId());
            data.setPrice(stk.getRentalPrice());
            data.setTitle(stk.getTitle());
            String isC = qm.isCheckedOut(values.get(i).getItemId());
            data.setIsCheckedOut(isC);
            l.add(data);
        }
        ObservableList<Data> items_list = FXCollections.observableArrayList(l);
        Items.setItems(items_list);
    }
    }