////////////////////////////////////////////////////////////////////
// SARA PRIVITERA 1201157
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.RestaurantBillException;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.ItemType;
import it.unipd.tos.MenuItem;
import it.unipd.tos.User;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillTest
{
    public static List<MenuItem> orderList;
    public static TakeAwayBill orderTotal;
    public static List<MenuItem> perScontoGelato;
    public static TakeAwayBill orderDiscountTest;
    public static List<MenuItem> perSconto10;
    public static TakeAwayBill orderDiscountT10Test;
    public static List<MenuItem> orderListLessThan10Test;
    public static TakeAwayBill orderLessThan10Test;
    public static List<TakeAwayBill> orders;
    public static List<MenuItem> orderListGratis;
    public static TakeAwayBill orderGratis;


    @BeforeClass
    public static void beforeClass() {
        orderList = new ArrayList<>();
        MenuItem item = new MenuItem(ItemType.Bevande,"coca cola", 2.00);
        for( int i=0; i<5; i++){
            orderList.add(item);
        }
        orderTotal = new TakeAwayBill(orderList,new User(1, "Sara", "Privitera", 21),12300);

        //metà sul prodotto meno caro #2
        perScontoGelato = new ArrayList<>();
        TakeAwayBill order;
        for (int i=0; i<6; ++i) {
            MenuItem item1 = new MenuItem(ItemType.Gelati, "Coppa Nafta", 5.00);
            MenuItem item2 = new MenuItem(ItemType.Budini, "Pinguino", 5.00);
            MenuItem item3 = new MenuItem(ItemType.Bevande, "Coca cola", 2.00);
            perScontoGelato.add(item1);
            perScontoGelato.add(item2);
            perScontoGelato.add(item3);
        }
        User user = new User(1, "Sara", "Privitera", 21);
        orderDiscountTest = new TakeAwayBill(perScontoGelato, user, 0);

        //sconto 10% #3
        perSconto10 = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            MenuItem item4 = new MenuItem(ItemType.Gelati, "Coppa Nafta", 15.00);
            MenuItem item5 = new MenuItem(ItemType.Budini, "Pinguino", 15.00);
            MenuItem item6 = new MenuItem(ItemType.Bevande, "Coca cola", 20.00);
            perSconto10.add(item4);
            perSconto10.add(item5);
            perSconto10.add(item6);
        }
        User user2 = new User(2,"Sara","Privitera",17);
        orderDiscountT10Test = new TakeAwayBill(perSconto10, user2, 0);

        //sconto se meno di 10
        orderListLessThan10Test = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            MenuItem item7 = new MenuItem(ItemType.Gelati, "Coppa Nafta", 1.50);
            MenuItem item8 = new MenuItem(ItemType.Budini, "Pinguino", 1.00);
            MenuItem item9 = new MenuItem(ItemType.Bevande, "Coca cola", 2.00);
            orderListLessThan10Test.add(item7);
            orderListLessThan10Test.add(item8);
            orderListLessThan10Test.add(item9);
        }
        User user3 = new User(2,"Sara","Privitera",17);
        orderLessThan10Test = new TakeAwayBill(orderListLessThan10Test, user3, 0);

        //10 ordini gratis #6
        orders = new ArrayList<>();
        orderListGratis = new ArrayList<>();
        for (int i=0; i<6; ++i) {
            MenuItem item10 = new MenuItem(ItemType.Gelati, "Coppa Nafta", 4.00);
            MenuItem item11 = new MenuItem(ItemType.Budini, "Pinguino", 10.00);
            orderListGratis.add(item10);
            orderListGratis.add(item11);
        }
        String [] names = {"a","b","c","d","e","f","g","h","i","l","m","n"};
        String [] surnames = {"o","p","q","r","s","t","u","v","w","x","y","z"};
        for (int j=0; j<12; ++j)
        {
            User userTmp = new User(j,names[j],surnames[j],17-j);
            TakeAwayBill gratis = new TakeAwayBill(orderListGratis,userTmp,68000+j);
            orders.add(gratis);
        }
    }

    @Test
    public void totalTest() throws RestaurantBillException {
        assertEquals(10.00, orderTotal.getOrderPrice(orderList,orderTotal.getUser()),0.00);
    }
    @Test
    public void getUserTest()
    {
        assertEquals("Sara",orderTotal.getUser().getName());
        assertEquals("Privitera",orderTotal.getUser().getSurname());
    }
    @Test
    public void discount10Test() throws RestaurantBillException{
            assertEquals(90.00, orderDiscountT10Test.getOrderPrice(orderDiscountT10Test.getLista(), orderDiscountT10Test.getUser()), 0.00);
    }

    @Test
    public void getTimeSecTest()
    {
        assertEquals(12300,orderTotal.getTimeSec());
    }

    @Test
    public void getListaTest()
    {
        assertEquals(orderTotal.getLista(),orderTotal.getLista());
    }

    @Test
    public void orderDiscountTest() {
        try {
            assertEquals(62.30, orderDiscountTest.getOrderPrice(perScontoGelato, orderDiscountTest.getUser()), 0);
        } catch (RestaurantBillException e) {
            e.getMessage();
        }
    }

    @Test(expected = RestaurantBillException.class)
    public void testNoMoreThan30ElementsAllowed() throws RestaurantBillException {
        List<MenuItem> items = List.of(
                new MenuItem(ItemType.Gelati, "gelato 1", 1),
                new MenuItem(ItemType.Gelati, "gelato 2", 2),
                new MenuItem(ItemType.Gelati, "gelato 3", 3),
                new MenuItem(ItemType.Gelati, "gelato 4", 1),
                new MenuItem(ItemType.Gelati, "gelato 5", 2),
                new MenuItem(ItemType.Gelati, "gelato 6", 3),
                new MenuItem(ItemType.Gelati, "gelato 7", 1),
                new MenuItem(ItemType.Gelati, "gelato 8", 2),
                new MenuItem(ItemType.Bevande, "bevanda 9", 3),
                new MenuItem(ItemType.Gelati, "gelato 10", 1),
                new MenuItem(ItemType.Gelati, "gelato 11", 1),
                new MenuItem(ItemType.Gelati, "gelato 12", 2),
                new MenuItem(ItemType.Budini, "budino 13", 1),
                new MenuItem(ItemType.Gelati, "gelato 14", 3),
                new MenuItem(ItemType.Gelati, "gelato 15", 4),
                new MenuItem(ItemType.Gelati, "gelato 16", 6),
                new MenuItem(ItemType.Gelati, "gelato 17", 1),
                new MenuItem(ItemType.Bevande, "bevanda 18", 1),
                new MenuItem(ItemType.Gelati, "gelato 19", 1),
                new MenuItem(ItemType.Gelati, "gelato 20", 2),
                new MenuItem(ItemType.Gelati, "gelato 21", 2),
                new MenuItem(ItemType.Gelati, "gelato 22", 2),
                new MenuItem(ItemType.Gelati, "gelato 23", 3),
                new MenuItem(ItemType.Gelati, "gelato 24", 2),
                new MenuItem(ItemType.Gelati, "gelato 25", 2),
                new MenuItem(ItemType.Gelati, "gelato 26", 2),
                new MenuItem(ItemType.Gelati, "gelato 27", 2),
                new MenuItem(ItemType.Gelati, "gelato 28", 2),
                new MenuItem(ItemType.Gelati, "gelato 29", 9),
                new MenuItem(ItemType.Gelati, "gelato 30", 3),
                new MenuItem(ItemType.Budini, "budino 4", 1),
                new MenuItem(ItemType.Bevande, "bevanda 5", 3)
        );
        TakeAwayBill order = new TakeAwayBill(items, new User(5, "Sara", "Privitera",21),0);
        order.getOrderPrice(items, new User(5, "Sara", "Privitera",21));
    }

    @Test
    public void lessThan10Test() {
        try {
            assertEquals(9.50, orderLessThan10Test.getOrderPrice(orderLessThan10Test.getLista(), orderLessThan10Test.getUser()), 0);
        } catch (RestaurantBillException e) {
            e.getMessage();
        }
    }

    @Test
    public void verifyGratis()
    {
        assertEquals(10, orders.get(0).gratisOrder(orders).size());
        List<TakeAwayBill> tmp = orders.get(0).gratisOrder(orders);
        try {
            for(int i=0;i< tmp.size();++i)
                assertEquals(0.00, tmp.get(i).getOrderPrice(tmp.get(i).getLista(), tmp.get(i).getUser()), 0);
        } catch (RestaurantBillException e) {
            e.getMessage();
        }
    }

}