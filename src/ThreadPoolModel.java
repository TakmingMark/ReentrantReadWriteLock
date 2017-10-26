import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class ThreadPoolModel {
	
	private ThreadPoolExecutor executor;
	private TimeUnit unit;
	private BlockingQueue<Runnable> workQueue;
	
	private ThreadPoolModel(int maximumPoolSize,long keepAliveTime) {
		
		int corePoolSize=Runtime.getRuntime().availableProcessors();
		unit=TimeUnit.MILLISECONDS;
		workQueue= new ArrayBlockingQueue<Runnable>(50);
		
		executor=new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	public static ThreadPoolModel getThreadPoolModelObject(int maximumPoolSize,long keepAliveTime) {
		return new ThreadPoolModel(maximumPoolSize, keepAliveTime);
	}
	
	public void executeThreadPool(Runnable task) {
		executor.execute(task);
	}
	
	public boolean isWorkThreadPoolExecutor() {
		if(executor==null)
			return false;
		return true;
	}
	
	public void shutdown() {
		executor.shutdown();
	}
}
