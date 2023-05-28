import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CSVService {
    private static final String filePath = "audit.csv";
    FileWriter fileWriter;

    public CSVService() throws IOException {
        this.fileWriter = new FileWriter(filePath, true);


    }

    public void addRow(String name) throws IOException {
        if(new File(filePath).length() == 0) {
            fileWriter.append("Date");
            fileWriter.append(",");
            fileWriter.append("Action");
            fileWriter.append("\n");
            fileWriter.flush();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        fileWriter.append(name);
        fileWriter.append(", ");
        fileWriter.append(dtf.format(now));
        fileWriter.append("\n");
        fileWriter.flush();
    }
}
