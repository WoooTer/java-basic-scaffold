package wooter.algorithm.wooter.stringCompress;

import org.apache.commons.lang3.StringUtils;
import wooter.utils.MyTimer;

import java.time.LocalDateTime;
import java.util.UUID;

public class Base94Code {

    private static final int sourceDividedSize = 8;
    private static final int targetDividedSize = 5;

    /**
     * 将32位uuid压缩至20位长度，压缩比为0.625
     * @param uuid
     * @return
     */
    public static String compressUUID(String uuid){
        uuid = uuid.replace("-","");
        if (uuid.length() != 32){
            throw new RuntimeException("uuid长度不是32位！");
        }
        DecimalConverter converter = new DecimalConverter(16, 94);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < uuid.length(); i += sourceDividedSize){
            String part = converter.convert(uuid.substring(i, i + sourceDividedSize));
            result.append(StringUtils.leftPad(part, targetDividedSize, "0"));
        }
        return result.toString();
    }

    /**
     * 将压缩过的uuid还原
     * @param strCompressed
     * @return
     */
    public static String decompressUUID(String strCompressed){
        DecimalConverter converter = new DecimalConverter(16, 94);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strCompressed.length(); i += targetDividedSize){
            String part = converter.recoverToStr(strCompressed.substring(i, i + targetDividedSize));
            result.append(StringUtils.leftPad(part, sourceDividedSize, "0"));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);

        LocalDateTime start = LocalDateTime.now(); //Timer
        String compressed = Base94Code.compressUUID(uuid);
        System.out.println(compressed);
        MyTimer.getDurationToMillis(start); //Timer

        String decompressed = Base94Code.decompressUUID(compressed);
        System.out.println(decompressed);
        MyTimer.getDurationToMillis(start); //Timer
    }

}
