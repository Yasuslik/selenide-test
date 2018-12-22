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

        FileOutputStream out = new FileOutputStream("workbook.xlsx");
        workbook.write(out);
        out.close();
    }

    public static List getArrayProductsTest(List arrayProducts, boolean rangePrice, int min, int max){
        ArrayList<ArrayList<String>> resultProducts = new ArrayList<>();

        for (int i=0; i < arrayProducts.size()/2; i++) {
            int result = Integer.valueOf(arrayProducts.get(i*2+1).toString().replaceAll("[^\\d.]", ""));
            if (rangePrice) {
                if (result >= min && result <= max) {
                    ArrayList<String> singleProduct = new ArrayList<>();
                    singleProduct.add(arrayProducts.get(i * 2).toString());
                    singleProduct.add(arrayProducts.get(i * 2 + 1).toString().replaceAll("[^\\d.]", ""));
                    resultProducts.add(singleProduct);
                    resultProducts.sort(Comparator.comparing(e -> Integer.valueOf(e.get(1))));
                    Collections.reverse(resultProducts);
                }
            } else {
                ArrayList<String> singleProduct = new ArrayList<>();
                singleProduct.add(arrayProducts.get(i*2).toString());
                singleProduct.add(arrayProducts.get(i*2+1).toString().replaceAll("[^\\d.]", ""));
                resultProducts.add(singleProduct);
                resultProducts.sort(Comparator.comparing(e -> Integer.valueOf(e.get(1))));
                Collections.reverse(resultProducts);
            }
        }
        return resultProducts;
    }

}
