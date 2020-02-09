package poc.graalvm.micronaut.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class ResponseDTO<T> {
    private String message;
    private T result;

    public ResponseDTO(String message, T result){
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

}
