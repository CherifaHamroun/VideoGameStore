package com.company.pipeandfilter;

import com.company.QueryProcessor;
import com.company.TransactionProcessor;
import com.company.gui.ClientController;
import com.sun.javafx.application.PlatformImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public  class FilterStoreIn extends Filter {

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

    ClientController cc = new ClientController();

    public FilterStoreIn(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }


    public String getData(){
        return _dataINPipe.dataOUT();
    }

    public void sendData( String tempData){

        _dataOUTPipe.dataIN(tempData);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        execute();
    }
    public void cases(String params, QueryProcessor query, TransactionProcessor tran){

    }
    @Override
    synchronized void execute() {
        Platform.setImplicitExit(false);
        // create JavaFX scene
        PlatformImpl.startup(() -> {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/Menu.fxml"));
                root = (Parent) fxmlLoader.load();
                Scene scene = new Scene(root, 630, 400);
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        }
    }
