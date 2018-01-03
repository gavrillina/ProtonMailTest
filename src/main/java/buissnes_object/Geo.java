package buissnes_object;

import java.util.HashMap;
import java.util.Map;

public class Geo {
    private String lat;
    private String lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getLat() {

        return lat;
    }

    public String getLng() {
        return lng;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
