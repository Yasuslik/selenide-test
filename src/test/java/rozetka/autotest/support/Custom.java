package rozetka.autotest.support;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


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

    public static boolean sendEmail() {
        return SendMail.main();
    }

}
