import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OnlineShoppingSystem {

    // List to store customer accounts
    private static List<Customer> customers = new ArrayList<>();
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            List<Product> products = new ArrayList<>();
            List<Customer> customers = new ArrayList<>();
            List<ShoppingCart> carts = new ArrayList<>();
            List<Order> orders = new ArrayList<>();

            // Main menu loop for user interaction
            while (true) {
                System.out.println("Online Shopping System Menu:");
                System.out.println("1. Add Product");
                System.out.println("2. Create Customer Account");
                System.out.println("3. Customer Login");
                System.out.println("4. View Account Information");
                System.out.println("5. View Order History");
                System.out.println("6. Add Product to Cart");
                System.out.println("7. Display Cart Contents");
                System.out.println("8. Checkout Cart");
                System.out.println("9. Place Order");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                // Different cases for different options
                switch (choice) {
                    case 1:
                        Product product = addProduct(scanner);
                        products.add(product);
                        break;
                    case 2:
                        Customer customer = createCustomerAccount(scanner);
                        customers.add(customer);
                        break;
                    case 3:
                        Customer loggedInCustomer = customerLogin(scanner, customers);
                        if (loggedInCustomer != null) {
                            int cartIndex = findCustomerCart(loggedInCustomer, customers, carts);
                            if (cartIndex == -1) {
                                ShoppingCart cart = new ShoppingCart();
                                carts.add(cart);
                            }
                            handleCustomerActions(scanner, loggedInCustomer, products, carts, orders);
                        }
                        break;
                    case 4:
                        viewAccountInformation(scanner, customers);
                        break;
                    case 5:
                        viewOrderHistory(scanner, customers);
                        break;
                    case 6:
                        addProductToCart(scanner, customers, products, carts);
                        break;
                    case 7:
                        displayCartContents(scanner, customers, carts);
                        break;
                    case 8:
                        checkoutCart(scanner, customers, carts);
                        break;
                    case 9:
                        placeOrder(scanner, customers, carts, orders);
                        break;
                    case 10:
                        System.out.println("Exiting Online Shopping System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            // Exception Handle
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Add Product
    private static Product addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock quantity: ");
        int stock = scanner.nextInt();

        // Create and return a new Product instance
        return new Product(name, price, stock);
    }

    // Create Customer Account
    private static Customer createCustomerAccount(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Create and return a new Customer instance
        return new Customer(username, password);
    }

    // Customer Login
    private static Customer customerLogin(Scanner scanner, List<Customer> customers) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Find the customer with the given username and password
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) { 

                return customer;
            }
        }
        System.out.println("Login failed. Username or password is incorrect.");
        return null;
    }

    // Find Customer Cart
    private static int findCustomerCart(Customer customer, List<Customer> customers, List<ShoppingCart> carts) {
        // Find the index of the customer's cart in the carts list
        int index = customers.indexOf(customer);
        if (index != -1 && index < carts.size()) {
            return index;
        }
        return -1;
    }

    // Handle Customer Actions
    private static void handleCustomerActions(Scanner scanner, Customer customer, List<Product> products,
            List<ShoppingCart> carts, List<Order> orders) {
        int cartIndex = findCustomerCart(customer, customers, carts);
        if (cartIndex == -1) {
            System.out.println("Error: Customer's cart not found.");
            return;
        }
        ShoppingCart cart = carts.get(cartIndex);

        while (true) {
            System.out.println("Customer Actions Menu:");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. View Cart Contents");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProductToCart(scanner, customer, products, cart);
                    break;
                case 2:
                    removeProductFromCart(scanner, customer, cart);
                    break;
                case 3:
                    displayCartContents(customer, cart);
                    break;
                case 4:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProductToCart(Scanner scanner, Customer customer, List<Product> products,
            ShoppingCart cart) {
        System.out.print("Enter the name of the product to add to the cart: ");
        String productName = scanner.next();
        Product productToAdd = null;

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                productToAdd = product;
                break;
            }
        }

        if (productToAdd != null) {
            cart.addItem(productToAdd);
            System.out.println(productToAdd.getName() + " added to the cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void removeProductFromCart(Scanner scanner, Customer customer, ShoppingCart cart) {
        System.out.print("Enter the name of the product to remove from the cart: ");
        String productName = scanner.next();

        Product productToRemove = null;

        for (Product product : cart.getItems()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            cart.removeItem(productToRemove);
            System.out.println(productToRemove.getName() + " removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    private static void displayCartContents(Customer customer, ShoppingCart cart) {
        System.out.println("Shopping Cart Contents for " + customer.getUsername() + ":");
        cart.displayCart();
    }

    // View Account Information
    private static void viewAccountInformation(Scanner scanner, List<Customer> customers) {
        System.out.print("Enter username: ");
        String username = scanner.next();

        // Find and display the account information of the customer with the given
        // username
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) { 
                customer.viewAccountInfo();
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    // View Order History
    private static void viewOrderHistory(Scanner scanner, List<Customer> customers) {
        System.out.print("Enter username: ");
        String username = scanner.next();

        // Find and display the order history of the customer with the given username
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) { 
                customer.viewOrderHistory();
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    // Add Product to Cart
    private static void addProductToCart(Scanner scanner, List<Customer> customers, List<Product> products,
            List<ShoppingCart> carts) {
        Customer customer = customerLogin(scanner, customers);
        if (customer != null) {
            System.out.print("Enter the name of the product to add to the cart: ");
            String productName = scanner.next();

            // Find the product with the given name
            Product productToAdd = null;
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    productToAdd = product;
                    break;
                }
            }

            if (productToAdd != null) {
                int cartIndex = findCustomerCart(customer, customers, carts);
                if (cartIndex != -1) {
                    ShoppingCart cart = carts.get(cartIndex);
                    cart.addItem(productToAdd);
                    System.out.println(productToAdd.getName() + " added to the cart.");
                } else {
                    System.out.println("Customer's cart not found.");
                }
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    // Display Cart Contents
    private static void displayCartContents(Scanner scanner, List<Customer> customers, List<ShoppingCart> carts) {
        Customer customer = customerLogin(scanner, customers);
        if (customer != null) {
            int cartIndex = findCustomerCart(customer, customers, carts);
            if (cartIndex != -1) {
                ShoppingCart cart = carts.get(cartIndex);
                cart.displayCart();
            } else {
                System.out.println("Customer's cart not found.");
            }
        }
    }

    // Checkout Cart
    private static void checkoutCart(Scanner scanner, List<Customer> customers, List<ShoppingCart> carts) {
        Customer customer = customerLogin(scanner, customers);
        if (customer != null) {
            int cartIndex = findCustomerCart(customer, customers, carts);
            if (cartIndex != -1) {
                ShoppingCart cart = carts.get(cartIndex);
                cart.checkout();
            } else {
                System.out.println("Customer's cart not found.");
            }
        }
    }

    // Place Order
    private static void placeOrder(Scanner scanner, List<Customer> customers, List<ShoppingCart> carts,
            List<Order> orders) {
        Customer customer = customerLogin(scanner, customers);
        if (customer != null) {
            int cartIndex = findCustomerCart(customer, customers, carts);
            if (cartIndex != -1) {
                ShoppingCart cart = carts.get(cartIndex);
                Order newOrder = new Order(cart.getItems());
                orders.add(newOrder);
                System.out.println("Order placed successfully. Order ID: " + newOrder.getOrderNumber());
            } else {
                System.out.println("Customer's cart not found.");
            }
        }
    }

}

class ShoppingCart {
    private List<Product> items;

    public List<Product> getItems() {
        return items;
    }

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
        System.out.println(product.getName() + " added to the cart.");
    }

    public void removeItem(Product product) {
        if (items.remove(product)) {
            System.out.println(product.getName() + " removed from the cart.");
        } else {
            System.out.println(product.getName() + " was not found in the cart.");
        }
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("Shopping Cart Contents:");
        for (Product item : items) {
            item.displayDetails();
        }
    }

    public void checkout() {
        try {
            // Simulated checkout process
            Thread.sleep(2000); // Sleep to mimic payment processing
            System.out.println("Checkout completed. Payment successful.");
        } catch (InterruptedException e) {
            System.err.println("Payment processing interrupted: " + e.getMessage());
        }
    }
}

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void displayDetails() {
        System.out.println("Product Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock);
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public void decreaseStock() {
        if (stock > 0) {
            stock--;
        } else {
            System.out.println("Sorry, this product is out of stock.");
        }
    }
}

class PhysicalProduct extends Product {
    private String size;
    private double weight;

    public PhysicalProduct(String name, double price, int stock, String size, double weight) {
        super(name, price, stock);
        this.size = size;
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }
}

class DigitalProduct extends Product {
    private String downloadLink;
    private double fileSize;

    public DigitalProduct(String name, double price, int stock, String downloadLink, double fileSize) {
        super(name, price, stock);
        this.downloadLink = downloadLink;
        this.fileSize = fileSize;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public double getFileSize() {
        return fileSize;
    }
}

class Order {
    private static int nextOrderNumber = 1;
    private int orderNumber;
    private Date orderDate;
    private List<Product> orderedProducts;

    public Order(List<Product> orderedProducts) {
        this.orderNumber = getNextOrderNumber();
        this.orderDate = new Date();
        this.orderedProducts = orderedProducts;
    }

    private static int getNextOrderNumber() {
        return nextOrderNumber++;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : orderedProducts) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void displayOrderDetails() {
        System.out.println("Order Number: " + orderNumber);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Ordered Products:");
        for (Product product : orderedProducts) {
            System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")");
        }
        System.out.println("Total Price: $" + getTotalPrice());
    }
}

class Customer {
    private String username;
    private String password;
    private List<Order> orderHistory;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    public void register() {
        System.out.println("Customer registered successfully. Username: " + username);
    }

    public void login() {
        System.out.println("Customer logged in successfully. Welcome, " + username + "!");
    }

    public void viewAccountInfo() {
        System.out.println("Customer Account Information:");
        System.out.println("Username: " + username);
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("Order History is empty.");
        } else {
            System.out.println("Order History:");
            for (Order order : orderHistory) {
                order.displayOrderDetails();
            }
        }
    }

    public void placeOrder(ShoppingCart cart) {
        try {
            Order newOrder = new Order(cart.getItems());
            orderHistory.add(newOrder);
            System.out.println("Order placed successfully. Order ID: " + newOrder.getOrderNumber());
        } catch (Exception e) {
            System.err.println("Failed to place the order: " + e.getMessage());
        }
    }
}