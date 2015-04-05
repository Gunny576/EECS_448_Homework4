import java.util.*;
public class ShoppingCart {
public double total;
List cart = new ArrayList();
List cartQuant = new ArrayList();
int index;
int index_item;
	public ShoppingCart()
	{
		total = 0.0;
		index = 0;
	}
	
	public void add(String item, double price)
	{
		total = total + price;
		if(cart.contains(item)) {
			index_item = cart.indexOf(item);
			cartQuant.set(index_item, Integer.toString(Integer.parseInt(cartQuant.get(index_item).toString()) + 1));
		} else {
			cart.add(index,item);
			cartQuant.add("1");
			index++;
		}
	}
	
	public void remove(String item, double price)
	{
		total = total - price;
		index_item = cart.indexOf(item);
		if(Integer.parseInt(cartQuant.get(index_item).toString()) > 1) {
			cartQuant.set(index_item, Integer.toString(Integer.parseInt(cartQuant.get(index_item).toString()) - 1));
		} else {
			cart.remove(index_item);
			cartQuant.remove(index_item);
			index --;
		}
	}
	
	public void remove(int index, double price)
	{
		total = total - price;
		index_item = index;
		cart.remove(index_item);
		index --;
	}

	public double getTotal()
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
	
public String[] getQtyList(){
		
		String[] array = new String[index];
		for(int i = 0; i < index; i++){
			array[i] = (String) cartQuant.get(i);
		}
		return array;
	}

}