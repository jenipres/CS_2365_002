import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;
public class CustomerOrderSystem {
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
// CREATE ACCOUNT METHOD
// ================================
public static void createAccount() { 
    
        
}

// ================================
// SELECT ITEMS METHOD
// ================================
public static void selectItems() { 
    
}

// ================================
// MAKE ORDER METHOD
// ================================

public static void makeOrder() {
    
}

// ================================
// VIEW ORDER METHOD
// ================================
public static void viewOrder() {
	
	}


	// ================================
	// LOGIN METHOD
	// ================================
	public static void logOn() {
   
}

	// ================================
	// LOG OUT METHOD
	// ================================
	public static void logOut() {
	
	}
	
	// ================================
	// MAIN MENU
	// ================================
	public static void main(String[] args) {
		int selection;
		
		while (true) {
			System.out.print("\n----Customer Main Menu----");
			System.out.print("\n1. Create Account \n2. Login Account \n3. Logout Account\n4. Select Items\n5. View Order\n6. Make Order\n7. Exit Program\nEnter: ");
			
			selection = input.nextInt();
			input.nextLine(); 
			
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

// ================================
// FIND CUSTOMER
// ================================
static int findCustomerIndex(String id) {
		for (int i = 0; i < customerInfos.size(); i++) {
			if (customerInfos.get(i).getCustomerId().equals(id)) return i;
		}
		return -1;
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
	// ================================
	// DEFAULT CONSTRUCTOR
	// ================================
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
	// ================================
	// DEFAULT CONSTRUCTOR
	// ================================
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
