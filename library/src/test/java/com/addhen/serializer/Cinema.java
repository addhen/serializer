package com.addhen.serializer;

import java.io.Serializable;

/**
 * A model that will be used for testing.
 *
 * @author Henry Addo
 */
public class Cinema implements Serializable {

    /**
     * The cinema name
     */
    public String name;

    /**
     * The locationName name
     */
    public String locationName;

    /**
     * The location latitude and longitude
     */
    public LatLng location;

    @Override
    public String toString() {
        return "Cinema{"
                + "name='" + name + '\''
                + ", locationName='" + locationName + '\''
                + ", location=" + location
                + '}';
    }

    public static class LatLng {

        /** Location's latitude */
        public Double latitude;

        /** Location's longitude */
        public Double longitude;

        public LatLng(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return String.format("%s,%s", latitude, longitude);
        }
    }
}
