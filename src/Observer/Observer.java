package Observer;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public interface Observer {

	/**
	 * provide Subject notify when it state happened change 
	 */
	public void update(String msg);
}
