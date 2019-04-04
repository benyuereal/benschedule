package com.moji.schedule.exception;

import com.moji.schedule.enums.ScheduleExceptionEnum;
import lombok.Data;

@Data
public class ExpressionException extends Exception{
    private int value;
    private String message;
    public ExpressionException(){
        super();
    }

    public ExpressionException(int value,String message){
        super(message);
        this.value=value;
    }
    public ExpressionException(ScheduleExceptionEnum exception){
        super(exception.name());
        this.value=1010;
    }
}
