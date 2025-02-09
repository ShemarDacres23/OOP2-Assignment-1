import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt for Client details
            System.out.print("Enter Client ID Number: ");
            int idNumber = scanner.nextInt();

            System.out.print("Enter First Name: ");
            String firstName = scanner.next();

            System.out.print("Enter Last Name: ");
            String lastName = scanner.next();

            System.out.print("Enter Age: ");
            int age =  scanner.nextInt();
            if (age < 0) {
                System.out.println("Age cannot be negative. Setting age to 0.");
                age = 0;
            }

            // Prompt for Card details
            System.out.print("Enter Card Number: ");
            int cardNumber = scanner.nextInt();

            System.out.print("Enter Card Balance: ");
            double balance = scanner.nextDouble();
            if (balance < 0) {
                System.out.println("Balance cannot be negative. Setting to 0.");
                balance = 0;
            }

            System.out.print("Enter Card PIN: ");
            int pin = scanner.nextInt();

            System.out.print("Enter Card Status (true for Active, false for Inactive): ");
            boolean status = scanner.nextBoolean();

            // Create Card and Client objects
            Card card = new Card(cardNumber, balance, pin, status);
            Client client = new Client(idNumber, firstName, lastName, age, card);

            // Display options
            client.displayClientDetails();
            client.displayCardInfoOnly();
            client.modifyCard();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class Client {
    private int idNum;
    private String firstName;
    private String lastName;
    private int age;
    Card card;

    // Constructor
    public Client(int idNum, String firstName, String lastName, int age, Card card) {
        this.idNum = idNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = Math.max(age, 0); // Prevent negative age
        this.card = card;
    }

    // Getter for Client
    public int getIdNum() { return idNum; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public Card getCard() { return card; }

    // Setter for Client
    public void setIdNumber(int idNum) { this.idNum = idNum; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Must be non-negative.");
            this.age = 0;
        }
    }
    public void setCard(Card card) { this.card = card; }

    // Display full client details
    public void displayClientDetails() {
        System.out.println("\nClient Details:");
        System.out.println("ID Number: " + getIdNum());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Age: " + getAge());
        getCard().displayCardDetails();
    }

    // Display card details only
    public void displayCardInfoOnly() {
        System.out.println("\nCard Details:");
        getCard().displayCardDetails();
    }

    // Modify card attributes
    public void modifyCard() {
        getCard().setStatus(true);
        getCard().setPin(76657);
        System.out.println("\nUpdated Card Details:");
        getCard().displayCardDetails();
    }
}

class Card {
    private int cardNumber;
    private double balance;
    private int pin;
    private boolean status;

    // Constructor
    public Card(int cardNumber, double balance, int pin, boolean status) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
        this.status = status;
    }

    // Getters
    public int getCardNumber() { return cardNumber; }
    public double getBalance() { return balance; }
    public int getPin() { return pin; }
    public boolean getStatus() { return status; }

    // Setters
    public void setCardNumber(int cardNumber) { this.cardNumber = cardNumber; }
    public void setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Invalid balance. Must be greater than zero (0)");
            this.balance = 0;
        }
    }
    public void setPin(int pin) { this.pin = pin; }
    public void setStatus(boolean status) { this.status = status; }

    // Display card details
    public void displayCardDetails() {
        System.out.println("Card Number: " + getCardNumber());
        System.out.println("Balance: $" + getBalance());
        System.out.println("PIN: " + getPin());
        System.out.println("Status: " + (getStatus() ? "Active" : "Inactive"));
    }
}
