package com.lpy.scm.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lpy
 * @date 2019/1/24 17:31
 */
public class RedisLog extends BaseLog {

    private Logger logger = LoggerFactory.getLogger(RedisLog.class);

    @Override
    public Logger getLogger() {
        return this.logger;
    }
}
