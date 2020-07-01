package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;
import java.util.Locale;

public interface CategoryDao {

    /**
     * 查询首页的所有信息
     * @return
     */
    List<Category> findAll();
}
