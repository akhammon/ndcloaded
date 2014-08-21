/**
 * Created by Keene on 7/26/2014.
 */
public class Company {

    private String identifier;
    private String name;
    private String prefix;

    public Company(String identifier, String name, String prefix) {


        this.identifier = identifier;
        this.name = name.replaceAll("\'", "\\\'");
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "Company{identifier='" + identifier + "', name=\"" + name + "\", prefix='" + prefix + "'}";
    }

    public String toSQL() {
        return "INSERT INTO company " +
                "(identifier, name, prefix, created_user_id, created_time, modified_user_id) " +
                "VALUES " +
                "('" + identifier + "', \"" + name + "\", '" + prefix + "', 1, NOW(), 1);\n";
    }


}
