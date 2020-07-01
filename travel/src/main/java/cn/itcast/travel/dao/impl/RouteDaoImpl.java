package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     *  分页查询
     * @param start
     * @param pageSize
     * @param cid
     * @return
     */
    @Override
    public List<Route> pageQuery(int start, int pageSize, int cid,String rname) {
        // 1. 定义模板sql
        String sql = "select * from tab_route where 1=1 ";
        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        List list = new ArrayList();
        // 2. 进行判断
        if(cid != 0){
            sb.append(" and cid = ? ");
            list.add(cid);
        }
        if(rname != null && rname.length() > 0 ){
            sb.append(" and rname like ?");
            list.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");
        list.add(start);
        list.add(pageSize);
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
    }

    /**
     *  查询总的页码数
     * @param cid
     * @return
     */
    @Override
    public Integer findPageCount(int cid,String rname) {
        // 1. 定义模板sql
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuffer sb = new StringBuffer();
        sb.append(sql);

        List list = new ArrayList();
        // 2. 判断cid和rname
        if(cid != 0){
            sb.append(" and cid = ? ");
            list.add(cid);
        }
        if(rname != null && rname.length() > 0 ){
            sb.append(" and rname like ?");
            list.add("%"+rname+"%");
        }
        sql = sb.toString();
        //查询时，我们要填cid 和 rname 但是我们不确定是否有值，
        // 这个时候，我们就用集合，在转换称数组

        return template.queryForObject(sql,Integer.class,list.toArray());
    }

    /**
     *  rid查询线路信息
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
