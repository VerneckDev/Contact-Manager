package Phone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Phone.DataBase.Contact;
import Phone.DataBase.Users;

public class Server
{
	public static void main(String[] args)
	{
		try
		{

			int door = 3333;
			
			try (ServerSocket sSock = new ServerSocket(door)) {
				System.out.println("Waiting for connection..."+door);
				
				
				while (true) {
					
					Socket client = sSock.accept();
					System.out.println("New client ["+ client.getInetAddress().getHostAddress()+"]");
					
					ClientHandler handler = new ClientHandler(client);
					new Thread(handler).start();
					
				}
			}
		}
		catch (Exception e)
		{
            e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable {
	
    private Socket client;
    
    public ClientHandler(Socket socket) {

        this.client = socket;
        
    }   
    
    @Override
    
    public void run() {
    	
    	try (
    		 PrintWriter out = new PrintWriter(client.getOutputStream(), true);
    	     ObjectOutputStream outl = new ObjectOutputStream(client.getOutputStream());
    		 InputStream in = client.getInputStream();
    		 InputStreamReader reader = new InputStreamReader(in);
    		 BufferedReader br = new BufferedReader(reader);
    			) 
    	{

    		boolean on = true;
    		String rep;
    		boolean log = true;
    		boolean adm = false;
    		boolean insert = false;
    		String user = null;
    		
    		File file = new File("users.dat");
    		File file2 = new File("cnts.dat");
    		
    		Map<String, DataBase> un;
    		Map<String, DataBase> cn;
    		
    		un = new HashMap<>();
    		cn = new HashMap<>();
    		
    		if (file.exists()) {
    			
    			un = loadUsers();
    			
    		}
    		if (file2.exists()) {
    			
    			cn = loadContact();
    			
    		}
    		
    		while (on) {
    			
    			while (log) {
    				
    				rep = br.readLine();
    			    
    				if (rep.equals("log")) {
    		    		
    		    		un = loadUsers();
    					
    					out.println("Username:");
    					String name = br.readLine();
    					
    					out.println("Password:");
    					String pass = br.readLine();
    					
    					if (un.containsKey(name)) {
    						
    						Users u = (Users) un.get(name);
    						user = name;
    						
    						if (u.getPass().equals(pass)) {
    							
    							out.println("ok");
    							
    							log = false;
    							adm = true;
    							
    						}
    						else {
    							
    							out.println("nopass");
    							
    						}
    						
    					}
    					else {
    						
    						out.println("nouser");
    						
    					}
    					
    				}
    				else if (rep.equals("reg")) {
    		    		
    		    		un = loadUsers();
    					
    					out.println("Username:");
    					String name = br.readLine();
    					
    					if (!un.containsKey(name)) {
    						
    						out.println("ok1");
        					
        					out.println("Email:");
        					String email = br.readLine();
        					String[] es = email.split("@");
        					
        					if (es.length == 2) {
        					
        						String[] es2 = es[1].split("\\.");
        						boolean end = false;
        						
        						if (es2.length > 1 && es2.length < 4) {
        	    					
        							for (Entry<String, DataBase> entry : un.entrySet()) {

        				                DataBase.Users e = (Users) entry.getValue();
        								if (email.equals(e.getEmail())) {
                							
                							out.println("inval2");
                							end = true;
                							break;
        								}
        							}
        							
        							if (!end) {
            							
        								out.println("ok2");
        								
            	    					out.println("Password:");
            	    					String pass = br.readLine();
            	        					
            	        				Users u = new DataBase.Users(name, email, pass);
            	        				un.put(name, u);
            	        				saveUsers(un);
            	        				out.println("User added!");
        								
        							}
        							else {
        								continue;
        							}
        							
        						}
        						else {
        							
        							out.println("inval");
        							
        						}
        						
        					}
        					else {
    							
    							out.println("inval");
        						
        					}
    						
    					}
    					else {
    						
    						out.println("added");
    						
    					}
    					
    				}	
    				else if (rep.equals("exit1")) {

    		            System.out.println("Disconnected!");
    		            log = false;
    					on = false;
    					
    				}
    			}
    			
    			while (adm) {
    				
    				rep = br.readLine();
    				
    				if (rep.equals("list")) {
    					
    					cn = loadContact();
    					
    					ArrayList<DataBase.Contact> contact = listOfCont(user);
    					outl.writeObject(contact);
    					outl.flush();
    					
    				}
    				else if (rep.equals("add")) {
    					
    					cn = loadContact();
    					
    					insert = true;
    					
    					out.println("Name: ");
    					String name2 = br.readLine();
    					
    					while (insert) {
    						
    						out.println("Phone number: ");
    						
    						String pnum = br.readLine();
    						
    						if (!pnum.equals("wrong")) {  
    							
								String k = pnum+user;
    							
    							if (cn.containsKey(k)) {
    								
    								out.println("exist");
    								insert = false;
    								
    							}
    							else {
    								
    								Contact c = new DataBase.Contact(user, Integer.parseInt(pnum), name2);
    								cn.put(k, c);
    								saveContact(cn);
    								out.println("Contact added!");
    								insert = false;
    								
    							}
    							
    						}
    						else {
    						
    							continue;
    							
    						}						
    					}
    				}
    				else if (rep.equals("remove")) {
    					
    					cn = loadContact();
    					
    					out.println("Phone number to be remove: ");
    					rep = br.readLine();
    					
    					if (cn.containsKey(rep+user)) {
    						
    						cn.remove(rep+user);
    						saveContact(cn);
    						out.println("Contact removed!");
    						
    					}
    					else {
    						
    						out.println("nexist");
    						
    					}
    					
    					
    				}
    				else if (rep.equals("exit2")) {
    					
    					log = true;
    					adm = false;
    					
    				}
    				
    			}
    		}
        	
        	
        	
        }
    	catch (Exception e) {
    		
            e.printStackTrace();
    		
    	}
    	finally {
    		
            try {
            	
                client.close();
            
            } 
            catch (IOException e) {
                
            	e.printStackTrace();
            
            }
            
            System.out.println("Conexão com cliente finalizada.");
        
    	}    		
}

    public static void saveUsers(Map<String, DataBase> un) {
    	
        try {
        	
            FileOutputStream fileOut = new FileOutputStream("users.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(un);
            objectOut.close();
            fileOut.close();
        } 
        catch (Exception exx) {
        	
            exx.printStackTrace();
        }
    }
	
	public static Map<String, DataBase> loadUsers() {
		
        try {
        	
            FileInputStream fileIn = new FileInputStream("users.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Map<String, DataBase> un = (Map<String, DataBase>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            return un;
        } 
        catch (Exception exx) {
        	
            return new HashMap<>();
        }
    }
	
	public static Map<String, DataBase> loadContact() {
		
        try {
        	
            FileInputStream fileIn = new FileInputStream("cnts.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Map<String, DataBase> cn = (Map<String, DataBase>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            return cn;
        } 
        catch (Exception exx) {
        	
            return new HashMap<>();
        }
    }

    public static void saveContact(Map<String, DataBase> cn) {
    	
        try {
        	
            FileOutputStream fileOut = new FileOutputStream("cnts.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(cn);
            objectOut.close();
            fileOut.close();
        } 
        catch (Exception exx) {
            exx.printStackTrace();
        }
    }
	
	public static ArrayList<DataBase.Contact> listOfCont(String name) {
		
        ArrayList<DataBase.Contact> list = new ArrayList<>();
        
	    try (ObjectInputStream lc = new ObjectInputStream(new FileInputStream("cnts.dat"))) {
	    	
	        HashMap<String, DataBase.Contact> cnt = (HashMap<String, DataBase.Contact>) lc.readObject();
	        
	        if (cnt.isEmpty()) {
	        } 
	        else {
	        	
	            for (Map.Entry<String, DataBase.Contact> entry : cnt.entrySet()) {
	            	
	                DataBase.Contact e = entry.getValue();
	                
	                if (e.getUser().equals(name)) {
	                	
	                	list.add(e);
	                }
	            }
	        }
	    } 
	    catch (IOException | ClassNotFoundException e) {
	        System.out.println("Erro ao ler o ficheiro cnts.dat: " + e.getMessage());
	    }
	    
	    return list;
	}
}