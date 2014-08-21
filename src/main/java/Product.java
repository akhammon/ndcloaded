/**
 * NDC Product File Definitions
 * NDC Directory - eLIST
 * Product File Data Elements, Definitions, and Notes
 * <p/>
 * Important Considerations Regarding the NDC Directory
 * <p/>
 * (NOTE: New field added 8/23/2013, ProductID)
 * <p/>
 * <p/>
 * NDC Directory is currently updated every Monday.
 * The NDC Directory contains ONLY information submitted to FDA in SPL electronic listing files by labelers. (A labeler
 * may be either a manufacturer, including a re-packager or re-labeler, or, for drugs subject to private labeling
 * arrangements, the entity under whose own label or trade name the product will be distributed.) Inclusion of
 * information in the NDC Directory does not indicate that FDA has verified the information provided. The content of
 * each NDC Directory entry is the responsibility of the labeler submitting the SPL file.
 * Assignment of an NDC number does not in any way denote FDA approval of the product. Any representation that creates
 * an impression of official approval because of possession of an NDC number is misleading and constitutes mis-branding.
 * (21 CFR 207.39)
 * Neither inclusion in the NDC Directory nor possession of an NDC number is a determination that a product is a drug as
 * defined by the FD&C Act, nor does either denote that a product is covered or eligible for reimbursement by Medicare,
 * Medicaid or other payers.
 * The NDC Directory does not contain all listed drugs. It does not include animal drugs, blood products, or human drugs
 * that are not in final marketed form, such as active pharmaceutical ingredients, or drugs that are marketed solely as
 * part of a kit or combination product or inner layer of a multi-level packaged product. For more information about how
 * certain kits or multi-level packed drugs are addressed in the new NDC Directory, see the NDC Directory Package File
 * Definitions document.
 * <p/>
 * File Notes
 * <p/>
 * Package data can be found in the Packages file, linked by the ProductID field.
 * Reference code names (translations) are included instead of the codes themselves.
 * Fields that have multiple values are identified with an “MV” after their name. Values are concatenated together by a
 * semi-colon “;”.
 * If the term NULL appears after an element name, it means there may be records where no value is provided.
 * <p/>
 * Created by Keene on 7/26/2014.
 */
public class Product {

    /**
     * Product File Data Elements and their definitions:
     */

    /**
     * ProductID  Text/string.
     * <p/>
     * ProductID is a concatenation of the NDC product code and SPL documentID. It is included to help
     * prevent duplicate rows from appearing when joining the product and package files together.  It has no regulatory
     * value or significance.
     */
    private String productId;

    /**
     * ProductNDC www.fda.gov/edrls under Structured Product Labeling Resources.   Text/string.
     * <p/>
     * The labeler code and product code segments of the National Drug Code number, separated by a hyphen. Asterisks are
     * no longer used or included within the product code segment to indicate certain configurations of the NDC.
     */
    private String productNDC;

    /**
     * ProductTypeName  Text/string.
     * <p/>
     * Indicates the type of product, such as Human Prescription Drug or Human OTC Drug. This data element corresponds
     * to the “Document Type” of the SPL submission for the listing. The complete list of codes and translations can be
     * found at
     */
    private String productTypeName;

    /**
     * ProprietaryName Text/string.
     * <p/>
     * Also known as the trade name. It is the name of the product chosen by the labeler.
     */
    private String proprietaryName;

    /**
     * ProprietaryNameSuffix NULL Text/string.
     * A suffix to the proprietary name, a value here should be appended to the ProprietaryName field to obtain the
     * complete name of the product. This suffix is often used to distinguish characteristics of a product such as
     * extended release (“XR”) or sleep aid (“PM”). Although many companies follow certain naming conventions for
     * suffices, there is no recognized standard.
     */
    private String proprietaryNameSuffix;

    /**
     * NonProprietaryName Text/string. MV
     * Sometimes called the generic name, this is usually the active ingredient(s) of the product.
     */
    private String nonProprietaryName;

    /**
     * DosageFormName Text/string.
     * The translation of the DosageForm Code submitted by the firm. The complete list of codes and translations can be
     * found www.fda.gov/edrls under Structured Product Labeling Resources.
     */
    private String dosageFormName;

    /**
     * RouteName  Text/string.  MV
     * The translation of the Route Code submitted by the firm, indicating route of administration. The complete list of
     * codes and translations can be found at www.fda.gov/edrls under Structured Product Labeling Resources.
     */
    private String routeName;

    /**
     * StartMarketingDate Text/string. [Include format]
     * This is the date that the labeler indicates was the start of its marketing of the drug product.
     */
    private String startMarketingDate;

    /**
     * EndMarketingDate  NULL Text/string. [Include format]
     * This is the date the product will no longer be available on the market. If a product is no longer being
     * manufactured, in most cases, the FDA recommends firms use the expiration date of the last lot produced as the
     * EndMarketingDate, to reflect the potential for drug product to remain available after manufacturing has ceased.
     * Products that are the subject of ongoing manufacturing will not ordinarily have any EndMarketingDate. Products
     * with a value in the EndMarketingDate will be removed from the NDC Directory when the EndMarketingDate is reached.
     */
    private String endMarketingDate;

    /**
     * MarketingCategoryName Text/string.
     * Product types are broken down into several potential Marketing Categories, such as NDA/ANDA/BLA, OTC Monograph,
     * or Unapproved Drug. One and only one Marketing Category may be chosen for a product, not all marketing categories
     * are available to all product types. Currently, only final marketed product categories are included.  The complete
     * list of codes and translations can be found at www.fda.gov/edrls under Structured Product Labeling Resources.
     */
    private String marketingCategoryName;

    /**
     * ApplicationNumber    NULL Text/string.
     * This corresponds to the NDA, ANDA, or BLA number reported by the labeler for products which have the
     * corresponding Marketing Category designated. If the designated Marketing Category is OTC Monograph Final or OTC
     * Monograph Not Final, then the Application number will be the CFR citation corresponding to the appropriate
     * Monograph (e.g. “part 341”). For unapproved drugs, this field will be null.
     */
    private String applicationNumber;

    /**
     * LabelerName Text/string.
     * Name of Company corresponding to the labeler code segment of the ProductNDC.
     */
    private String labelerName;

    /**
     * SubstanceName   Text/string. MV
     * This is the active ingredient list. Each ingredient name is the preferred term of the UNII code submitted.
     */
    private String substanceName;

    /**
     * StrengthNumber Text/string. MV
     * These are the strength values (to be used with units below) of each active ingredient, listed in the same order
     * as the SubstanceName field above.
     */
    private String strengthNumber;

    /**
     * StrengthUnit  Text/string. MV
     * These are the units to be used with the strength values above, listed in the same order as the SubstanceName and
     * SubstanceNumber.
     */
    private String strengthUnit;

    /**
     * Pharm_Classes Text/string. MV
     * These are the reported pharmacological class categories corresponding to the SubstanceNames listed above.
     */
    private String pharmClasses;

    /**
     * DEASchedule Text/string.
     * This is the assigned DEA Schedule number as reported by the labeler. Values are CI, CII, CIII, CIV, and CV.
     */
    private String deaSchedule;

    /**
     * Old Fields
     */
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
