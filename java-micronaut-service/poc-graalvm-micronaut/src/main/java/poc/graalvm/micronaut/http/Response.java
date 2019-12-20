package poc.graalvm.micronaut.http;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class Response<T> {

    private String message;
    private List<T> result;

    public Response(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
