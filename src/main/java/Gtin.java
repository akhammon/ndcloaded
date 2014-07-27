/**
 * Created by Keene on 7/26/2014.
 */
public class Gtin {

    private String identifier;
    private String prefix;
    private String productId;

    public Gtin(String identifier, String prefix, String productId) {
        this.identifier = identifier;
        this.prefix = prefix;
        this.productId = productId;
    }


    public String toSQL() {
        return "INSERT INTO gtin (identifier, company_id, product_id, tag_type_id, print_template_id, created_user_id, created_time, modified_user_id) " +
                "VALUES ('" + identifier + "', (SELECT id FROM company WHERE prefix='" + prefix + "'), (SELECT id FROM product_temp WHERE identifier='" + productId + "'), 3, NULL, 1, NOW(), 1);\n";

    }
}
