package com.pactera.gcw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.pactera.gcw.dao.mapper.GcwCityMapper;
import com.pactera.gcw.dao.model.GcwCity;
import com.pactera.gcw.dao.model.GcwCityExample;
import com.pactera.gcw.dao.model.GcwCityExample.Criteria;

@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
public class CityService implements ICityService {

    @Autowired
    private GcwCityMapper mapper;

    @Override
    public GcwCity getGcwCity(String code) {
        return mapper.selectByPrimaryKey(code);
    }

    @Override
    public boolean saveGcwCity(GcwCity city) {
        int res = mapper.insert(city);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveGcwCity(String code, String name) {
        GcwCity city = new GcwCity();
        city.setCode(code);
        city.setName(name);
        int res = mapper.insert(city);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGcwCity(String code) {
        int res = this.mapper.deleteByPrimaryKey(code);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGcwCity(GcwCity city) {
        return deleteGcwCity(city.getCode());
    }

    @Override
    @Cacheable(value = "com.pactera.gcw.dao.model.GcwCity", key = "#root.targetClass + #root.methodName")
    public List<GcwCity> getAllGcwCities() {
        GcwCityExample example = new GcwCityExample();
        Criteria criteria = example.createCriteria();
        return this.mapper.selectByExample(example);
    }

}
