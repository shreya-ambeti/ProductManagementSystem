import java.time.LocalDate;
import java.util.List;

public class ProductService {
    private final ProductDB db = new ProductDB();

    public void addProduct(Product p) {
        db.save(p); // Save product to the database
    }

    public List<Product> getAllProducts() {
        return db.getAll(); // Fetch all products from the database
    }

    public Product getProductByName(String name) {
        return db.getByCondition("name", name); // Query by name
    }

    public List<Product> getByPlace(String place) {
        return db.getListByCondition("place", place); // Query by place
    }

    public List<Product> getByCategory(String category) {
        return db.getListByCondition("category", category); // Query by category
    }

    public List<Product> getByType(String type) {
        return db.getListByCondition("type", type); // Query by type
    }

    public List<Product> getExpiredWarranty() {
        int currentYear = LocalDate.now().getYear();
        return db.getByWarrantyCondition("<", currentYear); // Query expired warranty
    }

    public List<Product> getHasWarranty() {
        int currentYear = LocalDate.now().getYear();
        return db.getByWarrantyCondition(">=", currentYear); // Query active warranty
    }

    public List<Product> getByText(String text) {
        return db.getByTextSearch(text); // Query by full-text search
    }

    public boolean deleteProductByName(String name) {
        return db.deleteByCondition("name", name.toLowerCase());
    }

    public void updateProduct(Product product) {
        db.updateProduct(product);
    }

}

