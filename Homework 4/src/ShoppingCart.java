import java.util.*;
public class ShoppingCart {
public int total;
Queue cart = new LinkedList();
	public ShoppingCart()
	{
		total = 0;
	}
	
	public void add(String[] item, int price)
	{
		total = total + price;
		cart.add(item);
	}
	
	public void remove(String[] item, int price)
	{
		total = total - price;
		cart.remove();
	}
}
