package pocgraalvm.api.rest.model;

public class Comment {

    private String name;
    private String email;
    private String movieId;
    private String text;
    private Long date;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", movieId='" + movieId + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
