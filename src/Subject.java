
interface Subject {

	public void attach(Observer observer,Architecture architecture);
	
	public void detach(Observer observer);
	
	public void notifyObserver(Architecture action,String inputMsg) ;
}
