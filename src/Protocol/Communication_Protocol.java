package Protocol;
import java.nio.channels.ReadableByteChannel;

public interface Communication_Protocol extends ReadWriteState_Protocol,Architecture_Protocol {
	int PROTOCOL_LEN=3;
	String SPLIT_SIGN="|";
}
