package com.company.pipeandfilter;
import java.util.*;

import com.company.*;

import javax.swing.*;

public  class FilterQueryProcessor extends Filter {

    public FilterQueryProcessor(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

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
        String[] params= getData().split(" ");
        switch(params[0]){
            case "FindByTitle":
                // code block
                sendData(params[0]+" "+params[1]);
                break;
            case "ndByActor":
                // code block
                sendData(params[0]+" "+params[1]);
                break;
            case "IsCheckedOut":
                // code block

                sendData(params[0]+" "+params[1]);
                break;
            case "Solde":
                // code block
                sendData(params[0]+" "+params[1]);
                break;
            case "OverdueItems":
                // code block
                sendData(params[0]);
                break;
            case "CheckOut":
                // code block
                String cho = params[0]+ " " + params[1] + " " + params[2]+ " " + params[3];
                this.sendData(cho);
                break;
            case "CheckIn":
                // code block
                String ch = params[0]+ " " + params[1] + " " + params[2];
                this.sendData(ch);
                break;
            case "AddCustomer":
                // code block
                    String data2 = params[0]+ " " + params[1]+" "+params[2];
                    this.sendData(data2);
                break;
            case "AddStock":
                // code block
                String stocks = params[0]+ " " + params[1] + " " + params[2]+ " " + params[3]+ " " + params[4];
                this.sendData(stocks);
                break;

            default:
                // code block
                break;
        }
    }
}
