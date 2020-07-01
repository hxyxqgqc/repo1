package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     * 激活码查询用户信息
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     *  更改用户的激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 通过用户名，密码查询
     * @param username
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String username, String password);
}
