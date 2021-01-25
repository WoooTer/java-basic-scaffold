package wooter.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import wooter.db.entity.Product;
import wooter.db.mybatis.config.SqlSessionFactoryContainer;
import wooter.db.mybatis.mapper.ProductMapper;

import java.util.List;

/**
 * <version>3.5.3</version>
 *
 * https://mybatis.org/mybatis-3/zh/getting-started.html
 * [mybatis 3.x源码深度解析与最佳实践](https://www.cnblogs.com/zhjh256/p/8512392.html)
 */
public class Main {

    public static void main(String[] args) {
        try (SqlSession session = SqlSessionFactoryContainer.getFactory().openSession()) {
            List<Product> productList = byMapper(session);
            System.out.println(productList.size());
        }
    }

    public static List<Product> bySessionSelectList(SqlSession session ){
        List<Product> productList = session.selectList("wooter.db.mybatis.mapper.ProductMapper.selectList");
        return productList;
    }

    public static List<Product> byMapper(SqlSession session ){
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        List<Product> productList = mapper.selectList();
        return productList;
    }
}
