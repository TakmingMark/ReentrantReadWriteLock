package Protocol;

public interface ReadWriteState_Protocol {
	String READ="Read";
	String CANCEL_READ="CancelRead";
	String WRITE="Write";
	String CANCEL_WRITE="CancelWrite";
	
	String READING="Reading";
	String WRITING="Writing";
	String CANCEL_READING="CancelReading";
	String CANCEL_WRITING="CancelWriting";
	String HAVE_READED="HaveReaded";
	String HAVE_WROTE="HaveWrote";
}
