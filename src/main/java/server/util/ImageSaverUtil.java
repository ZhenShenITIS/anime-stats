package server.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static server.constants.IntegerConstants.DIRECTORIES_COUNT;
import static server.constants.StringConstants.FILE_PREFIX;

public class ImageSaverUtil {
    public static String save (String webUrl) {
        String[] urlParts = webUrl.split("/");
        String filename = urlParts[urlParts.length - 1];
        String tempFilePath = FILE_PREFIX.getValue() + File.separator
                + Math.abs(filename.hashCode()) % DIRECTORIES_COUNT.getValue() + File.separator + filename;
        File file = new File(tempFilePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path target = Path.of(tempFilePath);

        try (InputStream is = new URL(webUrl).openStream()){
            Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return tempFilePath;

    }
}
