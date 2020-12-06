////////////////////////////////////////////////////////////////////
// SARA PRIVITERA 1201157
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.ItemType;
import it.unipd.tos.MenuItem;
import it.unipd.tos.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TakeAwayBill implements TakeAwayBillInterface
{
    private List<MenuItem> orderList;
    private User user;
    private int timeSec;

    public TakeAwayBill(List<MenuItem> orderList, User user, int timeSec)
    {
        this.orderList = new ArrayList<>(orderList);
        this.user = user;
        this.timeSec = timeSec;
    }

    public double getOrderPrice(List<MenuItem> orderList, User user)
            throws RestaurantBillException {
        double totalPrice = 0.0;

        for(MenuItem item : orderList) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public List<MenuItem> getLista()
    {
        return orderList;
    }

    public User getUser() {
        return user;
    }

    public int getTimeSec() {
        return timeSec;
    }
}

