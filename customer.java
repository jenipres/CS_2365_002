mport java.util.Scanner;
import java.util.ArrayList;
public class customer {
	// ================================
	// FIELDS 
	// ================================
	private static String customerId;
	private static String password;
	private static String name;
	private static String address;
	private static String creditCard;
	private static String securityAnswer;
	private static String securityQuestion;
	// ================================
	// OBJECTS
	// ================================
	static ArrayList<String> customerIds = new ArrayList<>(); 
	static ArrayList<String> passwords = new ArrayList<>(); 
	static ArrayList<String> names = new ArrayList<>();
	static ArrayList<String> addresses = new ArrayList<>();
	static ArrayList<String> creditCards = new ArrayList<>();
	static ArrayList<String> securityAnswers = new ArrayList<>();
	static ArrayList<String> securityQuestions = new ArrayList<>();
	static Scanner input = new Scanner(System.in); 
	// ================================
	// CONSTRUCTOR
	// ================================
	public customer(String customerId, String password, String name, String address, String creditCard) {
		this.customerId = customerId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.creditCard = creditCard;
	}
	
	// ================================
	// ACCESSORS / MUTATORS
	// ================================
	public String getCustomerId() { return customerId; }
	public String getPassword() { return password; }
	public void setPassword() { this.password = password; }
	public String getName() { return name; }
	public void setName() { this.name = name; }
	public String getAddress() { return address; }
	public void setAddress() { this.address = address; }
	public void setCreditCard() { this.creditCard = creditCard; }
	
	// ================================
	// LOGIN METHOD
	// ================================
	public static void logOn(String customerId, String password) {
		int attempts = 0;

		while (true) {
			// Step 1: ask for ID
			System.out.print("Enter customer ID: ");
			String id = input.nextLine();

			// Step 2a: check if ID exists
			if (!customerIds.contains(id)) {
				System.out.println("Account doesn't exist!");
				return; // terminate if not found
			}

			// find that customer's index
			int index = customerIds.indexOf(id);

			// Step 2b: ask for password
			System.out.print("Enter a password: ");
			String pw = input.nextLine();

			// check password
			if (!passwords.get(index).equals(pw)) {
				attempts++;
				System.out.println("Incorrect password");

				// lockout after 3 attempts
				if (attempts >= 3) {
					System.out.println("Too many attempts. Terminating.");
					return;
				}
				continue; // retry password
			}

			// Step 3: correct ID + password → ask security question
			System.out.println(securityQuestions.get(index));
			System.out.print("Enter here: ");
			String ans = input.nextLine();

			// Step 4: check answer
			if (securityAnswers.get(index).equals(ans)) {
				System.out.println("Welcome back!");
			} else {
				System.out.println("Answer doesn't match!");
			}
			return; // end login flow
		}
	}

	// ================================
	// LOG OUT METHOD
	// ================================
	public static void logOut(String customerId) {
			customerIds.clear();
			names.clear();
			passwords.clear();
			addresses.clear();
			creditCards.clear();
			securityAnswers.clear();
			securityQuestions.clear();
			
			System.out.print("Successfly logged out");
	}
	
	// ================================
	// CREATE ACCOUNT METHOD
	// ================================
	public static void createAccount(String customerId, String password, String name, String address, String creditCard, String securityAnswer, String securityQuestion) {
		int securityQuestionSelection;

		for (;;) {  // outer loop (wraps entire process)

			// Step 2 / 3: Customer ID must be unique
			for (;;) {
				System.out.println("Enter customer ID: ");
				customerId = input.nextLine();

				if (!customerIds.contains(customerId)) {
					break; // valid ID → continue
				} else {
					System.out.println("This customer already exist try again\n");
					continue; // retry
				}
			}

			// Step 4 / 5: Password validation
			for (;;) {
				System.out.println("Enter a password(6 character minimum, one digit, one special character, and a uppercase): ");
				password = input.nextLine();

				if (password.matches(".*[!@#$%^&*?].*") && 
				    password.length() >= 6 && 
				    password.matches(".*[A-Z].*") && 
				    password.matches(".*[0-9].*")) {
					break; // valid password
				} else {
					System.out.println("Invalid password please try again\n");
					continue; 
				}
			}

			// Step 6 / 7: Personal details
			for (;;) {
				System.out.println("Enter Name, Address, and credit card number");
				System.out.print("Enter here: ");
				name = input.nextLine();
				System.out.print("Enter here: ");
				address = input.nextLine();
				System.out.print("Enter here: ");
				creditCard = input.nextLine();

				if (!name.isEmpty() && !address.isEmpty() && !creditCard.isEmpty()) {
					break; // all good
				} else {
					System.out.println("Error try again\n");
					continue; 
				}
			}

			// Step 8: Security question
			for (;;) {
				System.out.print("----Security Question List----\n" + 
				                 "1. What city were you born in?\n" + 
				                 "2. Favorite movie?\n" + 
				                 "3. What year are you graduating?\nEnter here: ");
				if (input.hasNextInt()) {
					securityQuestionSelection = input.nextInt();
					if (securityQuestionSelection == 1) {
						securityQuestion = "What city were you born in?";
					} else if (securityQuestionSelection == 2) {
						securityQuestion = "Favorite movie?";
					} else if (securityQuestionSelection == 3) {
						securityQuestion = "What year are you graduating?";
					}
					input.nextLine(); // consume newline
				} else {
					input.nextLine(); 
					System.out.println("Invalid selection, try again\n");
					continue;
				}

				if (securityQuestionSelection == 1 || securityQuestionSelection == 2 || securityQuestionSelection == 3) {
					System.out.print("Enter answer: ");
					securityAnswer = input.nextLine(); 
					break; // valid choice
				} else {
					System.out.println("Invalid selection, try again\n");
					continue;
				}
			}

			// Store all details in parallel ArrayLists
			customerIds.add(customerId);
			names.add(name);
			passwords.add(password);
			addresses.add(address);
			creditCards.add(creditCard);
			securityAnswers.add(securityAnswer);
			securityQuestions.add(securityQuestion);
	
			System.out.println("Account created!\n");
			break; // exit outer loop
		}
	}

