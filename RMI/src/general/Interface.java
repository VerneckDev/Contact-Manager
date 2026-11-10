package general;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;




public interface Interface extends Remote
{
	String New (String name,String pass, String mail) throws RemoteException;//defines what function will used on the server machine (In the future will have things to check if the information in valid)
	String Check(String name,String pass) throws RemoteException;
	List<Contatos> List(String name)throws RemoteException;
	String Add(String username, String name, String number)throws RemoteException;
	String Remove(String username, String number)throws RemoteException;

}
