package Observer;

public interface Observer {

	/**
	 * provide Subject notify when it state happened layout change 
	 */
	public void update(String msg);
	
	/**
	 * provide Subject notify when it state happened transport message to user 
	 */
	public void transport(String msg);
}
