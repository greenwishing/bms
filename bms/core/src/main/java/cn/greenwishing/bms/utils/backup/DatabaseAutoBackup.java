package cn.greenwishing.bms.utils.backup;

import cn.greenwishing.bms.utils.BMSProperties;
import cn.greenwishing.bms.utils.JodaUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * @author Frank wu
 */
public class DatabaseAutoBackup {
    private static final Logger log = Logger.getLogger(DatabaseAutoBackup.class);

    private static final String format = ".s3db";
    private static final String separator = File.separator;

    private static final LocalDate YESTERDAY = JodaUtils.yesterday();

    public void backup() throws Exception {
        if (BMSProperties.isDevelopMode()) {
            log.info("develop mode enabled, process canceled.");
            return;
        }
        File monthFolder = initMonthFolder();

        String yesterday = YESTERDAY.toString("yyyyMMdd");
        String fileName = yesterday + format;

        File yesterdayFile = new File(monthFolder.getAbsolutePath() + separator + fileName);
        if (!yesterdayFile.exists()) {
            log.info("file " + yesterdayFile.getName() +  " is not exists, process database backup.");
            File original = new File(BMSProperties.get("database.original.path"));
            FileCopyUtils.copy(original, yesterdayFile);
            log.info("database backup finished. the new file path is: " + yesterdayFile.getAbsolutePath());
        } else {
            log.info("file " + yesterdayFile.getName() +  " is already backup, process finished.");
        }
    }

    private File initMonthFolder() {
        String year = YESTERDAY.toString("yyyy");
        File yearFolder = new File(BMSProperties.get("database.backup.dir") + separator + year);
        if (!yearFolder.exists()) {
            log.info("folder " + yearFolder.getName() +  " is not exists, created.");
            yearFolder.mkdirs();
        }
        String month = YESTERDAY.toString("yyyyMM");
        File monthFolder = new File(yearFolder.getAbsolutePath() + separator + month);
        if (!monthFolder.exists()) {
            log.info("folder " + monthFolder.getName() +  " is not exists, created.");
            monthFolder.mkdirs();
        }
        return monthFolder;
    }

}
