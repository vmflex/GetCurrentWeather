package com.pactera.gcw.dao.mapper;

import com.pactera.gcw.dao.model.GcwCity;
import com.pactera.gcw.dao.model.GcwCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcwCityMapper {
    long countByExample(GcwCityExample example);

    int deleteByExample(GcwCityExample example);

    int deleteByPrimaryKey(String code);

    int insert(GcwCity record);

    int insertSelective(GcwCity record);

    List<GcwCity> selectByExample(GcwCityExample example);

    GcwCity selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") GcwCity record, @Param("example") GcwCityExample example);

    int updateByExample(@Param("record") GcwCity record, @Param("example") GcwCityExample example);

    int updateByPrimaryKeySelective(GcwCity record);

    int updateByPrimaryKey(GcwCity record);
}