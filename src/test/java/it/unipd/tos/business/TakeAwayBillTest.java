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

    @BeforeClass
    public static void beforeClass() {
        orderList = new ArrayList<>();
        MenuItem item = new MenuItem(ItemType.Bevande,"coca cola", 2.00);
        orderList.add(item);
        orderList.add(item);
        orderTotal = new TakeAwayBill(orderList,new User(1, "Sara", "Privitera", 21),12300);
    }

    @Test
    public void totalTest() throws RestaurantBillException {
        assertEquals(4.00, orderTotal.getOrderPrice(orderList,orderTotal.getUser()),0.00);
    }
    @Test
    public void getUserTest()
    {
        assertEquals("Sara",orderTotal.getUser().getName());
        assertEquals("Privitera",orderTotal.getUser().getSurname());
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
}