package com.lpy.scm.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalLog extends BaseLog {
    private Logger logger = LoggerFactory.getLogger(GlobalLog.class);

    @Override
    public Logger getLogger() {
        return logger;
    }
}
