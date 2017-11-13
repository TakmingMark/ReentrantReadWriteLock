package Protocol;
import java.nio.channels.ReadableByteChannel;

public interface Communcation_Protocol extends ReadWirteState_Protocol,Architecture_Protocol {
	int PROTOCOL_LEN=2;
	String SPLIT_SIGN=":";
	
	String M="M";
	String V="V";
	String C="C";
	String M_V="M_V";
	String M_C="M_C";
	String V_C="V_C";
	String M_V_C="M_V_C";
}
