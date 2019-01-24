package com.lpy.scm.service;

import com.lpy.scm.DO.UserDO;

public interface UserService {
    UserDO queryUserById(Long id);
}
