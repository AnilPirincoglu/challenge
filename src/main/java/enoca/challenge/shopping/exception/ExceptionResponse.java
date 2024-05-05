package enoca.challenge.shopping.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@AllArgsConstructor
@Data
public class ExceptionResponse {

    private String message;
    private Instant timeStamp;
}
