import java.util.Scanner;
import java.io;
import java.util.ArrayList;
public class customer {
	// initialize
	private String customerId;
	private String password;
	private String name;
	private String address;
	private String creditCard;
	//objects
	Arraylist<String> customerIds = new Arraylist; // array object
	Scanner input = new Scanner(System.in); // scanner object
	
	// Constructer
	public customer(String customerId, String password, String name, String address, String creditCard) 
	{
		this.customerId =customerId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.creditCard = creditCard;
	}
	//Mutators and Accessors 
	public String getCustomerId() { return customerId; }
	public String getPassword() { return password; }
	public void setPassword() { this.password = password; }
	public String getName() { return name; }
	public void setName() { this.name = name; }
	public String getAddress() { return address; }
	public void setAddress() { this.address = address; }
	public void setCreditCard() { this.creditCard = creditCard; }
	
	//Methods
	public String logOn(String customerId, String password)
	{
		
	}
	
	public void logOut(String customerId)
	{
		
	}
	
	public void createAccount(String customerId, String password, String name, String address, String creditCard, String securityAnswer, int securityQuestionSelection) 
	{
		
	for (;;) {
        System.out.println("Enter customer ID: ");
        customerId = input.nextLine();

        if (!customerIds.contains(customerId)) {
            System.out.println("Enter a password(6 character minimum, one digit, one special character, and a uppercase): ");
            password = input.nextLine();

            if (password.matches(".*[!@#$%^&*?].*") && password.length() >= 6 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*")) {

                System.out.println("Enter Name, Address, and credit card number");
                name = input.nextLine();
                address = input.nextLine();
                creditCard = input.nextLine();

                if (!name.isEmpty() && !address.isEmpty() && !creditCard.isEmpty()) {
                    System.out.println("Account created!");

                    System.out.println("----Security Question List----\n1. What city were you born in?\n2. Favorite movie?\n3. What year are you graduating?");
                    if (input.hasNextInt()) {
                        securityQuestionSelection = input.nextInt();
                        input.nextLine(); 
                    } else {
                        input.nextLine(); 
                        System.out.println("Invalid selection, try again");
                        continue;
                    }

                    if (securityQuestionSelection == 1) {
                        System.out.println("Enter: ");
                        securityAnswer = input.nextLine(); 
                    } else if (securityQuestionSelection == 2) {
                        System.out.println("Enter: ");
                        securityAnswer = input.nextLine();
                    } else if (securityQuestionSelection == 3) {
                        System.out.println("Enter: ");
                        securityAnswer = input.nextLine();
                    } else {
                        System.out.println("Invalid selection, try again");
                        continue;
                    }

                    customerIds.add(customerId); 
                    break; 
                } else {
                    System.out.println("Error try again");
                    continue;
                }
            } else {
                System.out.println("Invalid password please try again");
                continue;
            }
        } else {
            System.out.println("This customer already exist try again");
            continue;
        }
    }
}

	
	public void selectItems()
	{
	
	}
	
	public void viewOrder()
	{
		
	}
	
		
	public static void main(Strings[] args)
	{
		// initialize
		int selection, securityQuestionSelection;
		String securityAnswer;
		
		while(true)
		{
			System.out.print("----Customer Main Menu----");
			System.out.print("----Enter number as shown----\n1. Create Account \n2. Login Account \n3. Logout Account\n4. Select Items\n5. View Order\n6. Exit Program\n");
			selection = input.nextInt();
			if(selection == 1)
			{
				createAccount();
			}
			else if(selection == 2)
			{
				logIn();
			}
			else if(selection == 3)
			{
				logOut();
			}
			else if(selection == 4)
			{
				selectItems();
			}
			else if(selection == 5)
			{
				viewOrder();
			}
			else if(selection == 6)
			{
				System.out.print("Exiting program");
				break;
			}
			else 
			System.out.print("Selection wasnt between 1 - 6, please try again!");
			
		}
		
	}
