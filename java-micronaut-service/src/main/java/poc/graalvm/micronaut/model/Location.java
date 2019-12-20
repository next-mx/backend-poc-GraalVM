package poc.graalvm.micronaut.model;

public class Location {

    private Address address;
    private Geo geo;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                ", geo=" + geo +
                '}';
    }
}
