package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param cid
     * @return  list集合
     */
    public PageBean<Route> findByPage(int currentPage,int pageSize,int cid,String rname);

    /**
     *  根据id 查询线路的详细信息
     * @param rid
     * @return
     */
    Route findOne(String rid);
}
