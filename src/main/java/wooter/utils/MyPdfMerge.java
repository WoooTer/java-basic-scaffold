package wooter.utils;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyPdfMerge {

    public static void main(String[] args) throws Exception {
        PDFMergerUtility ut = new PDFMergerUtility();

        String base64 = readPdfCode();
        ut.addSource(base64ToInputStream(base64));
        ut.addSource(base64ToInputStream(base64));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ut.setDestinationStream(os);
        ut.mergeDocuments(null);

        String base64merged = outputStreamToBase64(os);
        System.out.println(base64merged);
    }

    public static InputStream base64ToInputStream(String base64) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = decoder.decodeBuffer(base64);
        return new ByteArrayInputStream(decodedBytes);
    }

    public static String outputStreamToBase64(ByteArrayOutputStream os) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(os.toByteArray());
    }

    public static String readPdfCode() throws Exception {
        InputStream is = MyPdfMerge.class.getClassLoader().getResourceAsStream("pdf/base64.txt");

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

}
