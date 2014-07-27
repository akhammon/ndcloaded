/**
 * Created by Keene on 7/26/2014.
 */
public class DrugCode {

    private String identifier;
    private String product_id;

    public DrugCode(String identifier, String product_id) {
        this.identifier = identifier;
        this.product_id = product_id;

    }

    public String toSQL() {
        return "INSERT INTO  drug_code (identifier, drug_code_type_id, country_code_id, product_id, created_user_id, created_time, modified_user_id) VALUES ('" +
                identifier + "', 1, 236, (SELECT id FROM product_temp WHERE identifier='" + product_id + "'), 1, NOW(), 1);\n";
    }
}
