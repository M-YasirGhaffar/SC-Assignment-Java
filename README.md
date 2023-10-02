# SC-Assignment-Java-Code

### Online Shopping System Documentation

#### Language Used:
- Programming Language: Java

#### Introduction:
The "OnlineShoppingSystem" is an implementation of an online shopping system using Java. It employs object-oriented programming principles to create a modular and extensible system. The code provides a text-based menu-driven interface for users to interact with the online shopping system.

#### Key Concepts Used (OOP):
1. **Classes and Objects**: The code defines several classes, including `Product`, `PhysicalProduct`, `DigitalProduct`, `ShoppingCart`, `Order`, and `Customer`, to model different aspects of the online shopping system.

2. **Inheritance**: The code uses inheritance to create subclasses (`PhysicalProduct` and `DigitalProduct`) that inherit attributes and behaviors from the parent class (`Product`).

3. **Encapsulation**: The classes encapsulate their data by using private fields and providing getter methods to access data.

4. **Polymorphism**: The code leverages polymorphism, where different types of products (`PhysicalProduct` and `DigitalProduct`) can be treated as their parent type (`Product`) through inheritance.

5. **Composition**: The `ShoppingCart` class contains a list of `Product` items, demonstrating composition by including objects of another class within it.

6. **Exception Handling**: The code handles exceptions for scenarios like product not found or interrupted payment processing.

#### How the Code Works:
1. **Main Menu**: The program starts by displaying a main menu with various options such as adding a product, creating a customer account, logging in, viewing account information, and more.

2. **Product Management**: Users can add products to the system with details like name, price, and stock quantity. Physical and digital products can be added with additional attributes.

3. **Customer Management**: Users can create customer accounts by providing a username and password. Registered customers can log in and view their account information and order history.

4. **Shopping Cart**: Customers can add and remove products from their shopping carts. The shopping cart keeps track of items and calculates the total price.

5. **Order Placement**: Customers can place orders, and each order includes the items from the shopping cart. Order details are displayed, and the order is added to the customer's order history.

6. **Simulated Payment**: The code simulates a payment process by introducing a 2-second delay to mimic payment processing during cart checkout.

#### Class Functions:
1. `Product`: Represents a product with attributes like name, price, and stock quantity. It includes methods to display product details and decrease stock.

2. `PhysicalProduct` and `DigitalProduct`: Subclasses of `Product` that add attributes specific to physical and digital products, respectively.

3. `ShoppingCart`: Represents a customer's shopping cart. It allows adding and removing products, calculating the total price, displaying cart contents, and simulating the checkout process.

4. `Order`: Represents an order placed by a customer. It includes order number, order date, ordered products, and methods to calculate the total price and display order details.

5. `Customer`: Represents a customer with a username, password, and order history. It includes methods for registration, login, viewing account information, and order history.

#### Areas of Improvement:
1. **User Interface**: The system currently uses a text-based interface. Enhancements can be made to provide a graphical user interface (GUI) for a more user-friendly experience.

2. **Database Integration**: Integrate a database to store product, customer, and order data persistently, allowing for better data management.

3. **Security**: Implement secure authentication and authorization mechanisms to protect customer data.

4. **Error Handling**: Improve error handling with more informative error messages and robust exception handling.

5. **Testing**: Implement unit tests to ensure the reliability and correctness of the code.

6. **Scalability**: Consider scalability for handling a larger number of products, customers, and orders.

#### Additional Information:
- The code includes comments to explain the functionality of various methods and blocks of code.
- The simulated payment process is used to demonstrate how payment processing might work in a real system.
- This code serves as a foundation for building a more comprehensive and feature-rich online shopping system.

#### Conclusion:
The "OnlineShoppingSystem" code demonstrates the application of OOP concepts in Java to create a functional online shopping system. It allows users to manage products, create customer accounts, shop, and place orders. Areas for improvement include enhancing the user interface, incorporating a database, and ensuring security and scalability.
