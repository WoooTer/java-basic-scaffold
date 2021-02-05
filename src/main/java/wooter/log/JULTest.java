package wooter.log;

import java.util.logging.Logger;

/**
 * Java Util Logging 是一种JDK自带的实现
 */
public class JULTest {

    private static Logger logger = Logger.getLogger(JULTest.class.getName());

    public static void main(String[] args) {
        logger.info("jul logging");
    }

}
