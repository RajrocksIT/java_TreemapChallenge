package com.rajeshchinni;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {

//    Modify the program so that adding items to the shopping basket doesn't
//    actually reduce the stock count but, instead, reserves the requested
//    number of items.
//
//    You will need to add a "reserved" field to the StockItem class to store the
//    number of items reserved.
//
//    Items can continue to be added to the basket, but it should not be possible to
//    reserve more than the available stock of any item. An item's available stock
//    is the stock count less the reserved amount.
//
//    The stock count for each item is reduced when a basket is checked out, at which
//    point all reserved items in the basket have their actual stock count reduced.
//
//    Once checkout is complete, the contents of the basket are cleared.
//
//    It should also be possible to "unreserve" items that were added to the basket
//    by mistake.
//
//    The program should prevent any attempt to unreserve more items than were
//    reserved for a basket.
//
//    Add code to Main that will unreserve items after they have been added to the basket,
//    as well as unreserving items that have not been added to make sure that the code
//    can cope with invalid requests like that.
//
//    After checking out the baskets, display the full stock list and the contents of each
//    basket that you created.


        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);  // newly added to check if this is working
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s: stockList.Items().keySet()){
            System.out.println(s);
        }

        Basket RajsBasket = new Basket("Raj");
        sellItem(RajsBasket, "car", 1);
        System.out.println(RajsBasket);

        sellItem(RajsBasket, "car", 1);
        System.out.println(RajsBasket);

        sellItem(RajsBasket, "car", 1);
        sellItem(RajsBasket, "spanner", 5);
        System.out.println(RajsBasket);

        sellItem(RajsBasket, "juice", 4);
        sellItem(RajsBasket, "cup", 12);
        sellItem(RajsBasket, "bread", 1);
        System.out.println(RajsBasket);

        System.out.println(stockList);



    }

    public static int sellItem(Basket basket, String item, int quantity){
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0){   // sellStock is changed to reserveStock
            return basket.addToBasket(stockItem, quantity);          // quantity is changed to basket.addToBasket(stockItem, quantity)
        }
        return 0;
    }


    public static int removeItem(Basket basket, String item, int quantity){
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity){
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    // new method added
    public static void checkOut(Basket basket){
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()){
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }

}
