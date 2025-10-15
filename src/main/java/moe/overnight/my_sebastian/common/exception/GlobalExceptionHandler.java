package moe.overnight.my_sebastian.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import moe.overnight.my_sebastian.common.dto.response.ApiErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(ApiException.class)
    public ProblemDetail handleApiException(ApiException ex, Locale locale) {
        ErrorCode ec = ex.getErrorCode();
        String title = resolveMessage(ec, ex.getMessageArgs(), locale);
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(ec.httpStatus().value()),
                title
        );
        pd.setTitle(ec.code()); // code를 title 또는 custom property로
        pd.setType(URI.create("https://example.com/errors/" + ec.code()));
        pd.setProperty("code", ec.code());
        Map<String, Object> details = ex.getDetails();
        if (details != null && !details.isEmpty()) {
            pd.setProperty("details", details);
        }
        return pd;
    }

    private String resolveMessage(ErrorCode ec, Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(ec.code(), args, ec.defaultMessage(), locale);
        } catch (Exception ignored) {
            return ec.defaultMessage();
        }
    }
}