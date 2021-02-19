package wooter.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * [详解log4j2配置](https://blog.csdn.net/autfish/article/details/51203709)
 * [带你弄清混乱的JAVA日志体系](https://zhuanlan.zhihu.com/p/52272417)
 * [Java日志体系真的没你想的那么混乱](https://zhuanlan.zhihu.com/p/56496267)
 */
public class Log4jTest {

    private static Logger logger = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");
    }
}
