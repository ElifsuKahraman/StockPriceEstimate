import java.io.File;
public class Main {
    public static void main(String[] args) {
        File file=new File("C:/Users/kelif/Desktop/stock_price.csv");
        FileRead fileRead=new FileRead(file);
        fileRead.readAllData();

    }

}
