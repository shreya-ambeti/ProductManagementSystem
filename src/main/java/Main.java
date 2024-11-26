import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Product Management System ===");
            System.out.println("1. Add a Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product by Name");
            System.out.println("4. Filter Products by Place");
            System.out.println("5. Filter Products by Category");
            System.out.println("6. Filter Products by Type");
            System.out.println("7. View Products with Expired Warranty");
            System.out.println("8. View Products with Active Warranty");
            System.out.println("9. Search Products by Text");
            System.out.println("10. Delete a Product");
            System.out.println("11. Update a Product");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add a product
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Product Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Product Place: ");
                    String place = scanner.nextLine();
                    System.out.print("Enter Warranty Year: ");
                    int warranty = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Product newProduct = new Product(name, type, category, place, warranty);
                    service.addProduct(newProduct);
                    System.out.println("Product added successfully!");
                    break;

                case 2:
                    // View all products
                    System.out.println("All Products:");
                    print(service.getAllProducts());
                    break;

                case 3:
                    // Search product by name
                    System.out.print("Enter Product Name to Search: ");
                    String searchName = scanner.nextLine();
                    Product productByName = service.getProductByName(searchName);
                    if (productByName != null) {
                        System.out.println(productByName);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    // Filter by place
                    System.out.print("Enter Place to Filter: ");
                    String searchPlace = scanner.nextLine();
                    print(service.getByPlace(searchPlace));
                    break;

                case 5:
                    // Filter by category
                    System.out.print("Enter Category to Filter: ");
                    String searchCategory = scanner.nextLine();
                    print(service.getByCategory(searchCategory));
                    break;

                case 6:
                    // Filter by type
                    System.out.print("Enter Type to Filter: ");
                    String searchType = scanner.nextLine();
                    print(service.getByType(searchType));
                    break;

                case 7:
                    // View expired warranty products
                    System.out.println("Products with Expired Warranty:");
                    print(service.getExpiredWarranty());
                    break;

                case 8:
                    // View active warranty products
                    System.out.println("Products with Active Warranty:");
                    print(service.getHasWarranty());
                    break;

                case 9:
                    // Search products by text
                    System.out.print("Enter Text to Search: ");
                    String searchText = scanner.nextLine();
                    print(service.getByText(searchText));
                    break;

                case 10:
                    // Delete a product
                    System.out.print("Enter Product Name to Delete: ");
                    String deleteName = scanner.nextLine();
                    boolean isDeleted = service.deleteProductByName(deleteName);
                    if (isDeleted) {
                        System.out.println("Product deleted successfully!");
                    } else {
                        System.out.println("Product not found or could not be deleted.");
                    }
                    break;

                case 11:
                    // Update a product
                    System.out.print("Enter Product Name to Update: ");
                    String updateName = scanner.nextLine();
                    Product existingProduct = service.getProductByName(updateName);
                    if (existingProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }
                    System.out.println("Enter new details for the product (leave blank to keep unchanged):");
                    System.out.print("New Type: ");
                    String newType = scanner.nextLine();
                    System.out.print("New Category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("New Place: ");
                    String newPlace = scanner.nextLine();
                    System.out.print("New Warranty Year (0 to skip): ");
                    int newWarranty = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    existingProduct.setType(!newType.isEmpty() ? newType : existingProduct.getType());
                    existingProduct.setCategory(!newCategory.isEmpty() ? newCategory : existingProduct.getCategory());
                    existingProduct.setPlace(!newPlace.isEmpty() ? newPlace : existingProduct.getPlace());
                    if (newWarranty > 0) existingProduct.setWarranty(newWarranty);

                    service.updateProduct(existingProduct);
                    System.out.println("Product updated successfully!");
                    break;

                case 12:
                    // Exit
                    System.out.println("Exiting Product Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Utility method to print a list of products
    public static void print(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
