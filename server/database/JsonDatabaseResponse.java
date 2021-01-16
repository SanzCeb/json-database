package server.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDatabaseResponse {

    private String response;
    private String reason;
    private String value;
    private static final Gson GSON = new GsonBuilder().create();
    private JsonDatabaseResponse(String response) {
        this.response = response;
    }

    public static JsonDatabaseResponse noSuchKey() {
        var response = new JsonDatabaseResponse("ERROR");
        response.reason = "No such key";
        return response;
    }

    public static JsonDatabaseResponse responseWithValue(String value) {
        var response = ok();
        response.value = value;
        return response;
    }

    public static JsonDatabaseResponse ok() {
        return new JsonDatabaseResponse("OK");
    }

    public String getResponse() {
        return response;
    }

    public String marshalling() {
        return GSON.toJson(this);
    }
}
