package general;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server
{
	public static void main(String[] args)
	{
		try 
		{
			Interface inter= new Implement();//Creates a inter with the functions on the implement
			Registry registry =LocateRegistry.createRegistry(1099);//defines a object registry and where it is going to be 
			registry.rebind("houseWife", inter);//name of the file that the object inter will be
			System.out.println("Server ready");//message sent to tell that the server is ready
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
