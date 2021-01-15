package server.database;

public class JSONDatabaseResponse {

    private String response;
    private String reason;
    private String value;

    private JSONDatabaseResponse(String response) {
        this.response = response;
    }

    public static JSONDatabaseResponse noSuchKey() {
        var response = new JSONDatabaseResponse("ERROR");
        response.reason = "No such key";
        return response;
    }

    public static JSONDatabaseResponse responseWithValue(String value) {
        var response = ok();
        response.value = value;
        return response;
    }

    public static JSONDatabaseResponse ok() {
        return new JSONDatabaseResponse("OK");
    }
}
