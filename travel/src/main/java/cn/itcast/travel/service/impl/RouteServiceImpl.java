package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.Route_img;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.Route_imgImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;
import com.sun.scenario.effect.impl.prism.PrImage;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private Route_img route_img = new Route_imgImpl();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param cid
     * @return
     */
    @Override
    public PageBean<Route> findByPage(int currentPage, int pageSize, int cid,String rname) {
        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        // 1. 获取总的页码数
        Integer totalCount = routeDao.findPageCount(cid,rname);
        pb.setTotalCount(totalCount);
        // 2. 我们计算出一共有几页数据
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize :(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        // 3. 查询分页信息
        int start = (currentPage - 1)*pageSize ;
        List<Route> list = routeDao.pageQuery(start, pageSize, cid,rname);
        pb.setList(list);
        return pb;
    }

    /**
     *   查询线路的详细信息
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        // 1. 现根据rid 查询到Route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        // 2. 根据查询到的route对象中sid，查询商家信息
        Seller seller = sellerDao.findSeller(route.getSid());
        route.setSeller(seller);
        // 3. 根据 route里的rid 查询 图片的集合
        List<RouteImg> list = route_img.findImgByRid(route.getRid());
        route.setRouteImgList(list);
        return route;
    }


}
