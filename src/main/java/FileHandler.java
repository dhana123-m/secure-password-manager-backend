import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "passwords.csv";

    public static List<String[]> readAll() throws IOException {
        List<String[]> records = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return records;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        }
        return records;
    }

    public static void writeAll(List<String[]> records) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] r : records) {
                bw.write(String.join(",", r));
                bw.newLine();
            }
        }
    }
}
