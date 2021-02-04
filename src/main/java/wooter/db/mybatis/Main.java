package wooter.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import wooter.db.entity.Product;
import wooter.db.mybatis.config.SqlSessionFactoryContainer;
import wooter.db.mybatis.mapper.ProductMapper;

import java.util.List;

/**
 * <version>3.5.3</version>
 * [官网](https://mybatis.org/mybatis-3/zh/getting-started.html)
 *
 * [mybatis 3.x源码深度解析与最佳实践](https://www.cnblogs.com/zhjh256/p/8512392.html)
 * [Mybatis源码解读-设计模式总结](http://www.crazyant.net/2022.html)
 *
 * [mybatis缓存机制](https://www.cnblogs.com/wuzhenzhao/p/11103043.html)
 * [聊聊MyBatis缓存机制-美团技术](https://tech.meituan.com/2018/01/19/mybatis-cache.html)
 *
 * [自己手写一个Mybatis框架(简化)](https://my.oschina.net/liughDevelop/blog/1631006)
 */
public class Main {

    public static void main(String[] args) {
        updateManualCommit();
    }

    public static void selectBySessionSelectList() {
        try (SqlSession session = SqlSessionFactoryContainer.getFactory().openSession()) {
            List<Product> products = session.selectList("wooter.db.mybatis.mapper.ProductMapper.selectList");
            System.out.println(products);
        }
    }

    public static void selectByMapper() {
        try (SqlSession session = SqlSessionFactoryContainer.getFactory().openSession()) {
            ProductMapper mapper = session.getMapper(ProductMapper.class);
            List<Product> products = mapper.selectList();
            System.out.println(products);
        }
    }

    public static void updateAutoCommit() {
        try (SqlSession session = SqlSessionFactoryContainer.getFactory().openSession(true)) {
            ProductMapper mapper = session.getMapper(ProductMapper.class);
            int count = mapper.updateName(6, "手套Auto");
            System.out.println(count);
        }
    }

    public static void updateManualCommit() {
        try (SqlSession session = SqlSessionFactoryContainer.getFactory().openSession()) {
            try {
                ProductMapper mapper = session.getMapper(ProductMapper.class);
                int count = mapper.updateName(6, "手套Manual");
                System.out.println(count);
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
            }
        }
    }
}