	// ================================
	// SELECT ITEMS METHOD
	// ================================
	public static void selectItems() {
		double salesPrice, regularPrice;
		salesPrice = regularPrice * (1 - 0.20); // 20% could be different in the future
		int productSelection;
		int totalMilk = 0;
		int totalBread = 0;
		int totalEggs = 0;
		int finalTotal;
		int milkCount, breadCount, eggsCount, milkQuantity, breadQuantity, eggsQuantity;
		System.out.printf("%-15s | %-25s | %-13s | %-12s%n", "Product", "Description", "Regular Price", "Sales Price");
		System.out.printf("%-15s | %-25s | %-13s | $%s%n", "Whole Milk", "Milk from a cow", "$3", salesPrice);
		System.out.printf("%-15s | %-25s | %-13s | $%s%n", "Bread", "Bread from a bakery", "$2", salesPrice);
		System.out.printf("%-15s | %-25s | %-13s | $%s%n", "Eggs", "Eggs from a chicken", "$5", salesPrice);
		while(true)
		{
			System.out.println("Select which product(1-3)\nEnter here(input 0 to exit): ");
			productSelection = input.nextInt();
			input.nextLine();
			if(productSelection == 1)
			}
				milkCount++;
				System.out.print("Select Quantity\nEnter here: ")
				milkQuantity = input.nextInt();
				input.nextLine();
			{
			else if(productSelection == 2)
			{
				breadCount++;
				System.out.print("Select Quantity\nEnter here: ")
				breadQuantity= input.nextInt();
				input.nextLine();
			}
			else if(productSelection == 3)
			{
				eggsCount++;
				System.out.print("Select Quantity\nEnter here: ")
				eggsQuantity = input.nextInt();
				input.nextLine();
			}
			else if(productSelection == 0)
			{
				totalMilk = ((3 * milkCount) * milkQuantity) * 0.825;
				totalBread = ((2 * breadCount) * breadQuantity) * 0.825;
				totalEggs = ((5 * eggsCount) * eggsQuantity) * 0.825;
				finalTotal = totalMilk + totalEggs + totalBread;
				break;
			}
			else 
				System.out.print("");
		
		}	
	}	
  
	
	
	
	
	
	
	// ================================
	// VIEW ORDER METHOD
	// ================================
	public static void viewOrder() {
		
	}
	
	// ================================
	// MAKE ORDER METHOD
	// ================================
	public static void makeOrder() {
		
	}
	
	// ================================
	// MAIN MENU
	// ================================
	public static void main(String[] args) {
		int selection;
		
		while (true) {
			// menu display
			System.out.print("----Customer Main Menu----");
			System.out.print("\n1. Create Account \n2. Login Account \n3. Logout Account\n4. Select Items\n5. View Order\n6. Make Order\n7. Exit Program\nEnter: ");
			
			selection = input.nextInt();
			input.nextLine(); // clear newline
			
			if (selection == 1) {
				createAccount(customerId, password, name, address, creditCard, securityAnswer, securityQuestion);
			} else if (selection == 2) {
				logOn(customerId, password);
			} else if (selection == 3) {
				logOut(customerId, password, name, address, creditCard, securityAnswer, securityQuestion);
			} else if (selection == 4) {
				selectItems();
			} else if (selection == 5) {
				viewOrder();   
			} else if (selection == 6) {
				makeOrder();   
			} else if (selection == 7) {
				System.out.print("Exiting program");
				break;
			} else {
				System.out.print("Selection wasnt between 1 - 7, please try again!");
			}
		}
	}
}
