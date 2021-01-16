package server.database.args;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class JSONDatabaseArgs {

    private static final Gson GSON;
    static {

        GSON = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }


    @Parameter(names="-k") @Expose
    private String key;

    @Parameter(names="-t") @Expose
    private String type;

    @Parameter(names = "-v" )@Expose
    private String value;

    @Parameter(names = "-in", converter = JSONFileConverter.class)
    private String jsonString;

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static JSONDatabaseArgs parseCmdLineArgs(String ... args) {
        var jsonDatabaseArgs = new JSONDatabaseArgs();

        JCommander.newBuilder()
                .addObject(jsonDatabaseArgs)
                .build()
                .parse(args);

        if (Objects.nonNull(jsonDatabaseArgs.jsonString)) {
            jsonDatabaseArgs = unmarshalling(jsonDatabaseArgs.jsonString);
        }
        return jsonDatabaseArgs;
    }

    public static JSONDatabaseArgs unmarshalling(String jsonString) {
        return GSON.fromJson(jsonString, JSONDatabaseArgs.class);
    }

    public static String marshalling (JSONDatabaseArgs jsonDatabaseArgs) {
        return GSON.toJson(jsonDatabaseArgs);
    }

}
