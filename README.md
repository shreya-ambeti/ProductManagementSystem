# Product Management System

A Product Management System built in Java to manage products in a catalog. This system allows users to perform basic CRUD operations (Create, Read, Update, Delete) on products, store data in a PostgreSQL database, and interact with the system through a simple console interface.

## Features

- **Add Products**: Add a new product with details like name, type, category, place of manufacture, and warranty information.
- **View Products**: Display a list of all available products in the catalog.
- **Search Products**: Search products by name or other details using a case-insensitive search.
- **Update Products**: Update the details of an existing product (name, type, category, place, warranty).
- **Delete Products**: Remove a product from the system by name.
- **PostgreSQL Database**: Store product data securely in a PostgreSQL database.
  
## Tech Stack

- **Java 20** for backend development
- **PostgreSQL** for database storage
- **Maven** for project management and dependency management
- **JDBC** for database connectivity


## Usage

Once you run the system, you'll be presented with a simple menu where you can perform the following actions:

1. **Add Product**: Add a new product by providing the required details.
2. **View Products**: View a list of all products in the database.
3. **Search Products**: Search for products by name.
4. **Update Product**: Update an existing product's details.
5. **Delete Product**: Delete a product by its name.
6. **Exit**: Close the application.


## Code Structure

- **ProductService.java**: This file contains the service logic, which communicates with the `ProductDB` for CRUD operations.
- **ProductDB.java**: Handles the database interaction, including connecting to PostgreSQL and executing SQL queries for product management.
- **Main.java**: Contains the main method and serves as the entry point for running the console application.
- **pom.xml**: Maven configuration file, which includes dependencies for PostgreSQL and other necessary libraries.



