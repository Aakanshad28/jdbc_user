package jdbc_user;

import java.util.Scanner;

public class UserController {
	public static void main(String[] args) throws Exception {
		 Scanner scanner=new Scanner(System.in);
		 int choice=scanner.nextInt();
		 
		 User user=new User();
		 UserCRUD crud=new UserCRUD();
		 System.out.println("Enter the choice \n1.Signup User \n2.Login User");
		 
		 switch (choice) {
		case 1:{
			System.out.println("Enter the id:");
			int id=scanner.nextInt();
			System.out.println("Enter the name:");
			String name=scanner.next();
			System.out.println("Enter the phone:");
			long phone=scanner.nextLong();
			System.out.println("Enter the email:");
			String email=scanner.next();
			System.out.println("Enter the password:");
			String password=scanner.next();
			
			user.setId(id);
			user.setName(name);
			user.setPhone(phone);
			user.setEmail(email);
			user.setPassword(password);
			
			crud.signUpUser(user);
			
		}
		break;
		case 2:{
			System.out.println("Enter the email:");
			String email=scanner.next();
			
			System.out.println("Enter the password:");
			String password=scanner.next();
			
			user.setEmail(email);
			user.setPassword(password);
			
			boolean res=crud.userLogin(email,password);
			if (res) {
				System.out.println("Login successfull");
				crud.showPassword(email);
			} else {
                 System.out.println("Login Failed");
			}
			
		}
		case 3:{
			System.out.println("Enter the facebook password:");
		//	String Password=Scanner.next();
			System.out.println("Enter the Instagram password:");
			
			System.out.println("Enter the Snapchat password:");
			
			System.out.println("Enter the Twitter password:");
			
			System.out.println("Enter the Whatsapp password:");
			
			
			
			
		}
			
		
			default:
			break;
		}
	}

}
