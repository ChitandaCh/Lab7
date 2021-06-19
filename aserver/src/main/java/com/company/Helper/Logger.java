package com.company.Helper;

import com.company.Main;
import org.apache.logging.log4j.LogManager;

public class Logger {
    public org.apache.logging.log4j.Logger logger;

    public void Info(Object a){
        logger.info(a);
    }
    public void Error(Object error){
        logger.error(error);
    }

    public Logger(){
        logger = LogManager.getLogger(Main.class);
    }

}
