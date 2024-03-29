package com.example.go4lunch24.models.places;

import com.example.go4lunch24.models.Location;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = new ArrayList<>();
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();
    @SerializedName("status")
    @Expose
    private String status;

    public Restaurant() {
    }

    /**
     * @return The htmlAttributions
     */
    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    /**
     * @param htmlAttributions The html_attributions
     */
    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public static class Geometry {

        @SerializedName("location")
        @Expose
        private Location location;

        /**
         * @return The location
         */
        public Location getLocation() {
            return location;
        }

        /**
         * @param location The location
         */
        public void setLocation(Location location) {
            this.location = location;
        }
    }


    public static class OpeningHours {

        @SerializedName("open_now")
        @Expose
        private Boolean openNow;
        @SerializedName("weekday_text")
        @Expose
        private List<Object> weekdayText = new ArrayList<>();

        /**
         * @return The openNow
         */
        public Boolean getOpenNow() {
            return openNow;
        }

        /**
         * @param openNow The open_now
         */
        public void setOpenNow(Boolean openNow) {
            this.openNow = openNow;
        }

        /**
         * @return The weekdayText
         */
        public List<Object> getWeekdayText() {
            return weekdayText;
        }

        /**
         * @param weekdayText The weekday_text
         */
        public void setWeekdayText(List<Object> weekdayText) {
            this.weekdayText = weekdayText;
        }
    }

    public static class Photo {

        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("html_attributions")
        @Expose
        private List<String> htmlAttributions = new ArrayList<>();
        @SerializedName("photo_reference")
        @Expose
        private String photoReference;
        @SerializedName("width")
        @Expose
        private Integer width;

        /**
         * @return The height
         */
        public Integer getHeight() {
            return height;
        }

        /**
         * @param height The height
         */
        public void setHeight(Integer height) {
            this.height = height;
        }

        /**
         * @return The htmlAttributions
         */
        public List<String> getHtmlAttributions() {
            return htmlAttributions;
        }

        /**
         * @param htmlAttributions The html_attributions
         */
        public void setHtmlAttributions(List<String> htmlAttributions) {
            this.htmlAttributions = htmlAttributions;
        }

        /**
         * @return The photoReference
         */
        public String getPhotoReference() {
            return photoReference;
        }

        /**
         * @param photoReference The photo_reference
         */
        public void setPhotoReference(String photoReference) {
            this.photoReference = photoReference;
        }

        /**
         * @return The width
         */
        public Integer getWidth() {
            return width;
        }

        /**
         * @param width The width
         */
        public void setWidth(Integer width) {
            this.width = width;
        }

    }

    public static class Result {
        @SerializedName("geometry")
        @Expose
        private Geometry geometry;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("opening_hours")
        @Expose
        private OpeningHours openingHours;
        @SerializedName("photos")
        @Expose
        private List<Photo> photos = new ArrayList<>();
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("rating")
        @Expose
        private double rating;
        @SerializedName("reference")
        @Expose
        private String reference;
        @SerializedName("scope")
        @Expose
        private String scope;
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<>();
        @SerializedName("vicinity")
        @Expose
        private String vicinity;
        @SerializedName("price_level")
        @Expose
        private Integer priceLevel;

        /**
         * @return The geometry
         */
        public Geometry getGeometry() {
            return geometry;
        }

        /**
         * @param geometry The geometry
         */
        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        /**
         * @return The icon
         */
        public String getIcon() {
            return icon;
        }

        /**
         * @param icon The icon
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }

        /**
         * @return The id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The openingHours
         */
        public OpeningHours getOpeningHours() {
            return openingHours;
        }

        /**
         * @param openingHours The opening_hours
         */
        public void setOpeningHours(OpeningHours openingHours) {
            this.openingHours = openingHours;
        }

        /**
         * @return The photos
         */
        public List<Photo> getPhotos() {
            return photos;
        }

        /**
         * @param photos The photos
         */
        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        /**
         * @return The placeId
         */
        public String getPlaceId() {
            return placeId;
        }

        /**
         * @param placeId The place_id
         */
        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        /**
         * @return The rating
         */
        public Double getRating() {
            return rating;
        }

        /**
         * @param rating The rating
         */
        public void setRating(Double rating) {
            this.rating = rating;
        }

        /**
         * @return The reference
         */
        public String getReference() {
            return reference;
        }

        /**
         * @param reference The reference
         */
        public void setReference(String reference) {
            this.reference = reference;
        }

        /**
         * @return The scope
         */
        public String getScope() {
            return scope;
        }

        /**
         * @param scope The scope
         */
        public void setScope(String scope) {
            this.scope = scope;
        }

        /**
         * @return The types
         */
        public List<String> getTypes() {
            return types;
        }

        /**
         * @param types The types
         */
        public void setTypes(List<String> types) {
            this.types = types;
        }

        /**
         * @return The vicinity
         */
        public String getVicinity() {
            return vicinity;
        }

        /**
         * @param vicinity The vicinity
         */
        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        /**
         * @return The priceLevel
         */
        public Integer getPriceLevel() {
            return priceLevel;
        }

        /**
         * @param priceLevel The price_level
         */
        public void setPriceLevel(Integer priceLevel) {
            this.priceLevel = priceLevel;
        }

    }
}
