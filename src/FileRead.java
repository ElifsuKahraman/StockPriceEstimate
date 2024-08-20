import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
    ArrayList<String> dataArray=new ArrayList<>();
    File file;
    public FileRead(File file){
        this.file=file;
    }

    public void readAllData()
    {
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                String price = row[1];
                System.out.println("Fiyat: " + (Double.parseDouble(price)));
//                for (String cell : row) {
//                    //System.out.print(cell + "\t");
//                }
                dataArray.add(price);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }





}
