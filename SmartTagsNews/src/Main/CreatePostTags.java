package Main;


import java.io.IOException;

import org.apache.log4j.Logger;

import com.ecyrd.speed4j.StopWatch;
import com.vcc.smarttags.config.ConfigUtil;
import com.vcc.smarttags.config.SystemInfo;
import com.vcc.smarttags.run.CreatePostTagsRun;

public class CreatePostTags {

	/**
	 * @param args
	 */
	private static Logger logger=Logger.getLogger("Main");
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		CreatePostTagsRun createPostTags = new CreatePostTagsRun();
		ConfigUtil.loadConfig();
        StopWatch stopWatch = new StopWatch();
        while (true) {
            try {
                stopWatch.start();
                createPostTags.run();
                stopWatch.stop();
                logger.info("run time:" + stopWatch);
            } catch (Exception exp) {
                logger.fatal("error", exp);
                System.out.println("Error:"+exp);
            }
            logger.info("done!!! We sleep " + SystemInfo.TIME_SLEEP_WHEN_RERUN_JOB_IN_SECS + " secs now!!");
            Thread.sleep(SystemInfo.TIME_SLEEP_WHEN_RERUN_JOB_IN_SECS * 1000);
        }
	}

}
