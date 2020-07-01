package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     *  分页查询
     * @param start
     * @param pageSize
     * @param cid
     * @return
     */
    List<Route> pageQuery(int start, int pageSize, int cid,String rname);

    /**
     * 通过cid查询总的页码数
     * @param cid
     * @return
     */
    public Integer findPageCount(int cid,String rname);

    /**
     *  根据rid查询 线路信息
     * @param rid
     * @return
     */
    public Route findOne(int rid);

}
