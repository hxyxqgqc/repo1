package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 *  线路图片
 */
public interface Route_img {
    /**
     *  查询图片集合 by rid
     * @param rid
     * @return
     */
    List<RouteImg> findImgByRid(int rid);
}
