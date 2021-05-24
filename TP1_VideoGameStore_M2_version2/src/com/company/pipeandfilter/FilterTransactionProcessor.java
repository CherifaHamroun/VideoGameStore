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
    public static Map<String, Client> mapCus = new HashMap<>();
    public static Map<String, StockItem> mapStk = new HashMap<>();
    public static ArrayList<RentedItem> rented = new ArrayList<RentedItem>();
    public static QueryProcessor query = new QueryProcessor(mapCus,mapStk,rented);
    public static TransactionProcessor tran = new TransactionProcessor(mapCus,mapStk,rented);

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
                Integer film = this.query.FindByTitle(params[1]);
                if (film == 1) sendData(params[0] + " " + params[1] );
                else sendData("NoData");
                break;
            case "ndByActor":
                // code block

                List<Film> films = this.query.ndByActor(params[1]);
                Iterator<Film> filmsIterator = films.iterator();
                String data = params[0];
                while(filmsIterator.hasNext()) {
                    data = data + " " + filmsIterator.next().getTitle();
                }
                sendData(data);
                break;
            case "IsCheckedOut":
                // code block
                Integer response = this.query.IsCheckedOut(params[1]);
                sendData(response.toString());

                break;
            case "Solde":
                // code block
                Float solde = this.query.Solde(params[1]);
                sendData(solde.toString());
                break;
            case "OverdueItems":
                // code block
                List<RentedItem> over = this.query.OverdueItems();
                System.out.println(over);
                Iterator<RentedItem> overIterator = over.iterator();
                //Iterator<StockItem> stkIterator = this.tran.getStockList().values().iterator();
                String overdues = "OverdueItems";
                RentedItem a;
                StockItem b;
                while(overIterator.hasNext()) {
                    a = overIterator.next();
                    Iterator<StockItem> stkIterator = this.tran.getStockList().values().iterator();
                    while (stkIterator.hasNext()){
                        b = stkIterator.next();
                        System.out.println(a.getItemID() == b.getItemID());
                        if (a.getItemID() == b.getItemID()) {
                            overdues = overdues + " " + b.getTitle();
                        }
                    }

                }
                sendData(overdues);
                break;
            case "CheckOut":
                // code block
                String sDate1=params[3];
                System.out.println(sDate1);
                Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                this.tran.CheckOut(params[1],date1,params[2]);
                //sendData("Done");
                break;
            case "CheckIn":
                // code block
                this.tran.CheckIn(params[1],params[2]);
                System.out.println(params[2]);
                //sendData("Done");
                break;
            case "AddCustomer":
                // code block
                    this.tran.AddCustomer(params[1]);
                   // sendData("Done");
                break;
            case "AddStock":
                // code block
                this.tran.AddStock(params[1],Float.parseFloat(params[2]),params[3],Integer.parseInt(params[4]));
                //sendData("Done");
                break;
            default:
                // code block
                //sendData("No Operation");
                break;
        }
    }
}
