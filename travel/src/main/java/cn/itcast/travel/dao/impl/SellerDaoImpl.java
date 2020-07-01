package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     *  查询商家信息
     * @param sid
     * @return
     */
    @Override
    public Seller findSeller(int sid) {
        return template.queryForObject("select * from tab_seller where sid = ?",
                                            new BeanPropertyRowMapper<Seller>(Seller.class),
                                            sid);
    }
}
