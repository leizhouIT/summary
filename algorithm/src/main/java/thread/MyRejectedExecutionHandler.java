package thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

class MyRejectedExecutionHandler extends RejectedExecutionException implements RejectedExecutionHandler {

    protected final Log logger = LogFactory.getLog(getClass());
        public MyRejectedExecutionHandler(){}
        public MyRejectedExecutionHandler(String msg) {
            super(msg);
        }

        public MyRejectedExecutionHandler(String msg, Throwable cause) {
            super(msg, cause);
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                logger.error("任务已满,任务"+r.toString()+"当前executor"+executor);
        }
    }