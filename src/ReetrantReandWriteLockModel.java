import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class ReetrantReandWriteLockModel {

	private final ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private final Lock readLock=readWriteLock.readLock();
	private final Lock writeLock=readWriteLock.writeLock();
	
	
}
