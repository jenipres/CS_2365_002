import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;
public class customer {
	// ================================
	// OBJECTS
	// ================================
	static ArrayList<CustomerInfo> customerInfos = new ArrayList<>();
	static ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
	static CustomerOrder order = new CustomerOrder();
	static CustomerInfo info = new CustomerInfo();
	static final Scanner input = new Scanner(System.in);
	static Random rand = new Random();
	// ================================
	// LOGIN METHOD
	// ================================
	public static void logOn() {
    System.out.print("Enter customer ID: ");
    String id = input.nextLine();

    int index = findCustomerIndex(id);
    if (index == -1) {
        System.out.println("Account doesn't exist!");
        return;
    }

    int attempts = 0;
    while (true) {
        System.out.print("Enter password: ");
        String pw = input.nextLine();

        if (!customerInfos.get(index).getPassword().equals(pw)) {
            attempts++;
            System.out.println("Incorrect password");
            if (attempts >= 3) {
                System.out.println("Too many attempts. Terminating.");
                return;
            }
            continue;
        }
        break; // password OK
    }

    // Security Q
    System.out.println(customerInfos.get(index).getSecurityQuestion());
    System.out.print("Enter here: ");
    String ans = input.nextLine();

    if (customerInfos.get(index).getSecurityAnswer().equals(ans)) {
        System.out.println("Welcome back!");
        // optionally track logged-in user:
        // currentIndex = index;
    } else {
        System.out.println("Answer doesn't match!");
    }
}

	static int findCustomerIndex(String id) {
		for (int i = 0; i < customerInfos.size(); i++) {
			if (customerInfos.get(i).getCustomerId().equals(id)) return i;
		}
		return -1;
	}

