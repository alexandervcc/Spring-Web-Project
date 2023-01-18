package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.ErrorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException ex) throws IOException, ServletException {
        ErrorDto errorDto = ErrorDto.builder().code(HttpStatus.UNAUTHORIZED.value())
                .error(ex.getMessage()).build();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, errorDto);
        out.flush();
    }

}
