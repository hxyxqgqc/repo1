package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.Route_img;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



import java.util.List;

public class Route_imgImpl implements Route_img {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findImgByRid(int rid) {
        return  template.query("select * from tab_route_img where rid = ? ",
                new BeanPropertyRowMapper<RouteImg>(RouteImg.class),
                rid);
    }
}
