package by.htp.exc;

@SuppressWarnings("serial")
public class IncorectFlowerException extends Exception{
	public IncorectFlowerException() {
		super();
	}

	@Override
	public String toString() {
		return "Incorrect flower! Imposible to predict!";
	}
}
