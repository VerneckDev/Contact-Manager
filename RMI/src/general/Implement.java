package general;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.util.List;

public class Implement extends UnicastRemoteObject implements Interface
{
	private RegUser reg;
	public Implement()throws RemoteException
	{
		super();//set up the class to be used remotely
		this.reg = FileMnager.load();//Shares Reguser to every folder 
	}
	public String New (String name,String pass, String mail) throws RemoteException//Implements the function that was "created" on the Interface file and shapes the function behavior 
	{

	String[] parts=mail.split("@");

        if(parts.length != 2)
        {
	        return("Invalid email address. (There is no '@') ");
        }	
        else if(parts[1].indexOf('.')==-1)
        {
        	return("Invalid email address. \n No valid extention (ex: .com) at the end \n ");
        }
        else
            {
            	User user=reg.getName(name);
            	if(user!=null)
            	{
            		return("User already exists.");
            	}
            	user=reg.getMail(mail);
            	if(user!=null)
            	{
            		return("e-mail already used.");

            	}
            	else
            	{
            		user=new User(name,pass,mail);
            		reg.addUser(user);
            		FileMnager.save(reg);
            		return("User added");
            		
            	}
            	
            }

	 }
	public String Check(String name,String pass) throws RemoteException
	{
		User user=reg.getName(name);
		if(user==null)
		{
			return("User not found");
		}
		else if(!pass.equals(user.getPass()))
		{
			return("Incorrect password");
		}
		else
		{
			return("Welcome!");
		}
	}	
	public List<Contatos> List(String name)throws RemoteException
	{
		User user=reg.getName(name);
		ArrayList<Contatos> zeca=new ArrayList<>(user.getContatos());
		return(zeca);
	}

	public String Add(String username, String name, String number)throws RemoteException
	{
		User user=reg.getName(username);
		if(number.length()!=9)
		{
			return("Invalid number (needs to be 9 numbers long)");
		}
		else
		{
			for (Contatos c : user.getContatos())
			{
				if (c.getCel() == Integer.parseInt(number)) {
		            return "There is already a contact with that number.";
		        }

			}
			Contatos contato=new Contatos(name,Integer.parseInt(number));
			user.addContato(contato);
			FileMnager.save(reg);
			return ("Contact added!");
		}
	}

	public String Remove (String username, String number)throws RemoteException
	{
		User user=reg.getName(username);
		Contatos contato=user.getConNumber(number);
		if (contato==null)
		{
			return("Contact not found");
			
		}
		else
		{
			user.removeContato(contato);
			FileMnager.save(reg);
			return("Contact removed.");
		}
	}
	
}