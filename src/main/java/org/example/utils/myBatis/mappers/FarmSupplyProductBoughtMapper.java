package org.example.utils.myBatis.mappers;

import org.apache.ibatis.annotations.*;
import org.example.domain.FarmSupplyProductBought;

import java.util.List;

public interface FarmSupplyProductBoughtMapper extends GenericMapper<FarmSupplyProductBought>{
    @Override
    @Insert("Insert into farms_supply_product_bought(amount,total,purchase_date,farm_id,product_id) values (#{farmSupply.amount},#{farmSupply.total},#{farmSupply.purchaseDate},#{farmSupply.farm_id},#{farmSupply.product_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(@Param("farmSupply") FarmSupplyProductBought data);

    @Override
    @Insert("Update farms_supply_product_bought set" +
            "  amount = #{farmSupply.amount}," +
            "total = #{farmSupply.total}," +
            "purchase_date = #{farmSupply.purchaseDate}," +
            "farm_id = #{farmSupply.farm_id}," +
            "product_id = #{farmSupply.product_id} where id = #{id}")
    Integer updateById(@Param("id") int id, @Param("farmSupply") FarmSupplyProductBought data);

    @Override
    @Delete("Delete from farms_supply_product_bought where id = #{id}")
    Integer deleteById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "amount",property = "amount"),
            @Result(column = "total",property = "total"),
            @Result(column = "purchase_date",property = "purchaseDate"),
            @Result(column = "farm_id",property = "farm_id"),
            @Result(column = "product_id",property = "product_id")
    })
    @Select("Select id,amount,total,purchase_date,farm_id,product_id from farms_supply_product_bought where id = #{id}")
    FarmSupplyProductBought findById(int id);

    @Override
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "amount",property = "amount"),
            @Result(column = "total",property = "total"),
            @Result(column = "purchase_date",property = "purchaseDate"),
            @Result(column = "farm_id",property = "farm_id"),
            @Result(column = "product_id",property = "product_id")
    })
    @Select("Select id,amount,total,purchase_date,farm_id,product_id from farms_supply_product_bought")
    List<FarmSupplyProductBought> findAll();
}
