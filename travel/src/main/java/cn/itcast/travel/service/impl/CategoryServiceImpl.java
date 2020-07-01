package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        // 1. 查询是否有缓存 从redis中查询
        // 1.1  获取Jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        // 1.2 用sortedset排序查询   （查询后的数据 从  1-8）
        //Set<String> categorys = jedis.zrange("category", 0, -1);

        /*
              如果只用zrang查询，cid的值就查询不了
         */
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);


        List<Category> list = null;
        // 2. 如果查询出来的是null或者值为0
        if(categorys == null || categorys.size() == 0){
            // 2.1 说明没有缓存，从数据库中查询
            list = categoryDao.findAll();
            // 2.2 并把数据作为缓存存储
            for (int i = 0; i < list.size(); i++) {
                jedis.zadd("category",list.get(i).getCid(),list.get(i).getCname());
            }
        }else {
            //如果不为空，就说明存在缓存,同时把set集合的数据存进list集合中
            list = new ArrayList<Category>();
            // 查询出来的就是name值
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                list.add(category);
            }
        }

        return list;
    }
}
