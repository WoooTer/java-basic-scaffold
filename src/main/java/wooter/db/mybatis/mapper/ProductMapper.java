package wooter.db.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import wooter.db.entity.Product;

import java.util.List;

public interface ProductMapper {

//    @Select("select * from product")
    List<Product> selectList();
}
