package cn.greenwishing.bms.web.listener;

import cn.greenwishing.bms.utils.backup.DatabaseAutoBackup;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Wu Fan
 */
public class DatabaseBackupListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(DatabaseBackupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("database backup: initialization started");
        long startTime = System.currentTimeMillis();
        try {
            new DatabaseAutoBackup().backup();
        } catch (Exception e) {
            log.error("database backup error: " + e);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info("database backup: initialization completed in " + (endTime - startTime) + "ms.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("database backup destroyed.");
    }
}
