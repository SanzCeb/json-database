package server.database.args;

import com.beust.jcommander.IStringConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class JSONFileConverter implements IStringConverter<String> {
    private static final String CLIENT_DATA_DIR;
    private static final String CLIENT_DATA_USR_DIR;
    static {
        var usrDir = System.getProperty("user.dir");
        CLIENT_DATA_DIR = String.join(File.separator, "src", "client", "data");
        CLIENT_DATA_USR_DIR = String.join(File.separator, usrDir, CLIENT_DATA_DIR);
    }
    @Override
    public String convert(String value) {
        try {
            var inputFilePath = Path.of(CLIENT_DATA_USR_DIR, value);
            return Files.readString(inputFilePath);
        } catch (IOException e) {
            return  null;
        }
    }
}
