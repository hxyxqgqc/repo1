package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户,注册成功的同时保存用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }

        // 2.保存用户
        // 2.1 设置激活码，且是唯一字符串
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);

        // 3.给用户发送激活邮件
        // 3.1 设置正文
        String context = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>" +
                "点击激活【青春旅游网】</a>";
        // 3.2 通过javamail发送邮件
        MailUtils.sendMail(user.getEmail(), context, "激活邮件");
        return true;
    }

    /**
     * 用户激活
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        // 1. 根据激活码查询用户
        User user = userDao.findByCode(code);

        // 2. 如果存在。则激活码正确，同时修改该用户的激活状态
        if (user != null) {
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        User findUser = userDao.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        return findUser;
    }

}
