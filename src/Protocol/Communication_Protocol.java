package Protocol;

public interface Communication_Protocol extends ReadWriteState_Protocol,Architecture_Protocol {
	int PROTOCOL_LEN=3;
	String SPLIT_SIGN="|";
}
