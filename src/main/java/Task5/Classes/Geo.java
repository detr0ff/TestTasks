package Task5.Classes;

import lombok.Data;

@Data
public class Geo {
    double lat;
    double lng;

    public Geo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
