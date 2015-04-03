import java.util.*;
public class ShoppingCart {
public int total;
List cart = new ArrayList();
int index;
int index_item;
	public ShoppingCart()
	{
		total = 0;
		index = 0;
	}
	
	public void add(String item, int price)
	{
		total = total + price;
		cart.add(index,item);
		index++;
	}
	
	public void remove(String item, int price)
	{
		total = total - price;
		index_item = cart.indexOf(item);
		cart.remove(index_item);
		index --;
	}

	public int getTotal()
	{
		return total;
	}
	public String[] getList(){
		
		String[] array = new String[index];
		for(int i = 0; i < index; i++){
			array[i] = (String) cart.get(i);
		}
		return array;
	}	

}