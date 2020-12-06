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
        double totalPrice = 0.00;
        double discount = 0.00;
        double min = Double.MAX_VALUE;
        int nGelati = 0;
        double totalGelatieBudini = 0.00;

        if(orderList.size()>30)
        {
            throw new RestaurantBillException(
                    "Impossibile ordinare più di 30 elementi"
            );
        }
        for(MenuItem item : orderList) {
            totalPrice += item.getPrice();
            if( item.getType()==ItemType.Gelati) {
                nGelati++;
                totalGelatieBudini += item.getPrice();
                if (item.getPrice() < min) {
                    min = item.getPrice();
                }
            }
            if( item.getType()==ItemType.Budini) {
                totalGelatieBudini += item.getPrice();
            }
        }
        if(nGelati>5) {
            discount += min / 2.00;
        }
        if(totalGelatieBudini>50.00){
            discount+=((totalPrice/100.00)*10);
        }
        if (totalPrice > 0.0 && totalPrice < 10.0) {
            discount -= 0.5;
        }
        return totalPrice-discount;
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

