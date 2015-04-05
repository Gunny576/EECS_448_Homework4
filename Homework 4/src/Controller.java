import java.awt.EventQueue;


public class Controller {
	ShoppingCart SCart = new ShoppingCart();
	public Controller()
	{
		
	}
	public void run(final String[][] database)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
		    public void run() {
		        DisplayDriver newView = new DisplayDriver(database, SCart);
		        newView.setVisible(true);
		    }
		});             
		int inputType =0;
		int inputbook = 0;
		int inputgenre =0;
		int price =0;
		
		switch (inputType)
		{
		case 1:
			SCart.add(database[inputgenre][inputbook], price);
		//add call	
		break;
		
		case 2:
			SCart.remove(database[inputgenre][inputbook], price);
		//remove call
		break;
		
		case 3:
		//checkout call
		break;
		}
		
		
	}
}
