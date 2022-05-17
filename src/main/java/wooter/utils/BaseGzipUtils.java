package wooter.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public abstract class BaseGzipUtils {

    /**
     * 压缩字符串
     *
     * @param uncompressedStr
     *            待压缩文本
     * @return 压缩文本
     */
    public static String compressGzip(String uncompressedStr) {
        if (StringUtils.isEmpty(uncompressedStr)) {
            return StringUtils.EMPTY;
        }

        String result = StringUtils.EMPTY;
        try {
            result = base64encode(gzipCompress(uncompressedStr.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解压字符串
     *
     * @param compressedStr
     *            压缩文本
     * @return 解压文本
     */
    public static String uncompressGzip(String compressedStr) {
        if (StringUtils.isEmpty(compressedStr)) {
            return StringUtils.EMPTY;
        }

        String result = StringUtils.EMPTY;
        try {
            result = new String(gzipUncompress(base64decode(compressedStr)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解压字符串，并转为对象
     * 
     * @param compressedStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T uncompressGzip(String compressedStr, Class<T> clazz) {
        String str = uncompressGzip(compressedStr);
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        T t = null;
        try {
            t = JSON.parseObject(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * base64编码
     * 
     * @param rawBytes
     * @return
     */
    public static String base64encode(byte[] rawBytes) {
        String encodedStr = StringUtils.EMPTY;
        try {
            encodedStr = new sun.misc.BASE64Encoder().encode(rawBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedStr;
    }

    /**
     * base64解码
     * 
     * @param encodedStr
     * @return
     */
    public static byte[] base64decode(String encodedStr) {
        byte[] rawBytes = new byte[] {};
        try {
            rawBytes = new sun.misc.BASE64Decoder().decodeBuffer(encodedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawBytes;
    }

    /**
     * gzip压缩
     * 
     * @param uncompressedData
     * @return
     */
    public static byte[] gzipCompress(byte[] uncompressedData) {
        byte[] result = new byte[] {};
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(uncompressedData.length)) {
            try (GZIPOutputStream gzipOS = new GZIPOutputStream(bos)) {
                gzipOS.write(uncompressedData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * gzip解压
     * 
     * @param compressedData
     * @return
     */
    public static byte[] gzipUncompress(byte[] compressedData) {
        byte[] result = new byte[] {};
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
            GZIPInputStream gzipIS = new GZIPInputStream(bis)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipIS.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            result = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
