package xpu.edu.blog.service;

import xpu.edu.blog.entity.UserInfo;

import java.util.List;

public interface UserService {
    /**
     * 新增用户
     * @param userInfo 用户信息
     * @return 新增成功的用户信息
     */
    UserInfo addUser(UserInfo userInfo);


    /**
     * 修改用户
     * @param userInfo 用户信息
     * @return 修改成功的用户信息
     */
    UserInfo updateUser(UserInfo userInfo);


    /**
     * 删除用户
     * @param userId 用户Id
     */
    void deleteUser(String userId);


    /**
     * 查找用户
     * @param userId 用户Id
     * @return 用户信息
     */
    UserInfo getUserById(String userId);


    /**
     * 所有用户信息
     * @return 所有用户信息集合
     */
    List<UserInfo> listUsers();


    UserInfo getUserBuIdOrEmail(String idEmail, String password);
}
