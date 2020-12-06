////////////////////////////////////////////////////////////////////
// SARA PRIVITERA 1201157
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuItemTest
{
    private static MenuItem item;

    @BeforeClass
    public static void beforeClass()
    {
        item = new MenuItem(ItemType.Budini,"Pinguino", 5.00);
    }

    @Test
    public void getTypeTest()
    {
        assertEquals(ItemType.Budini, item.getType());
    }
    @Test
    public void getNameTest()
    {
        assertEquals("Pinguino", item.getName());
    }
    @Test
    public void getPriceTest()
    {
        assertEquals(5.00, item.getPrice(),0);
    }
}