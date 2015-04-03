
public class Controller {
	ShoppingCart SCart = new ShoppingCart();
	public Controller()
	{
		
	}
	public void run(String[][] database)
	{
		//get input
	   String[][] blaaa = {{"SciFi 1", "SciFi 2"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {"Travel 1", "Travel 2"},
            {"SoE 1", "SoE 2"}};
		
                DisplayDriver newView = new DisplayDriver(blaaa);
                newView.setVisible(true);              
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
