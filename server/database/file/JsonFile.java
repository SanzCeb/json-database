package server.database.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class JsonFile {
    private final Path jsonFilePath;


    public JsonFile() {
        jsonFilePath = Path.of(System.getProperty("user.dir"), "src","server", "data", "db.json");
    }

    public void write (HashMap<String, String> jsonDatabase) {
        try (Writer writer = Files.newBufferedWriter(jsonFilePath, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)) {

            Gson gson = new Gson();
            gson.toJson(jsonDatabase, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> read() {
        try (Reader reader = Files.newBufferedReader(jsonFilePath, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
           HashMap<String, String> jsonDatabase = gson.fromJson(reader,
                    new TypeToken<HashMap<String, String>>(){}.getType());
            return jsonDatabase != null ? jsonDatabase : new HashMap<>();
        } catch (Exception ignored) {
            return new HashMap<>();
        }
    }

}
