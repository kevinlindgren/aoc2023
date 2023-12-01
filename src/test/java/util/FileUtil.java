package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {
    
    public static List<String> readFile(String resourceName) {
        try {
            Path path = Paths.get(FileUtil.class.getClassLoader().getResource(resourceName).toURI());
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Caught URISyntaxException path: " + resourceName, e);
        }
    }
}
