package pocgraalvm.api.rest.http;

public class Response<T> {

    private String message;
    private T result;

    public Response(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
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
