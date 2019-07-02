package xpu.edu.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.edu.blog.entity.UserInfo;
import xpu.edu.blog.repository.UserRepository;
import xpu.edu.blog.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo addUser(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        Optional<UserInfo> findRet = userRepository.findById(userInfo.getUserId());
        if(!findRet.isPresent()) throw new RuntimeException("修改对应信息不存在");
        return userRepository.save(userInfo);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserInfo getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<UserInfo> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo getUserBuIdOrEmail(String idEmail, String password) {
        Integer id;
        try{
            id = Integer.parseInt(idEmail);
            UserInfo userById = userRepository.findByUserIdAndUserPassword(id, password);
            if(userById != null) return userById;
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return userRepository.findByUserEmailAndUserPassword(idEmail, password);
    }
}