	// ================================
	// LOG OUT METHOD
	// ================================
	public static void logOut() {
		customerInfos.clear();
		System.out.print("Successfully logged out");
	}
	
// ================================
// CREATE ACCOUNT METHOD
// ================================
public static void createAccount() { 
	String customerId, password, name, address, creditCard;
	String securityQuestion = null, securityAnswer = null;
    int securityQuestionSelection;

    for (;;) {  // outer loop (wraps entire process)

        // Step 2 / 3: Customer ID must be unique
        for (;;) {
            System.out.print("Enter customer ID: ");
            customerId = input.nextLine();
			
            // replace: if (!customerIds.contains(customerId)) { ... }
            boolean exists = false;
            for (CustomerInfo c : customerInfos) {
                if (c.getCustomerId().equals(customerId)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                break; // valid ID → continue
            } else {
                System.out.println("This customer already exist try again\n");
                continue; // retry
            }
        }

        // Step 4 / 5: Password validation
        for (;;) {
            System.out.print("Enter a password(6 character minimum, one digit, one special character, and a uppercase): ");
            password = input.nextLine();

            if (password.matches(".*[!@#$%^&*?].*") && password.length() >= 6 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*")) 
			{
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
            System.out.print("----Security Question List----\n" + "1. What city were you born in?\n" + "2. Favorite movie?\n" + "3. What year are you graduating?\nEnter here: ");
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
        // Store all details using CustomerInfo instead of parallel ArrayLists
        CustomerInfo info = new CustomerInfo(customerId, password, name, address, creditCard, securityQuestion, securityAnswer);
        info.setSecurityQuestion(securityQuestion);
        info.setSecurityAnswer(securityAnswer);
        customerInfos.add(info);

        System.out.println("Account created!\n");
        break; // exit outer loop
    }
}


// ================================
// SELECT ITEMS METHOD
// ================================
public static void selectItems() {
    int milkQuantity, breadQuantity, eggsQuantity; 
	milkQuantity = breadQuantity = eggsQuantity = 0; 
    
    int productSelection;
    double totalMilk = 0.0, totalBread = 0.0, totalEggs = 0.0;
    double milkTax, breadTax, eggsTax, finalTax;
	double finalTotal = 0;
    System.out.printf("%-15s | %-25s | %-13s | %-12s%n", "Product", "Description", "Regular Price", "Sales Price");
    System.out.printf("%-15s | %-25s | %-13s | $%s%n", "Whole Milk", "Milk from a cow", "$3.00", "2.40");
    System.out.printf("%-15s | %-25s | %-13s | $%s%n", "Bread", "Bread from a bakery", "$2.00", "1.60");
    System.out.printf("%-15s | %-25s | %-13s | $%s%n", "Eggs", "Eggs from a chicken", "$5.00", "4.00");
    while (true) {
        System.out.print("Select which product(1-3)\nEnter here(input 0 to exit): ");
        productSelection = input.nextInt();
        input.nextLine();
        if (productSelection == 1) {
            System.out.print("Select Quantity\nEnter here: ");
            milkQuantity += input.nextInt();
            input.nextLine();
        } else if (productSelection == 2) {
            System.out.print("Select Quantity\nEnter here: ");
            breadQuantity += input.nextInt();
            input.nextLine();
        } else if (productSelection == 3) {
            System.out.print("Select Quantity\nEnter here: ");
            eggsQuantity += input.nextInt();
            input.nextLine();
        } else if (productSelection == 0) {
            break;
        } else if (productSelection < 1 || productSelection > 3) {
            System.out.print("Invalid selection!");
            continue; 
        }
    }
    
    if (milkQuantity == 0 && breadQuantity == 0 && eggsQuantity == 0) {
        System.out.print("Nothing selected");
        return;
    }
    
    totalMilk  = (2.40 * milkQuantity);    
    totalBread = (1.60 * breadQuantity);
    totalEggs  = (4.00 * eggsQuantity);
    milkTax = totalMilk  * 0.0825;
    breadTax = totalBread * 0.0825;
    eggsTax = totalEggs  * 0.0825;
    finalTax = milkTax + breadTax + eggsTax;
    finalTotal = totalMilk + totalEggs + totalBread;

    CustomerOrder order = new CustomerOrder();
    order.setMilkQuantity(milkQuantity);
    order.setBreadQuantity(breadQuantity);
    order.setEggsQuantity(eggsQuantity);
    order.setFinalTotal(finalTotal + finalTax);
    //order.setOrderDate(LocalDate.now());
    customerOrders.add(order);

    System.out.printf("%-15s | %-10s | %-10s | %-12s%n", "Product", "Quantity", "Tax", "Final Total");
    System.out.printf("%-15s | %-10d | %-10.2f | $%-11.2f%n", "Whole Milk", milkQuantity, milkTax, totalMilk + milkTax);
    System.out.printf("%-15s | %-10d | %-10.2f | $%-11.2f%n", "Bread",     breadQuantity, breadTax, totalBread + breadTax);
    System.out.printf("%-15s | %-10d | %-10.2f | $%-11.2f%n", "Eggs",      eggsQuantity,  eggsTax,  totalEggs + eggsTax);
    System.out.printf("%-15s | %-10s | %-10s | $%-11.2f%n",   "", "", "Final Total", finalTotal + finalTax);
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
    if (customerOrders.isEmpty() || customerInfos.isEmpty()) {
        System.out.println("No items or customer on file. Please create account and select items first.");
        return;
    }

    CustomerInfo info = customerInfos.get(customerInfos.size() - 1);
    CustomerOrder order = customerOrders.get(customerOrders.size() - 1);
    order.setCustomerId(info.getCustomerId());

    int deliveryOption;
    double deliveryFee = 0.0;
    double creditLimit = 500.0; // all credit cards will have the same credit line
	String cardCheck;

    int authCode = rand.nextInt((9999 - 1000) + 1) + 1000; // 1000..9999

    System.out.print("(1) mail by charging a fee (e.g., $3.00 per order) or (2) in-store pick up for free, or (0) cancel.\nEnter Here: ");
    if (!input.hasNextInt()) { input.nextLine(); System.out.println("Invalid selection"); return; }
    deliveryOption = input.nextInt();
    input.nextLine();

    if (deliveryOption == 0) { System.out.println("Order cancelled."); return; }
    else if (deliveryOption == 1) deliveryFee = 3.00;
    else if (deliveryOption == 2) deliveryFee = 0.00;
    else { System.out.println("Invalid selection"); return; }

    double subtotal = order.getFinalTotal();      // items+tax from selectItems()
    double grandTotal = subtotal + deliveryFee;

    System.out.printf("Total (before delivery): $%.2f%n", subtotal);
    System.out.printf("Delivery fee: $%.2f%n", deliveryFee);
    System.out.printf("Grand total: $%.2f%n", grandTotal);
	
	String card = info.getCreditCard(); // this is not user specific 
	System.out.print("Enter your credit card number\nEnter here: ");
	cardCheck = input.nextLine();
	

    /*while (card == null || card.equals(cardCheck) || grandTotal > creditLimit) { //card.equals(cardCheck) isnt working properly 
        System.out.println("Charge denied (invalid card or over limit). Enter another credit card number or 0 to exit:");
        String newCard = input.nextLine();
        if ("0".equals(newCard)) { System.out.println("Order cancelled."); return; }
        info.setCreditCard(newCard);              // update account per alt step 7
        card = newCard;
		creditLimit -= grandTotal;
        // (in this simple simulation the limit stays $500)
        if (!(card == null || card.isEmpty())) break; // accept non-empty as “valid”
    }*/
	
	while(true)
	{
		if(grandTotal > creditLimit)
		{
			System.out.println("Charge denied, limit has been maxed out! Make a new credit card or 0 to exit: ");
		}
		
	}
	creditLimit -= grandTotal;
	//CustomerOrder order = new CustomerOrder();
    order.setAuthCode(authCode);
	order.setFinalTotal(grandTotal); 
	order.setOrderDate(LocalDate.now());
	order.setCreditCard(card);
	customerOrders.add(order);

	System.out.println("Credit card balance: " + creditLimit);
    System.out.println("Transaction success, Authorization code: " + authCode);
}

	// ================================
	// MAIN MENU
	// ================================
	public static void main(String[] args) {
		int selection;
		
		while (true) {
			// menu display
			System.out.print("\n----Customer Main Menu----");
			System.out.print("\n1. Create Account \n2. Login Account \n3. Logout Account\n4. Select Items\n5. View Order\n6. Make Order\n7. Exit Program\nEnter: ");
			
			selection = input.nextInt();
			input.nextLine(); // clear newline
			
			if (selection == 1) {
				createAccount(); 
			} else if (selection == 2) {
				logOn();
			} else if (selection == 3) {
				logOut();
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

// ======================
// BLUEPRINT CLASS/ ARRAYS
// ======================
class CustomerInfo {
	// ================================
	// FIELDS
	// ================================
    private String customerId;
    private String password;
    private String name;
    private String address;
    private String creditCard;
    private String securityQuestion;
    private String securityAnswer;
	
	
	// ================================
	// CONSTRUCTOR
	// ================================
	public CustomerInfo(String customerId, String password, String name, String address, String creditCard, String securityQuestion, String securityAnswer) {
        this.customerId = customerId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.creditCard = creditCard;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		
	}
	public CustomerInfo() {}
	
	// ================================
	// ACCESSORS / MUTATORS
	// ================================
	public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCreditCard() { return creditCard; }
    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
    public String getSecurityQuestion() { return securityQuestion; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }
    public String getSecurityAnswer() { return securityAnswer; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }
}
class CustomerOrder {
	// ================================
	// FIELDS
	// ================================
    private int milkQuantity = 0;
	private int breadQuantity = 0;
	private int eggsQuantity = 0;
	private double finalTotal = 0.0;
	private int authCode;
	private String customerId;
	private LocalDate orderDate;
	private String creditCard;
	
	// ================================
	// CONSTRUCTOR
	// ================================
	public CustomerOrder(String customerId, int milkQuantity, int breadQuantity, int eggsQuantity, double finalTotal, String creditCard, int authCode, LocalDate orderDate) {
        this.milkQuantity = milkQuantity;
        this.breadQuantity = breadQuantity;
        this.eggsQuantity = eggsQuantity;
        this.finalTotal = finalTotal;
        this.creditCard = creditCard;
		this.authCode = authCode;
		this.customerId = customerId;
		this.orderDate = orderDate;
		
	}
	public CustomerOrder() {}
	
	// ================================
	// ACCESSORS / MUTATORS
	// ================================
	public int getMilkQuantity() { return milkQuantity; }
    public void setMilkQuantity(int milkQuantity) { this.milkQuantity = milkQuantity; }
    public int getBreadQuantity() { return breadQuantity; }
    public void setBreadQuantity(int breadQuantity) { this.breadQuantity = breadQuantity; }
    public int getEggsQuantity() { return eggsQuantity; }
    public void setEggsQuantity(int eggsQuantity) { this.eggsQuantity = eggsQuantity; }
    public double getFinalTotal() { return finalTotal; }
    public void setFinalTotal(double finalTotal) { this.finalTotal = finalTotal; }
	public int getAuthCode() { return authCode; }
    public void setAuthCode(int authCode) { this.authCode = authCode; }
	public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
	public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
	public String getCreditCard() { return creditCard; }
    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
}
