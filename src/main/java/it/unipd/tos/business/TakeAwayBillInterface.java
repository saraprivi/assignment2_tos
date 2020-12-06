////////////////////////////////////////////////////////////////////
// SARA PRIVITERA 1201157
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.MenuItem;
import it.unipd.tos.User;

import java.util.List;

public interface TakeAwayBillInterface
{
    double getOrderPrice(List<MenuItem> itemsOrdered, User user)
            throws RestaurantBillException;
}