package Main;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.smarttags.config.ConfigUtil;
import com.vcc.smarttags.config.SystemInfo;
import com.vcc.smarttags.hotterm.FrequencyTermImpl;
import com.vcc.smarttags.run.CreatePostTagsRun;

public class MainHotTerm {

	/**
	 * @param args
	 */
	private static Logger logger=Logger.getLogger("Batch");
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		DOMConfigurator.configure("log4j.xml");
		FrequencyTermImpl ft=new FrequencyTermImpl();
		ConfigUtil.loadConfig();
        StopWatch stopWatch = new StopWatch();
        while (true) {
            try {
            	logger.info("Start Date:"+new Date());
                stopWatch.start();
                ft.updateHotTerm();
                stopWatch.stop();
                logger.info("run time:" + stopWatch);
                logger.info("end Date:"+new Date());
            } catch (Exception exp) {
                logger.fatal("error", exp);
            }
            logger.info("done!!! We sleep " + SystemInfo.TIME_SLEEP_WHEN_RERUN_JOB_IN_SECS + " secs now!!");
            Thread.sleep(SystemInfo.TIME_SLEEP_WHEN_RERUN_JOB_IN_SECS * 1000);
        }

	}

}
