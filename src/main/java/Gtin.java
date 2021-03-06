/**
 * NDC Package File Definitions
 * NDC Directory - eLIST
 * Package File Data Elements, Definitions, and Notes
 * <p/>
 * Important Considerations Regarding the NDC Directory
 * <p/>
 * (NOTE: New field added 8/23/2013, ProductID)
 * <p/>
 * <p/>
 * NDC Directory is updated daily.
 * <p/>
 * The NDC Directory contains ONLY information on final marketed drugs submitted to FDA in SPL electronic listing files
 * by labelers. (A labeler may be either a manufacturer, including a repackager or relabeler, or, for drugs subject to
 * private labeling arrangements, the entity under whose own label or trade name the product will be distributed.)
 * Inclusion of information in the NDC Directory does not indicate that FDA has verified the information provided. The
 * content of each NDC Directory entry is the responsibility of the labeler submitting the SPL file.Assignment of an NDC
 * number does not in any way denote FDA approval of the product. Any representation that creates an impression of
 * official approval because of possession of an NDC number is misleading and constitutes misbranding. (21 CFR 207.39)
 * <p/>
 * Assignment of an NDC number does not in any way denote FDA approval of the product. Any representation that creates
 * an impression of official approval because of possession of an NDC number is misleading and constitutes misbranding.
 * (21 CFR 207.39)
 * <p/>
 * Neither inclusion in the NDC Directory nor possession of an NDC number is a determination that a product is a drug as
 * defined by the FD&C Act, nor does either denote that a product is covered or eligible for reimbursement by Medicare,
 * Medicaid or other payers. Assignment of NDC number to non-drug products is extremely prohibited.
 * <p/>
 * The NDC Directory does not contain all listed drugs. The new version includes the final marketed drugs which listing
 * information were submitted electronically. It does not include animal drugs, blood products, or human drugs that are
 * not in final marketed form, such as Active Pharmaceutical Ingredients (APIs), drugs for further processing, drugs
 * manufactured exclusively for a private label distributor, or drugs that are marketed solely as part of a kit or
 * combination product or inner layer of a multi-level packaged product not marketed individually.
 * <p/>
 * File notes:
 * <p/>
 * Only the OUTERMOST packages and dispensable inner layer packages, reported by firms as part of their product listing
 * submission to the FDA, are included in the NDC Directory. This applies to co-packaged products listed as kits and
 * multi-level packaged products.
 * <p/>
 * The inner level of a multi level packaging or components of a kit will be included in the package description of the
 * product to show the relationship between all layers and parts as reported by the listing firm.
 * <p/>
 * If a product contained within a kit is approved/ authorized to be marketed separately, then a separate SPL file must
 * be submitted.
 * <p/>
 * If the inner level of a multi level packaging is a marketable/ dispensable layer, the NDCPackageCode assigned to it
 * should be reported as a separate package code on the same listing SPL.
 * <p/>
 * The relation between different levels of a multi level packaged product or components of a kit is still provided in
 * the package description section. For any additional information, users can go to the FDA Online Label Repository
 * page: http://labels.fda.gov/
 * <p/>
 * Created by Keene on 7/26/2014.
 */
public class Gtin {

    private String productId;
    private String identifier;
    private String prefix;

    /**
     *
     * @param identifier
     * @param prefix
     * @param productId
     */
    public Gtin(String identifier, String prefix, String productId) {
        this.identifier = identifier;
        this.prefix = prefix;
        this.productId = productId;
    }

    /**
     *
     * @return
     */
    public String toSQL() {
        return "INSERT INTO gtin (identifier, company_id, product_id, tag_type_id, print_template_id, created_user_id, created_time, modified_user_id) " +
                "VALUES ('" + identifier + "', (SELECT id FROM company WHERE prefix='" + prefix + "'), (SELECT id FROM product_temp WHERE identifier='" + productId + "'), 3, NULL, 1, NOW(), 1);\n";

    }
}
