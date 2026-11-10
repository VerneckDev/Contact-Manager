package general;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable 
{
	private String name;
	private String pass; 
	private String mail;
	private List<Contatos> contatos;
	
	public String getName()
	{
		return name;
	}
	
	public String getPass()
	{
		return pass;
	}
	
	public String getMail()
	{
		return mail;
	}
	
	public List<Contatos> getContatos()
	{
		return(contatos);
	}
	
	public Contatos getConName(String name)
	{
		for(Contatos contato: contatos)
		{
			if(contato.getName().equals(name))
			{
				return contato;
			}
		}
		return null;
	}
	public Contatos getConNumber(String number)
	{
		for(Contatos contato: contatos)
		{
			if(contato.getCel()==Integer.parseInt(number))
			{
				return contato;
			}
		}
		return null;
	}
	
	
	public void addContato(Contatos contato) 
	{
	    contatos.add(contato);
	}

	
	public void removeContato(Contatos contato)
	{
		contatos.remove(contato);
	}
	
	
	


	
	public User(String name, String pass, String mail)
	{
		this.name=name;
		this.pass=pass;
		this.mail=mail;
		this.contatos=new ArrayList<>();
	}
}

