/**
 * Created by Keene on 7/26/2014.
 */
public class Product {

    private String identifier;
    private String description;

    public Product(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }



    public String toSQL() {
        return "INSERT INTO product_temp (identifier, description, uom_id, serialized, lot_controlled, expiry_required, created_user_id, created_time, modified_user_id) VALUES('" +
                identifier + "', '" + description + "', 4, 0, 1, 1, 1, NOW(), 1);\n";
    }
}
