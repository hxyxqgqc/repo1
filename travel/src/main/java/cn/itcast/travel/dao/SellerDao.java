package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 *  卖家信息
 */
public interface SellerDao {
    /**
     *  根据id查询商家信息
     * @param sid
     * @return
     */
    Seller findSeller(int sid);
}
