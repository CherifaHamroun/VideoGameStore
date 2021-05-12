package com.company;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import sun.net.TelnetOutputStream;

public class Main {

    public static void main(String[] args) {
        Map<String, Client> mapCus = new HashMap<>();
        Client cherifa = new Client(1,10,"Cherifa");
        Client katia = new Client(2,10,"Katia");
        Client fatima = new Client(3,10,"Fatima");
        Client mohand = new Client(4,10,"Mohand");
        mapCus.put("Cherifa", cherifa);
        mapCus.put("Katia", katia);
        mapCus.put("Fatima", fatima);
        mapCus.put("Mohand", mohand);
        Map<String, StockItem> mapStk = new HashMap<>();
        Film a = new Film(1,2,"a","aa");
        Film c = new Film(3,2,"c","aa");
        Jeux b = new Jeux(2,2,"b","ba");
        mapStk.put("aa", a);
        mapStk.put("ba", b);
        mapStk.put("ca",c);
        ArrayList<RentedItem> rented = new ArrayList<RentedItem>();
        QueryProcessor query = new QueryProcessor(mapCus,mapStk,rented);
        // Test 1
        System.out.println(query.FindByTitle("a"));
        // Test 2
        List<Film> films = query.ndByActor("aa");
        Iterator<Film> filmsIterator = films.iterator();
        while(filmsIterator.hasNext()) {
            System.out.println(filmsIterator.next().getTitle());
        }
        // Test 3
        System.out.println(query.IsCheckedOut(a));
        RentedItem ar =new RentedItem(1,1,new Date(0));
        rented.add(ar);
        System.out.println(query.IsCheckedOut(a));
        // Test 4
        System.out.println(query.Solde("Cherifa"));
        // Test 5
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        RentedItem br =new RentedItem(1,2,cal.getTime());
        rented.add(br);
        cal.add(Calendar.DATE, 2);
        RentedItem cr =new RentedItem(1,3,cal.getTime());
        rented.add(cr);
        System.out.println(query.OverdueItems());
        System.out.println(query.IsCheckedOut(c));
    
    //**********************************************************************************************************************************************
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        TransactionProcessor tp = new TransactionProcessor(mapCus,mapStk,rented);
      //---------------------------------Test AddCustomer ---------------------------------------------
    	String nom="Katia";
    	tp.AddCustomer(nom);
    	System.out.println(tp.getClientList().size());
    	for (String keys : tp.getClientList().keySet())  
    	{
    	   System.out.println(keys + ":"+ tp.getClientList().get(keys));
    	}
    	
    	//----------------------------------Test AddStock-------------------------------------------------
    	String titre="Film1";
    	float prix = (float) 1.0; 
    	String act="Johnny Depp";
    	int type=0;
    	tp.AddStock(titre,prix,act,type);
    	System.out.println(tp.getStockList().size());
    	for (String keys : tp.getStockList().keySet())  
    	{
    	   System.out.println(keys + ":"+ tp.getStockList().get(keys));
    	}
    	
    	//-----------------------------------Test CheckOut--------------------------------------------------
    	String cl="Katia";
    	Date date= new Date(0);
    	String itemTitle="Film1";
    	tp.CheckOut(cl, date, itemTitle);
    	System.out.println(tp.getRentedList().size());
    	for(int i = 0; i < tp.getRentedList().size(); i++) {
            System.out.println(tp.getRentedList().get(i).toString());
        }
    	for (String keys : tp.getClientList().keySet())  
    	{
    	   System.out.println(keys + ":"+ tp.getClientList().get(keys));
    	}
    	
    	//----------------------------------Test CheckIn----------------------------------------------------
    	System.out.println(tp.getRentedList().size());
    	int itemid=1;
    	tp.CheckIn(cl,itemid,itemTitle);
    	System.out.println(tp.getRentedList().size());
    	for(int i = 0; i < tp.getRentedList().size(); i++) {
            System.out.println(tp.getRentedList().get(i).toString());
        }
    	for (String keys : tp.getClientList().keySet())  
    	{
    	   System.out.println(keys + ":"+ tp.getClientList().get(keys));
    	}
    	
    }
}