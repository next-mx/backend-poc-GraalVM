package poc.graalvm.micronaut.model;

public class Theater {

    private String type;
    private Location location;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "type='" + type + '\'' +
                ", location=" + location +
                '}';
    }
}
