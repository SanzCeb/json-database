package server.database;

import com.beust.jcommander.Parameter;

public class JSONDatabaseArgs {

    @Parameter(names="-k")
    private String key;

    @Parameter(names="-t")
    private String type;

    @Parameter(names = "-v")
    private String value;

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }


}
