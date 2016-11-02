package com.xy.xyblog.service.user;


import com.xy.xyblog.bean.User;
import com.xy.xyblog.bean.inbean.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by XiuYang on 2016/10/25.
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
