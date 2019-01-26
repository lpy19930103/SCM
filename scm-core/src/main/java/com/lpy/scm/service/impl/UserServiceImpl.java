package com.lpy.scm.service.impl;

import com.lpy.scm.dataobject.UserDO;
import com.lpy.scm.dao.UserMapper;
import com.lpy.scm.dto.UserDTO;
import com.lpy.scm.enums.system.GlobalIdBizType;
import com.lpy.scm.exception.BizException;
import com.lpy.scm.exception.ExceptionCode;
import com.lpy.scm.exception.ParamException;
import com.lpy.scm.manager.SystemConfigManager;
import com.lpy.scm.param.LoginParam;
import com.lpy.scm.service.UserService;
import com.lpy.scm.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SystemConfigManager systemConfigManager;

    @Override
    public UserDO queryUserById(String id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public UserDTO login(LoginParam loginParam) throws ParamException {
        UserDO userDo = userMapper.loginCheck(loginParam);
        if (userDo == null) {
            throw new BizException(ExceptionCode.PARAM_ERROR, "用户名不存在");
        }
        //登录错误次数大于5 判断是否距离上次登录时间超过一天
        if (userDo.getIsLocked() >= 5) {
            judgePwdAndUpdateLoginInfo(userDo, loginParam);
        } else {
            if (pwdAvailable(userDo.getUserPwd(), loginParam.getUserPass())) {
                return doPwdJudgePassWork(userDo);
            } else {
                updateErrorTimes(userDo.getUserId(), new Date(),
                        userDo.getIsLocked() + 1);
                throw new ParamException(ExceptionCode.PARAM_ERROR, "用户名或密码错误");
            }
        }
        return doPwdJudgePassWork(userDo);
    }

    /**
     * @description 密码校验通过更新错误登陆次数 增加登录记录
     */
    private UserDTO doPwdJudgePassWork(UserDO userDo) {
        UserDTO userDTO = BeanUtil.convertObject(userDo, UserDTO.class);
        updateErrorTimes(userDo.getUserId(), new Date(), 0);
        return userDTO;
    }


    /**
     * @description 校验密码更新登录错误次数
     */
    private UserDTO judgePwdAndUpdateLoginInfo(UserDO userDo, LoginParam loginParam) throws ParamException {
        Date lastLoginDate = userDo.getUpdateAt();
        if (lastLoginDatePassedLimitTime(lastLoginDate)) {
            if (pwdAvailable(userDo.getUserPwd(), loginParam.getUserPass())) {
                return doPwdJudgePassWork(userDo);
            } else {
                updateErrorTimes(userDo.getUserId(), new Date(), 1);
                throw new ParamException(ExceptionCode.PARAM_ERROR, "用户名密码错误");
            }
        } else {
            throw new BizException(ExceptionCode.BIZ_ERROR, "用户名或密码错误次数达到限制，账户被冻结");
        }
    }

    /**
     * @description 密码错误达到上限时 距离上次登录时间已经超过了限制时间 则允许登录
     */
    private boolean lastLoginDatePassedLimitTime(Date lastLoginDate) {
        return System.currentTimeMillis() - lastLoginDate.getTime() > 60 * 1000;

    }

    /**
     * @description 更新错误信息
     */
    private void updateErrorTimes(String userId, Date updateTime, int lockedNum) {
        UserDO userDO = new UserDO();
        userDO.setIsLocked(lockedNum);
        userDO.setUpdateAt(updateTime);
        Example example = new Example(UserDO.class);
        example.createCriteria().andEqualTo("userId", userId);
        userMapper.updateByExampleSelective(userDO, example);
    }

    /**
     * @description 校验密码是否有效
     */
    private boolean pwdAvailable(String dbPasswd, String pwdPram) {
        return dbPasswd.equals(pwdPram);
    }

}
