mport java.util.Scanner;
import java.util.ArrayList;
public class customer {
	// initialize
	private static String customerId;
	private static String password;
	private static String name;
	private static String address;
	private static String creditCard;
	private static String securityAnswer;
	private static String securityQuestion;
	
	//objects
	static ArrayList<String> customerIds = new ArrayList<>(); 
	static ArrayList<String> passwords = new ArrayList<>(); 
	static ArrayList<String> names = new ArrayList<>();
	static ArrayList<String> addresses = new ArrayList<>();
	static ArrayList<String> creditCards = new ArrayList<>();
	static ArrayList<String> securityAnswers = new ArrayList<>();
	static ArrayList<String> securityQuestions = new ArrayList<>();
	static Scanner input = new Scanner(System.in); 
	
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
	public static void logOn(String customerId, String password) 
	{
		int attempts = 0;

		while (true) 
		{
			System.out.print("Enter customer ID: ");
			String id = input.nextLine();

		// Step 3: check if ID exists
			if (!customerIds.contains(id)) 
			{
				System.out.println("Account doesn't exist!");
				return; 
			}

				// find index of this customer
				int index = customerIds.indexOf(id);

				// Step 2: check password (3 attempts max)
				System.out.print("Enter a password: ");
				String pw = input.nextLine();

			if (!passwords.get(index).equals(pw)) 
			{
				attempts++;
				System.out.println("Incorrect password");
				if (attempts >= 3) 
				{
					System.out.println("Too many attempts. Terminating.");
					return;
				}
					continue; // try again
			}

					// Step 3: correct ID + password → ask security question
					System.out.println(securityQuestions.get(index));
					System.out.print("Enter here: ");
					String ans = input.nextLine();

					if (securityAnswers.get(index).equals(ans)) 
					{
					System.out.println("Welcome back!");
					} else {
						System.out.println("Answer doesn't match!");
					}
			return; // end login flow
    }
}

	public static void logOut(String customerId)
	{
		
	}
	
	public static void createAccount(String customerId, String password, String name, String address, String creditCard, String securityAnswer, String securityQuestion) 
	{
	// intialize
	int securityQuestionSelection;
	for (;;) {  // OUTER LOOP

    //Step 2 / 3: Customer ID
    for (;;) {
        System.out.println("Enter customer ID: ");
        customerId = input.nextLine();

        if (!customerIds.contains(customerId)) {
            break; // valid ID -> move to password
        } else {
            System.out.println("This customer already exist try again\n");
            continue; // retry ID
        }
    }

    // Step 4 / 5: Password 
    for (;;) {
        System.out.println("Enter a password(6 character minimum, one digit, one special character, and a uppercase): ");
        password = input.nextLine();

        if (password.matches(".*[!@#$%^&*?].*") && password.length() >= 6 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*")) {
            break; 
        } else {
            System.out.println("Invalid password please try again\n");
            continue; 
        }
    }

    // Step 6 / 7: Customer details 
    for (;;) {
        System.out.println("Enter Name, Address, and credit card number");
        System.out.print("Enter here: ");
        name = input.nextLine();
        System.out.print("Enter here: ");
        address = input.nextLine();
        System.out.print("Enter here: ");
        creditCard = input.nextLine();

        if (!name.isEmpty() && !address.isEmpty() && !creditCard.isEmpty()) {
            break; 
        } else {
            System.out.println("Error try again\n");
            continue; 
        }
    }

    // Security Question 
    for (;;) {
        System.out.print("----Security Question List----\n" + "1. What city were you born in?\n" + "2. Favorite movie?\n" + "3. What year are you graduating?\nEnter here: ");
        if (input.hasNextInt()) {
            securityQuestionSelection = input.nextInt();
			if(securityQuestionSelection == 1)
			{
				securityQuestion = "What city were you born in?";
			}
			else if(securityQuestionSelection == 2)
			{
				securityQuestion = "Favorite movie?";
			}
			else if(securityQuestionSelection == 3)
			{
				securityQuestion = "What year are you graduating?";
			}
            input.nextLine(); 
        } else {
            input.nextLine(); 
            System.out.println("Invalid selection, try again\n");
            continue;
        }

        if (securityQuestionSelection == 1 || securityQuestionSelection == 2 || securityQuestionSelection == 3) {
            System.out.print("Enter answer: ");
            securityAnswer = input.nextLine(); 
            break; // valid selection
        } else {
            System.out.println("Invalid selection, try again\n");
            continue;
        }
    }

    customerIds.add(customerId);
	names.add(name);
	passwords.add(password);
	addresses.add(address);
	creditCards.add(creditCard);
	securityAnswers.add(securityAnswer);
	securityQuestions.add(securityQuestion);
	
    System.out.println("Account created!\n");
    break; 
	}

}

	
	public static void selectItems()
	{
	
	}
	
	public static void viewOrder()
	{
		
	}
	
	public static void makeOrder()
	{
		
	}
	
		
	public static void main(String[] args)
	{
		// initialize
		int selection;
		
		while(true)
		{
			System.out.print("----Customer Main Menu----");
			System.out.print("\n1. Create Account \n2. Login Account \n3. Logout Account\n4. Select Items\n5. View Order\n6. Make Order\n7. Exit Program\nEnter: ");
			selection = input.nextInt();
			input.nextLine();
			if(selection == 1)
			{
				createAccount(customerId, password, name, address, creditCard, securityAnswer, securityQuestion);
			}
			else if(selection == 2)
			{
				logOn(customerId, password);
			}
			else if(selection == 3)
			{
				logOut(customerId);
			}
			else if(selection == 4)
			{
				selectItems();
			}
			else if(selection == 5)
			{
				makeOrder();
			}
			else if(selection == 6)
			{
				viewOrder();
			}
			else if(selection == 7)
			{
				System.out.print("Exiting program");
				break;
			}
			else 
			System.out.print("Selection wasnt between 1 - 6, please try again!");
			
		}
		
	}
}
