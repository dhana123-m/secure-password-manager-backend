
import static spark.Spark.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;

public class PasswordAPI {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        port(getAssignedPort());
        System.out.println("✅ Secure Password Manager API started...");

        get("/", (req, res) -> "Secure Password Manager API is running ✅");

        post("/api/checkMaster", (req, res) -> {
            Map<String, String> data = gson.fromJson(req.body(), Map.class);
            boolean valid = PasswordManager.checkMaster(data.get("password"));
            res.type("application/json");
            return gson.toJson(Map.of("valid", valid));
        });

        post("/api/add", (req, res) -> {
            Map<String, String> data = gson.fromJson(req.body(), Map.class);
            PasswordManager.addEntry(
                data.get("website"), data.get("username"), data.get("password")
            );
            res.type("application/json");
            return gson.toJson(Map.of("status", "added"));
        });

        get("/api/search", (req, res) -> {
            String q = req.queryParams("q");
            List<PasswordEntry> results = PasswordManager.search(q);
            res.type("application/json");
            return gson.toJson(results);
        });
    }

    static int getAssignedPort() {
        String port = System.getenv("PORT");
        return port != null ? Integer.parseInt(port) : 4567;
    }
}
