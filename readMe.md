# Banking Management System

This is a simple console-based Banking Management System implemented in Java that uses JDBC and Oracle DB for connectivity.

## Features

The system provides the following features:

1. Show Customer Records
2. Add Customer Record
3. Delete Customer Record
4. Update Customer Record
5. Show Account Details of a Customer
6. Show Loan Details of a Customer
7. Deposit Money to an Account
8. Withdraw Money from an Account

## Usage

To run the program, you need to have a Java Runtime Environment (JRE) installed on your machine. You also need to have access to an Oracle database, as the program uses Oracle JDBC to interact with the database.

Compile the `Main.java` file and run the `Main` class. The program will prompt you to enter a number corresponding to the operation you want to perform.

Compilation command: 
`javac -cp './dependencies/ojdbc8.jar' Main.java`

To run: 
`javac -cp './dependencies/ojdbc8.jar' Main.java`

## Dependencies

This project uses the following dependencies:

- Oracle JDBC Driver

## License

This project is open source and available under the [MIT License](LICENSE).