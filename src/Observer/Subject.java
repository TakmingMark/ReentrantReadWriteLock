package Observer;

public interface Subject {

	public void attach(String architecture,Observer observer);
	
	public void detach(String architecture);
	
	public void notifyObserver(String action,String inputMsg) ;
}
