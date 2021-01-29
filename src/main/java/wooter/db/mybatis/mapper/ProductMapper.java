package wooter.db.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import wooter.db.entity.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> selectList();

    int updateName(@Param("id") int id, @Param("name") String name);
}
