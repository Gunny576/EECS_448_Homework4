import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
		public static void main(String[] args) {
			String[] type1 = new String[20];
			String[] type2 = new String[20];
			String[] type3 = new String[20];

		    
			BufferedReader br = null;
			 if (true)
			 {
			try {
	 
				String sCurrentLine;
	 
				br = new BufferedReader(new FileReader("H:\\EECS_448\\Homework 4\\testEnviroment\\src\\Database.txt"));
				int k = 0;
				while ((sCurrentLine = br.readLine()) != null) {
					k++;
					if ((k <= 21) && (k>1))
					{
						type1[k-2] = sCurrentLine;
						//System.out.println(sCurrentLine);
					}
					if ((k < 43) && (k>22))
					{
						type2[k-23] = sCurrentLine;
						//System.out.println(sCurrentLine);
					}
					if ((k < 63) && (k>43))
					{
						type3[k-44] = sCurrentLine;
						//System.out.println(sCurrentLine);
					}
					
				}
				
			
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		String[][] Database = {type1, type2, type3};	
		Controller control = new Controller();
		control.run(Database);
			
		}
	}