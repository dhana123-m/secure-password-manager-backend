import static spark.Spark.*;
import java.util.*;

public class PasswordAPI {

    public static void main(String[] args) {
        port(getAssignedPort());
        enableCORS();   // ✅ allow GitHub Pages frontend
        System.out.println("✅ Secure Password Manager API started...");

        // ---- ROUTES ----
        get("/", (req, res) -> "Secure Password Manager API is running ✅");

        // ✅ Master password check
        post("/api/checkMaster", (req, res) -> {
            String master = req.queryParams("password");
            boolean valid = PasswordManager.checkMaster(master);
            res.type("text/plain");
            return valid ? "valid" : "invalid";
        });

        // ✅ Add new entry
        post("/api/add", (req, res) -> {
            String website  = req.queryParams("website");
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            PasswordManager.addEntry(website, username, password);
            res.type("text/plain");
            return "Password added successfully!";
        });

        // ✅ Search entries
        get("/api/search", (req, res) -> {
            String q = req.queryParams("q");
            List<PasswordEntry> results = PasswordManager.search(q);
            StringBuilder sb = new StringBuilder();
            for (PasswordEntry e : results) {
                sb.append(e.toString()).append("\n");
            }
            res.type("text/plain");
            return sb.toString();
        });
    }

    static int getAssignedPort() {
        String port = System.getenv("PORT");
        return port != null ? Integer.parseInt(port) : 4567;
    }

    // ✅ CORS fix for GitHub Pages frontend
    private static void enableCORS() {
        options("/*", (req, res) -> {
            String headers = req.headers("Access-Control-Request-Headers");
            if (headers != null) res.header("Access-Control-Allow-Headers", headers);

            String methods = req.headers("Access-Control-Request-Method");
            if (methods != null) res.header("Access-Control-Allow-Methods", methods);

            return "OK";
        });

        before((req, res) -> {
            // Restrict to your frontend URL if you want tighter security:
            // res.header("Access-Control-Allow-Origin", "https://dhana123-m.github.io");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
        });
    }
}
