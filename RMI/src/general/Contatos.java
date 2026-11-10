package general;

import java.io.Serializable;


public class Contatos implements Serializable
{
	private int cel;
	private String name;
	
	public int getCel()
	{
		return (cel);
	}
	
	public String getName()
	{
		return(name);
	}
	
	
	public Contatos(String name,int cel)
	{
		this.cel=cel;
		this.name=name;
	}
}
