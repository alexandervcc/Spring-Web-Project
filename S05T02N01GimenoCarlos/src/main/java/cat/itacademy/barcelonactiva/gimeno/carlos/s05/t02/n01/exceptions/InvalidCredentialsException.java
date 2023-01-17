package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException  {

    public InvalidCredentialsException(String msg) {
        super(msg);
    }
    
}
