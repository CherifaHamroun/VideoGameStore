package pipeandfilter;

import com.company.Client;
import com.company.QueryProcessor;
import com.company.RentedItem;
import com.company.StockItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Snippet {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Pipe p1 = new BlockingQueue();
        Pipe p2 = new BlockingQueue();
        Pipe p3 = new BlockingQueue();
        Filter a1 = new FilterQueryProcessor(p1, p2);
        Filter a2 = new FilterTransactionProcessor(p2, p3);
        Thread th1 = new Thread(a1);
        Thread th2 = new Thread(a2);
        while (true){
            Scanner request = new Scanner(System.in);
            String my_str = request.nextLine();
            p1.dataIN(my_str);
            th1.run();
            th2.run();
            System.out.println(p3.dataOUT());
        }


    }
}