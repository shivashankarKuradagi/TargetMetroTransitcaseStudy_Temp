package com.target.metrotransit.casestudy.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class TargetResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler( ResourseNotFoundException.class )
    public final ResponseEntity<Object> handleResourseNotFoundException( ResourseNotFoundException ex,
                                                                         WebRequest request ) throws Exception
    {
        ExceptionResponse resourseNotFoundException = new ExceptionResponse( new Date(), ex.getMessage(), "" );

        return new ResponseEntity<>( resourseNotFoundException, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( Exception.class )
    public final ResponseEntity<Object> handleAllException( Exception ex,
                                                            WebRequest request ) throws Exception
    {

        ExceptionResponse resourseNotFoundException = new ExceptionResponse( new Date(), ex.getMessage(), "" );

        return new ResponseEntity<>( resourseNotFoundException, HttpStatus.INTERNAL_SERVER_ERROR );

    }

}
