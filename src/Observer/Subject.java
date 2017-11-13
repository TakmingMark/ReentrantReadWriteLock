
interface Subject {

	public void attach(Observer observer,String architecture);
	
	public void detach(Observer observer);
	
	public void notifyObserver(String action,String inputMsg) ;
}
