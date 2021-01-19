package wooter.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import wooter.db.entity.Product;
import wooter.db.mybatis.config.SqlSessionFactoryContainer;
import wooter.db.mybatis.mapper.ProductMapper;

import java.util.List;

/**
 * https://mybatis.org/mybatis-3/zh/getting-started.html
 * [mybatis 3.x源码深度解析与最佳实践](https://www.cnblogs.com/zhjh256/p/8512392.html)
 */
public class Main {

    public static void main(String[] args) {
        try (SqlSession session = SqlSessionFactoryContainer.getFactory().openSession()) {
            ProductMapper mapper = session.getMapper(ProductMapper.class);
            List<Product> productList = mapper.selectList();
            System.out.println(productList.size());
        }
    }
}
