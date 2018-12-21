package rozetka.autotest.support;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.*;


public class Custom {

    public static boolean writeToFile(List productList) {
        for (Object i:productList) {
            try {
                FileWriter writer = new FileWriter("ProductList.txt", true);
                writer.write(i.toString());
                writer.write("\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean deleteFile() {
        File file = new File("ProductList.txt");
        if(file.delete()){
            System.out.println("ProductList.txt файл был удален с корневой папки проекта");
        }else System.out.println("ProductList.txt не был найден в корневой папке проекта");
        return true;
    }

    public static boolean sendEmail(String setFileName) {
        return SendMail.send(setFileName);
    }


    public static void writeToExcel(List<ArrayList<String>> productsFirstSheet, List<ArrayList<String>> productsSecondSheet) throws IOException {
        Workbook workbook = new SXSSFWorkbook();
        int i = 0, j = 0;

        Sheet firstSheet = workbook.createSheet("Products");
        for(List<String> product : productsFirstSheet){
            Row row = firstSheet.createRow(i);

            Cell cell = row.createCell(0);
            cell.setCellValue(product.get(0));
            Cell cel2 = row.createCell(1);
            cel2.setCellValue(Integer.parseInt(product.get(1).replace(" ", "")));
            i++;
        }

        Sheet secondSheat = workbook.createSheet("MostPopular");
        for(List<String> product : productsSecondSheet){
            Row row = secondSheat.createRow(j);

            Cell cell = row.createCell(0);
            cell.setCellValue(product.get(0));
            Cell cel2 = row.createCell(1);
            cel2.setCellValue(Integer.parseInt(product.get(1).replace(" ", "")));
            j++;
        }

        // Voila!
        FileOutputStream out = new FileOutputStream("workbook.xlsx");
        workbook.write(out);
        out.close();
    }

    public static String[][] getArrayProducts(Object[] arrayProduct, boolean rangePrice, int min, int max){
        int chunk = 2;
        int step = 0;
        int productsNameAndPriceLength = arrayProduct.length / 2;
        String[][] resultProductsList = new String[90][2];
        for(int i=0; i < arrayProduct.length; i += chunk){
            String productAndPrice = Arrays.toString(Arrays.copyOfRange(arrayProduct, i, Math.min(arrayProduct.length,i+chunk)));
            String priceOnly = productAndPrice.replace(" грн", "");
            String[] arrayProductAndPrice = priceOnly.split(", ");

            String price = arrayProductAndPrice[1].replace(" ", "");
            price = price.replace("]", "");
            if (rangePrice) {
                int result = Integer.parseInt(price);
                if (result >= min && result <= max) {
                    resultProductsList[step][0] = arrayProductAndPrice[0];
                    resultProductsList[step][1] = price;
                    step++;
                    System.out.println(arrayProductAndPrice[0]);
                    System.out.println(resultProductsList[step][1]);
                }
            }else{
                resultProductsList[step][0] = arrayProductAndPrice[0];
                resultProductsList[step][1] = price;
                step++;
            }
        }
        return resultProductsList;
    }

    public static List getArrayProductsTest(List arrayProducts, boolean rangePrice, int min, int max){
        String products;

        for (int i=0; i < arrayProducts.size(); i++) {
            products = arrayProducts.get(i).toString();
            products = products.replace(" грн", "");
            products = products.replace(", ", " ");
            arrayProducts.set(i, products);
        }

        ArrayList<ArrayList<String>> resultProducts = new ArrayList<>();

        for (int i=0; i < arrayProducts.size()/2; i++) {
            System.out.println(arrayProducts.get(i*2+1).toString().replace(" ", ""));
            int result = Integer.parseInt(arrayProducts.get(i*2+1).toString().replace(" ", ""));
            if (rangePrice) {
                if (result >= min && result <= max) {
                    ArrayList<String> singleProduct = new ArrayList<>();
                    singleProduct.add(arrayProducts.get(i * 2).toString());
                    singleProduct.add(arrayProducts.get(i * 2 + 1).toString().replace(" ", ""));
                    resultProducts.add(singleProduct);
                    resultProducts.sort(Comparator.comparing(e -> Integer.valueOf(e.get(1).replace(" ", ""))));
                    Collections.reverse(resultProducts);
                }
            } else {
                ArrayList<String> singleProduct = new ArrayList<>();
                singleProduct.add(arrayProducts.get(i*2).toString());
                singleProduct.add(arrayProducts.get(i*2+1).toString());
                resultProducts.add(singleProduct);
                resultProducts.sort(Comparator.comparing(e -> Integer.valueOf(e.get(1).replace(" ", ""))));
                Collections.reverse(resultProducts);
            }
        }
        return resultProducts;
    }

}
