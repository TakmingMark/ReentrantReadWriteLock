package Tools;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolModel {
	private ThreadPoolExecutor executor;
	private TimeUnit unit;
	private BlockingQueue<Runnable> workQueue;
	
	private ThreadPoolModel(int maximumPoolSize,long keepAliveTime) {	
		int corePoolSize=Runtime.getRuntime().availableProcessors();
		unit=TimeUnit.MILLISECONDS;
		workQueue= new SynchronousQueue<Runnable>();//Pass the player immediately
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
	
	public void getThreadPoolInformation(){
		System.out.println("executor.getActiveCount():"+executor.getActiveCount());
		System.out.println("executor.getPoolSize():"+executor.getPoolSize());
		System.out.println("executor.getCorePoolSize():"+executor.getCorePoolSize());
		System.out.println("executor.getMaximumPoolSize():"+executor.getMaximumPoolSize());
		System.out.println("executor.getLargestPoolSize():"+executor.getLargestPoolSize());
		System.out.println("executor.getTaskCount():"+executor.getTaskCount());
	}
}
