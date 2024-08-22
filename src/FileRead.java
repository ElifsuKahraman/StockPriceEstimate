import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
    ArrayList<Double> priceArray = new ArrayList<>();
    ArrayList<Data> dataArray = new ArrayList<>();
    double price;
    String date;
    File file;

    public FileRead(File file) {
        this.file = file;
    }

    public void checkData() {
        if (file != null && !file.exists()) {
            file.isDirectory();
        }
    }

    public void readAllData() {
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                price = Double.parseDouble(row[1]);
                date=row[0];
                System.out.println("Fiyat: " + price);
                Data data=new Data(price,date);
                priceArray.add(price);
                dataArray.add(data);
            }

//           for(Data data:dataArray){
//               System.out.println(data);
//           }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
