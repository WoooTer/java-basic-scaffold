package wooter.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Apache Common Logging 是一种接口规范
 *
 * 若 classpath 中没有 log4j，则使用 JUL
 * 若 classpath 中有 log4j，则使用 log4j
 */
public class JCLTest {

    private static Log logger = LogFactory.getLog(JCLTest.class);

    public static void main(String[] args) {
        logger.info("jcl logging");
    }
}
