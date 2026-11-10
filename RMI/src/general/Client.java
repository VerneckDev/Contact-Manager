package general;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;


public class Client
{
		public static void main(String[]args)
	{
			boolean t=true;
			boolean t2=false;
			boolean on=true;
			String name=null;


		Scanner scan= new Scanner(System.in);
		try
		{
			Registry registry=LocateRegistry.getRegistry("localhost",1099);
			Interface eu =(Interface) registry.lookup("houseWife");//Name of the file that will be looking up for.
			while(on)
			{
				while (t)
				{
					menu();
					System.out.println("Choose a option ");
					String x=scan.nextLine();

					switch(x)
					{
						case("1"):
						{
							System.out.println("Username: ");
							name=scan.nextLine();
							System.out.println("Password: ");
							String pass=scan.nextLine();
							System.out.println(eu.Check(name, pass));
							if ((eu.Check(name, pass)).equals("Welcome!"))
							{
								t=false;
								t2=true;
							}
							break;
						}
						case("2"):
						{
							System.out.println("Username: ");
							name=scan.nextLine();
							System.out.println("Password: ");
							String pass=scan.nextLine();
							System.out.println("E-mail: ");
							String mail=scan.nextLine();
							System.out.println(eu.New(name,pass,mail)); 
							break;

						}
						case("3"):
						{
							System.out.println("See you soon! ");
							t=false;
							on=false;
							break;
						}
						default:
						{
							System.out.println("Choose a valid option");
							break;
						}
					}//switch
				}//while(t)
				while(t2)
				{
					menu2();
					System.out.println("Choose a option ");
					String x=scan.nextLine();
					
					switch(x)
					{
						case("1"):
						{
							ArrayList<Contatos> zeca = new ArrayList<>(eu.List(name));
							List(zeca);
							break;

						}
						case("2"):
						{
							System.out.println("Contact's name: ");
							String namec=scan.nextLine();
							System.out.println("Contact's number: ");
							String number=scan.nextLine();
							System.out.println(eu.Add(name, namec, number));
							break;
 						}
						case("3"):
						{
							ArrayList<Contatos> zeca = new ArrayList<>(eu.List(name));
							List(zeca);
							if(!zeca.isEmpty())
							{
								System.out.println("Choose which contact would you liek to delete (choose by phonenumber)");
								String number=scan.nextLine();
								System.out.println(eu.Remove(name, number));
							}
							break;
							
						}
						case("4"):
						{
							System.out.println("Good bye "+name+"!" );
							t2=false;
							t=true;
							break;
						}
					}


				}//While(y2)
			}//While(on)

		}//try
		catch (Exception e) 
		{
            e.printStackTrace();
        }
		scan.close();

	}
	public static void menu()
	{
		
	    int width = 33;
	    
	    String[] lines = {
	    	"  Contact Admin  ",
	        "",
	        "  [1] Login    ",
	        "  [2] Register ",
	        "  [3] Exit     ",
	        "",
	    }; 
	    
	    String uBorder = "╔" + "═".repeat(width - 2) + "╗";	    
	    String bBorder = "╚" + "═".repeat(width - 2) + "╝";
	    
	    System.out.println(uBorder);
	    for (String line : lines) {
	    	
	    	int padd = (width - 2 - line.length()) / 2;
	    		
		        String fLine = "║" + " ".repeat(Math.max(0, padd)) + line + " ".repeat(Math.max(0, width - 2 - padd - line.length())) + "║";
		        System.out.println(fLine);	    		
	    }
	    System.out.println(bBorder);
	}
	
	
	public static void menu2()
	{
		
	    int width = 33;
	    
	    String[] lines = {
	    	"  Contact Admin  ",
	        "",
	        "  [1] List of Contacts ",
	        "  [2] Insert Contact   ",
	        "  [3] Remove Contact   ",
	        "  [4] Exit             ",
	        "",
	    }; 
	    
	    String uBorder = "╔" + "═".repeat(width - 2) + "╗";	    
	    String bBorder = "╚" + "═".repeat(width - 2) + "╝";
	    
	    System.out.println(uBorder);
	    for (String line : lines) {
	    	
	    	int padd = (width - 2 - line.length()) / 2;
	    		
		        String fLine = "║" + " ".repeat(Math.max(0, padd)) + line + " ".repeat(Math.max(0, width - 2 - padd - line.length())) + "║";
		        System.out.println(fLine);	    		
	    }
	    System.out.println(bBorder);
	}

	public static void List(ArrayList<Contatos> zeca)
	{
		if(zeca.isEmpty())
		{
			System.out.println("There are no contacts ");
		}
		else
		{
			for (Contatos contato:zeca)
			{
				System.out.println("Contact's name: " +contato.getName() + ". Number: "+contato.getCel());
			}

		}

	}
}

