import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by Keene on 7/26/2014.
 */
public class NdcLoader {


    public enum PRODUCT_COLUMN {
        PRODUCTID, //1
        PRODUCTNDC, //2
        PRODUCTTYPENAME,//3
        PROPRIETARYNAME, //4
        PROPRIETARYNAMESUFFIX,//5
        NONPROPRIETARYNAME,//6
        DOSAGEFORMNAME,//7
        ROUTENAME,//8
        STARTMARKETINGDATE,//9
        ENDMARKETINGDATE,//10
        MARKETINGCATEGORYNAME,//11
        APPLICATIONNUMBER,//12
        LABELERNAME,//13
        SUBSTANCENAME,
        ACTIVE_NUMERATOR_STRENGTH,
        ACTIVE_INGRED_UNIT,
        PHARM_CLASSES,
        DEASCHEDULE;


    }

    public static void main(String[] args) {

        ArrayList<Company> companies = new ArrayList();
        ArrayList<Product> products = new ArrayList();
        ArrayList<DrugCode> drugCodes = new ArrayList();
        ArrayList<Gtin> gtins = new ArrayList();


        String productId = null;
        String drugCodeIdentifier = null;
        String productDescription = null;
        String companyName = "";
        String prefix = "";

        TreeSet<String> prefixSet = new TreeSet();

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("./product.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            switch (c) {
                                case 0:
                                    productId = cell.getStringCellValue();
                                    StringTokenizer prefixTokens = new StringTokenizer(productId, "-");
                                    while (null != prefixTokens && prefixTokens.hasMoreTokens()) {
                                        prefix = prefixTokens.nextToken();
                                        break;
                                    }
                                    break;
                                case 1:
                                    drugCodeIdentifier = cell.getStringCellValue();
                                    break;

                                case 5:
                                    productDescription = cell.getStringCellValue();
                                    break;
                                case 12:

                                    String tempCompanyName = cell.getStringCellValue();


                                    if (!companyName.equals(cell.getStringCellValue())) {
                                        companyName = cell.getStringCellValue();
                                        if (!prefixSet.contains(prefix)) {
                                            prefixSet.add(prefix);

                                            StringTokenizer identifierTokens = new StringTokenizer(tempCompanyName);
                                            StringBuilder builder = new StringBuilder();
                                            while (null != identifierTokens && identifierTokens.hasMoreTokens()) {
                                                String identifierToken = identifierTokens.nextToken();
                                                if (StringUtils.isAlphanumeric(identifierToken) || identifierToken.contains("-") || identifierToken.endsWith(",")) {
                                                    if (identifierToken.contains("-")) {
                                                        StringTokenizer dashTokens = new StringTokenizer(identifierToken, "-");
                                                        while (null != dashTokens && dashTokens.hasMoreTokens()) {
                                                            String dashToken = dashTokens.nextToken();
                                                            String uppercaseFirstLetter = dashToken.substring(0, 1).toUpperCase();
                                                            builder.append(uppercaseFirstLetter);
                                                        }
                                                    } else {
                                                        String uppercaseFirstLetter = identifierToken.substring(0, 1).toUpperCase();
                                                        builder.append(uppercaseFirstLetter);
                                                    }
                                                }
                                            }
                                            String identifier = builder.toString() + prefix;

                                            Company company = new Company(identifier, companyName, prefix);

                                            companies.add(company);

                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
                products.add(new Product(productId, productDescription));
                drugCodes.add(new DrugCode(drugCodeIdentifier, productId));
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }


        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("./package.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;


            String gtinIdentifier = null;
            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            switch (c) {
                                case 0:
                                    productId = cell.getStringCellValue();
                                    StringTokenizer prefixTokens = new StringTokenizer(productId, "-");
                                    while (null != prefixTokens && prefixTokens.hasMoreTokens()) {
                                        prefix = prefixTokens.nextToken();
                                        break;
                                    }
                                    break;
                                case 2:
                                    gtinIdentifier = cell.getStringCellValue();
                                    break;


                            }
                        }
                    }
                }
                gtins.add(new Gtin(gtinIdentifier, prefix, productId));
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        try {

            FileWriter writer = new FileWriter("company.sql");


            for (Company company : companies) {
                writer.write(company.toSQL());
            }
            writer.flush();
            writer.close();

            writer = new FileWriter("product.sql");

            for (Product product : products) {
                writer.write(product.toSQL());
            }
            writer.flush();
            writer.close();
            writer = new FileWriter("drugCode.sql");

            for (DrugCode drugCode : drugCodes) {
                writer.write(drugCode.toSQL());
            }
            writer.flush();
            writer.close();
            writer = new FileWriter("gtin.sql");

            for (Gtin gtin : gtins) {
                writer.write(gtin.toSQL());
            }

            writer.flush();
            writer.close();

        } catch (Exception e) {

        }

    }
}
