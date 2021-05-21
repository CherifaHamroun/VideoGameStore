package com.company.pipeandfilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.company.*;

public  class FilterTransactionProcessor extends Filter {

    public FilterTransactionProcessor(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;
    Map<String, Client> mapCus = new HashMap<>();
    Map<String, StockItem> mapStk = new HashMap<>();
    ArrayList<RentedItem> rented = new ArrayList<RentedItem>();
    QueryProcessor query = new QueryProcessor(mapCus,mapStk,rented);
    TransactionProcessor tran = new TransactionProcessor(mapCus,mapStk,rented);

    public String getData(){
        return _dataINPipe.dataOUT();
    }
    public void sendData( String tempData){
        _dataOUTPipe.dataIN(tempData);
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            execute();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    synchronized void execute() throws ParseException {

        String[] params= getData().split(" ");
        switch(params[0]) {
            case "FindByTitle":
                // code block
                Integer film = query.FindByTitle(params[1]);
                sendData(params[0] + " " + film.toString());
                break;
            case "ndByActor":
                // code block
                List<Film> films = query.ndByActor(params[1]);
                Iterator<Film> filmsIterator = films.iterator();
                String data = params[0];
                while(filmsIterator.hasNext()) {
                    data = data + " " + filmsIterator.next().getTitle();
                }
                sendData(data);
                break;
            case "IsCheckedOut":
                // code block
                Integer response = query.IsCheckedOut(params[1]);
                sendData(response.toString());
                break;
            case "Solde":
                // code block
                Float solde = query.Solde(params[1]);
                sendData(solde.toString());
                break;
            case "OverdueItems":
                // code block
                List<RentedItem> over = query.OverdueItems();
                Iterator<RentedItem> overIterator = over.iterator();
                String overdues = "OverdueItems";
                while(overIterator.hasNext()) {
                    overdues = overdues + " " + overIterator.next().getItemID();
                }
                sendData(overdues);
                break;
            case "CheckOut":
                // code block
                String sDate1=params[2];
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                tran.CheckOut(params[1],date1,params[3]);
                sendData("Done");
                break;
            case "CheckIn":
                // code block
                tran.CheckIn(params[1],Integer.parseInt(params[2]),params[3]);
                sendData("Done");
                break;
            case "AddCustomer":
                // code block
                    tran.AddCustomer(params[1]);
                    sendData("Done");
                break;
            case "AddStock":
                // code block
                tran.AddStock(params[1],Float.parseFloat(params[2]),params[3],Integer.parseInt(params[4]));
                sendData("Done");
                break;
            default:
                // code block
                sendData("No Operation");
                break;
        }
    }
}
