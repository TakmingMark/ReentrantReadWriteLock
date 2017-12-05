package Protocol;

public interface Communication_Protocol extends Architecture_Protocol {
	int PROTOCOL_LEN=3;
	String SPLIT_SIGN="|";
	
	String ONLY_SHOW_LAYOUT="1000";
	String SERVER_CONTROLLER="1001";
	String CLIENT_CONTROLLER="1002";
}
