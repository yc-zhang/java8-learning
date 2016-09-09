import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileStream {

    public static void main(String args[]) {
        try (Stream<String> lines = Files.lines(Paths.get("README.md"), Charset.defaultCharset())) {
            long count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .map(word -> {
                        System.out.println(word);
                        return word;
                    })
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
