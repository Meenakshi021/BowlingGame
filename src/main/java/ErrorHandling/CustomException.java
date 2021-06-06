package ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No of players not allowed ")
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}

}
