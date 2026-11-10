package general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegUser implements Serializable
{
	
	private List<User>users;
	
	public void addUser(User user)
	{
		users.add(user);
	}
	
	public void removeUser(User user)
	{
		users.remove(user);
	}
	
	public User getName(String name) 
	{
        for (User user : users) 
        {
            if (user.getName().equals(name))
            {
                return user;
            }
        }
        return null;
	}
	public User getMail(String mail)
	{
		for(User user:users)
		{
			if(user.getMail().equals(mail))
			{
				return user;
			}
		}
		return null;
	}
	
	public List<User>getUsers()
	{
		return users;
	}
	
	public RegUser()
	{
		this.users=new ArrayList<>();
	}
}
