package Phone;
import java.io.Serializable;

public class DataBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String pass;
	private int pnum;
	private String name2;
	
	
	public DataBase(String name, String email, String pass) {
	
		this.name = name;
		this.email = email;
		this.pass = pass;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public String getPass() {
		
		return pass;
	}
	
	public DataBase(String name, int pnum, String name2) {
	
		this.name = name;
		this.pnum = pnum;
		this.name2 = name2;
	}

	public String toString2() {
		
		return "Name: "+name2+", Contact: "+pnum;
	}
	
	public String getUser() {
		
		return name;
	}
	
	public String getName2() {
		
		return name2;
	}
	
	public int getPhone() {
		
		return pnum;
	}
	
	public static class Users extends DataBase implements Serializable {

		private static final long serialVersionUID = 1L;

		public Users(String name, String email, String pass) {
			super(name, email, pass);
		}

		@Override
		public String getName() {
			return super.getName();
		}

		@Override
		public String getEmail() {
			return super.getEmail();
		}

		@Override
		public String getPass() {
			return super.getPass();
		}
	}
	
	public static class Contact extends DataBase implements Serializable {

		private static final long serialVersionUID = 1L;

		public Contact(String name, int pnum, String name2) {
			super(name, pnum, name2);
		}

		@Override
		public String getName2() {
			return super.getName2();
		}
		
		public String getUser() {
			
			return super.getUser();
		}

		@Override
		public int getPhone() {
			return super.getPhone();
		}

		@Override
		public String toString2() {
			return super.toString2();
		}
	}
	
	
}
