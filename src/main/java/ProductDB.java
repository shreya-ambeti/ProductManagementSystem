import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private final Connection conn;

    public ProductDB() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/ProductManagementSystem",
                    "postgres",
                    "1234"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void save(Product p) {
        String query = "INSERT INTO product(name, type, category, place, warranty) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, p.getName());
            st.setString(2, p.getType());
            st.setString(3, p.getCategory());
            st.setString(4, p.getPlace());
            st.setInt(5, p.getWarranty());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving product", e);
        }
    }

    public List<Product> getAll() {
        String query = "SELECT name, type, category, place, warranty FROM product";
        return executeQuery(query);
    }

    public Product getByCondition(String column, String value) {
        String query = "SELECT name, type, category, place, warranty FROM product WHERE LOWER(" + column + ") = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, value.toLowerCase());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return mapRowToProduct(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching product by " + column, e);
        }
        return null;
    }

    public List<Product> getListByCondition(String column, String value) {
        String query = "SELECT name, type, category, place, warranty FROM product WHERE LOWER(" + column + ") = ?";
        return executeQuery(query, value.toLowerCase());
    }

    public List<Product> getByWarrantyCondition(String operator, int year) {
        String query = "SELECT name, type, category, place, warranty FROM product WHERE warranty " + operator + " ?";
        return executeQuery(query, year);
    }

    public List<Product> getByTextSearch(String text) {
        String query = "SELECT name, type, category, place, warranty FROM product WHERE " +
                "LOWER(name) LIKE ? OR LOWER(type) LIKE ? OR LOWER(category) LIKE ? OR LOWER(place) LIKE ?";
        text = "%" + text.toLowerCase() + "%";
        return executeQuery(query, text, text, text, text);
    }

    private List<Product> executeQuery(String query, Object... params) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                st.setObject(i + 1, params[i]);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query", e);
        }
        return products;
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setName(rs.getString("name"));
        p.setType(rs.getString("type"));
        p.setCategory(rs.getString("category"));
        p.setPlace(rs.getString("place"));
        p.setWarranty(rs.getInt("warranty"));
        return p;
    }


    public boolean deleteByCondition(String column, String value) {
        String query = "DELETE FROM product WHERE LOWER(" + column + ") = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, value);
            return st.executeUpdate() > 0; // Returns true if a row was deleted
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product by " + column, e);
        }
    }

    public void updateProduct(Product p) {
        String query = "UPDATE product SET type = ?, category = ?, place = ?, warranty = ? WHERE LOWER(name) = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, p.getType());
            st.setString(2, p.getCategory());
            st.setString(3, p.getPlace());
            st.setInt(4, p.getWarranty());
            st.setString(5, p.getName().toLowerCase());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

}
