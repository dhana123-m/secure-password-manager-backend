import java.io.*;
import java.util.*;

public class PasswordManager {
    private static final String MASTER_FILE = "master.txt";
    private static final String SECRET_KEY = "myKey";

    public static boolean checkMaster(String input) throws IOException {
        File file = new File(MASTER_FILE);
        if (!file.exists()) return false;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String master = br.readLine();
        br.close();
        return input.equals(master);
    }

    public static void addEntry(String website, String username, String password) throws IOException {
        String encrypted = EncryptionUtil.encrypt(password, SECRET_KEY);
        List<String[]> records = FileHandler.readAll();
        records.add(new String[]{website, username, encrypted});
        FileHandler.writeAll(records);
    }

    public static List<PasswordEntry> search(String q) throws IOException {
        List<String[]> records = FileHandler.readAll();
        List<PasswordEntry> results = new ArrayList<>();
        for (String[] row : records) {
            if (row[0].contains(q) || row[1].contains(q)) {
                results.add(new PasswordEntry(row[0], row[1], EncryptionUtil.decrypt(row[2], SECRET_KEY)));
            }
        }
        return results;
    }
}
