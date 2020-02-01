package wooter.io_learning.io.reader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ReaderTest {

    public void inputStream2reader() throws IOException {
        String originalString = "xxxksaddasdqwepoeq";
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        StringBuilder textBuilder = new StringBuilder();
        /**
         * try-with-resources
         */
        try (
                Reader reader = new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name()));
        ) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }

        System.out.println(textBuilder);
    }

    public void stringReader2string() throws IOException {
        StringReader reader = new StringReader("text");
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();
        System.out.println(targetString);
    }

    /**
     * 有坑，只能采用默认编码。若想指定字符编码，可以采用 FileInputStream
     */
    public void fileReader2string() throws IOException {
        File initialFile = new File(".gitignore");
        Reader reader = new FileReader(initialFile);
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();
        System.out.println(targetString);
    }

    public void string2fileWriter() throws IOException {
        File targetFile = new File("targetFile.temp");
        targetFile.createNewFile();

        Writer targetFileWriter = new FileWriter(targetFile);
        targetFileWriter.write("sdasdsadasdas");
        targetFileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        ReaderTest test = new ReaderTest();
        test.fileReader2string();

    }
}
