import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

interface Observer {

	/**
	 * provide Subject notify when it state happened change 
	 */
	public void update(String msg);
}
