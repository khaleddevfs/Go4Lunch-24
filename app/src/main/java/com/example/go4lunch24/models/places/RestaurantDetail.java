package com.example.go4lunch24.models.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetail {
    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestaurantDetail() {    }

    /**
     *
     * @param result
     * @param htmlAttributions
     * @param status
     */
    public RestaurantDetail(List<Object> htmlAttributions, Result result, String status) {
        super();
        this.htmlAttributions = htmlAttributions;
        this.result = result;
        this.status = status;
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public RestaurantDetail withHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public RestaurantDetail withResult(Result result) {
        this.result = result;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestaurantDetail withStatus(String status) {
        this.status = status;
        return this;
    }


    public static class AddressComponent {

        @SerializedName("long_name")
        @Expose
        private String longName;
        @SerializedName("short_name")
        @Expose
        private String shortName;
        @SerializedName("types")
        @Expose
        private List<String> types = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public AddressComponent() {
        }

        /**
         *
         * @param types
         * @param shortName
         * @param longName
         */
        public AddressComponent(String longName, String shortName, List<String> types) {
            super();
            this.longName = longName;
            this.shortName = shortName;
            this.types = types;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public AddressComponent withLongName(String longName) {
            this.longName = longName;
            return this;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public AddressComponent withShortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public AddressComponent withTypes(List<String> types) {
            this.types = types;
            return this;
        }

    }

    public static class Geometry {

        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("viewport")
        @Expose
        private Viewport viewport;

        /**
         * No args constructor for use in serialization
         *
         */
        public Geometry() {}

        /**
         *
         * @param viewport
         * @param location
         */
        public Geometry(Location location, Viewport viewport) {
            super();
            this.location = location;
            this.viewport = viewport;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Geometry withLocation(Location location) {
            this.location = location;
            return this;
        }

        public Viewport getViewport() {
            return viewport;
        }

        public void setViewport(Viewport viewport) {
            this.viewport = viewport;
        }

        public Geometry withViewport(Viewport viewport) {
            this.viewport = viewport;
            return this;
        }
    }

    public static class Location {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public Location() {}

        /**
         *
         * @param lng
         * @param lat
         */
        public Location(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Location withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Location withLng(Double lng) {
            this.lng = lng;
            return this;
        }
    }

    public static class Northeast {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public Northeast() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public Northeast(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Northeast withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Northeast withLng(Double lng) {
            this.lng = lng;
            return this;
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
         *
         * @return
         * The height
         */
        public Integer getHeight() {
            return height;
        }

        /**
         *
         * @param height
         * The height
         */
        public void setHeight(Integer height) {
            this.height = height;
        }

        /**
         *
         * @return
         * The htmlAttributions
         */
        public List<String> getHtmlAttributions() {
            return htmlAttributions;
        }

        /**
         *
         * @param htmlAttributions
         * The html_attributions
         */
        public void setHtmlAttributions(List<String> htmlAttributions) {
            this.htmlAttributions = htmlAttributions;
        }

        /**
         *
         * @return
         * The photoReference
         */
        public String getPhotoReference() {
            return photoReference;
        }

        /**
         *
         * @param photoReference
         * The photo_reference
         */
        public void setPhotoReference(String photoReference) {
            this.photoReference = photoReference;
        }

        /**
         *
         * @return
         * The width
         */
        public Integer getWidth() {
            return width;
        }

        /**
         *
         * @param width
         * The width
         */
        public void setWidth(Integer width) {
            this.width = width;
        }

    }

    public static class Result {

        @SerializedName("address_components")
        @Expose
        private List<AddressComponent> addressComponents = null;
        @SerializedName("the_address")
        @Expose
        private String theAddress;
        @SerializedName("formatted_address")
        @Expose
        private String formattedAddress;
        @SerializedName("formatted_phone_number")
        @Expose
        private String formattedPhoneNumber;
        @SerializedName("geometry")
        @Expose
        private Geometry geometry;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("international_phone_number")
        @Expose
        private String internationalPhoneNumber;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("opening_hours")
        @Expose
        private OpeningHours openingHours;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("reference")
        @Expose
        private String reference;
        @SerializedName("reviews")
        @Expose
        private List<Review> reviews = null;
        @SerializedName("types")
        @Expose
        private List<String> types = null;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("utc_offset")
        @Expose
        private Integer utcOffset;
        @SerializedName("vicinity")
        @Expose
        private String vicinity;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("photos")
        @Expose
        private List<Photo> photos = new ArrayList<>();

        /**
         * No args constructor for use in serialization
         *
         */
        public Result() {
        }
        public Result(List<AddressComponent> addressComponents, String theAddress, String formattedAddress,
                      String formattedPhoneNumber, Geometry geometry, String icon, String id, String internationalPhoneNumber,
                      String name, String placeId, Double rating, String reference, List<Review> reviews, List<String> types,
                      String url, Integer utcOffset, String vicinity, String website, List<Photo> photos) {
            super();
            this.addressComponents = addressComponents;
            this.theAddress = theAddress;
            this.formattedAddress = formattedAddress;
            this.formattedPhoneNumber = formattedPhoneNumber;
            this.geometry = geometry;
            this.icon = icon;
            this.id = id;
            this.internationalPhoneNumber = internationalPhoneNumber;
            this.name = name;
            this.placeId = placeId;
            this.rating = rating;
            this.reference = reference;
            this.reviews = reviews;
            this.types = types;
            this.url = url;
            this.utcOffset = utcOffset;
            this.vicinity = vicinity;
            this.website = website;
            this.photos = photos;
        }


        public List<AddressComponent> getAddressComponents() {
            return addressComponents;
        }

        public void setAddressComponents(List<AddressComponent> addressComponents) {
            this.addressComponents = addressComponents;
        }

        public Result withAddressComponents(List<AddressComponent> addressComponents) {
            this.addressComponents = addressComponents;
            return this;
        }

        public String getTheAddress() {
            return theAddress;
        }

        public void setTheAddress(String theAddress) {
            this.theAddress = theAddress;
        }

        public Result withTheAddress(String theAddress) {
            this.theAddress = theAddress;
            return this;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public Result withFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
            return this;
        }

        public String getFormattedPhoneNumber() {
            return formattedPhoneNumber;
        }

        public void setFormattedPhoneNumber(String formattedPhoneNumber) {
            this.formattedPhoneNumber = formattedPhoneNumber;
        }

        public Result withFormattedPhoneNumber(String formattedPhoneNumber) {
            this.formattedPhoneNumber = formattedPhoneNumber;
            return this;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public Result withGeometry(Geometry geometry) {
            this.geometry = geometry;
            return this;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Result withIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Result withId(String id) {
            this.id = id;
            return this;
        }

        public String getInternationalPhoneNumber() {
            return internationalPhoneNumber;
        }

        public void setInternationalPhoneNumber(String internationalPhoneNumber) {
            this.internationalPhoneNumber = internationalPhoneNumber;
        }

        public Result withInternationalPhoneNumber(String internationalPhoneNumber) {
            this.internationalPhoneNumber = internationalPhoneNumber;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Result withName(String name) {
            this.name = name;
            return this;
        }

        public OpeningHours getOpeningHours() {
            return openingHours;
        }

        public void setOpeningHours(OpeningHours openingHours) {
            this.openingHours = openingHours;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public Result withPlaceId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Result withRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public Result withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }

        public Result withReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public Result withTypes(List<String> types) {
            this.types = types;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Result withUrl(String url) {
            this.url = url;
            return this;
        }

        public Integer getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(Integer utcOffset) {
            this.utcOffset = utcOffset;
        }

        public Result withUtcOffset(Integer utcOffset) {
            this.utcOffset = utcOffset;
            return this;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public Result withVicinity(String vicinity) {
            this.vicinity = vicinity;
            return this;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public Result withWebsite(String website) {
            this.website = website;
            return this;
        }

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

    }

    public static class Review {

        @SerializedName("author_name")
        @Expose
        private String authorName;
        @SerializedName("author_url")
        @Expose
        private String authorUrl;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("profile_photo_url")
        @Expose
        private String profilePhotoUrl;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("relative_time_description")
        @Expose
        private String relativeTimeDescription;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("time")
        @Expose
        private Integer time;

        /**
         * No args constructor for use in serialization
         *
         */
        public Review() {
        }

        /**
         *
         * @param authorName
         * @param authorUrl
         * @param rating
         * @param relativeTimeDescription
         * @param language
         * @param text
         * @param time
         * @param profilePhotoUrl
         */
        public Review(String authorName, String authorUrl, String language, String profilePhotoUrl, Integer rating, String relativeTimeDescription, String text, Integer time) {
            super();
            this.authorName = authorName;
            this.authorUrl = authorUrl;
            this.language = language;
            this.profilePhotoUrl = profilePhotoUrl;
            this.rating = rating;
            this.relativeTimeDescription = relativeTimeDescription;
            this.text = text;
            this.time = time;
        }


        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public Review withAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public String getAuthorUrl() {
            return authorUrl;
        }

        public void setAuthorUrl(String authorUrl) {
            this.authorUrl = authorUrl;
        }

        public Review withAuthorUrl(String authorUrl) {
            this.authorUrl = authorUrl;
            return this;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Review withLanguage(String language) {
            this.language = language;
            return this;
        }

        public String getProfilePhotoUrl() {
            return profilePhotoUrl;
        }

        public void setProfilePhotoUrl(String profilePhotoUrl) {
            this.profilePhotoUrl = profilePhotoUrl;
        }

        public Review withProfilePhotoUrl(String profilePhotoUrl) {
            this.profilePhotoUrl = profilePhotoUrl;
            return this;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Review withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public String getRelativeTimeDescription() {
            return relativeTimeDescription;
        }

        public void setRelativeTimeDescription(String relativeTimeDescription) {
            this.relativeTimeDescription = relativeTimeDescription;
        }

        public Review withRelativeTimeDescription(String relativeTimeDescription) {
            this.relativeTimeDescription = relativeTimeDescription;
            return this;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Review withText(String text) {
            this.text = text;
            return this;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

        public Review withTime(Integer time) {
            this.time = time;
            return this;
        }

    }

    public static class Southwest {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public Southwest() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public Southwest(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Southwest withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Southwest withLng(Double lng) {
            this.lng = lng;
            return this;
        }

    }

    public static class Viewport {

        @SerializedName("northeast")
        @Expose
        private Northeast northeast;
        @SerializedName("southwest")
        @Expose
        private Southwest southwest;

        /**
         * No args constructor for use in serialization
         *
         */
        public Viewport() {
        }

        /**
         *
         * @param southwest
         * @param northeast
         */
        public Viewport(Northeast northeast, Southwest southwest) {
            super();
            this.northeast = northeast;
            this.southwest = southwest;
        }

        public Northeast getNortheast() {
            return northeast;
        }

        public void setNortheast(Northeast northeast) {
            this.northeast = northeast;
        }

        public Viewport withNortheast(Northeast northeast) {
            this.northeast = northeast;
            return this;
        }

        public Southwest getSouthwest() {
            return southwest;
        }

        public void setSouthwest(Southwest southwest) {
            this.southwest = southwest;
        }

        public Viewport withSouthwest(Southwest southwest) {
            this.southwest = southwest;
            return this;
        }

    }

    public static class Close {

        @SerializedName("day")
        @Expose
        private Integer day;
        @SerializedName("time")
        @Expose
        private String time;

        public Close() {}

        public Close(Integer pDay, String pTime) {
            day = pDay;
            time = pTime;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }

    public static class Open {

        @SerializedName("day")
        @Expose
        private Integer day;
        @SerializedName("time")
        @Expose
        private String time;

        public Open() {}

        public Open(Integer day, String time) {
            day = day;
            time = time;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }

    public static class OpeningHours {

        @SerializedName("open_now")
        @Expose
        private Boolean openNow;
        @SerializedName("periods")
        @Expose
        private List<Period> periods = null;
        @SerializedName("weekday_text")
        @Expose
        private List<String> weekdayText = null;

        public OpeningHours(Boolean openNow, List<Period> periods, List<String> weekdayText) {
            openNow = openNow;
            periods = periods;
            weekdayText = weekdayText;
        }

        public Boolean getOpenNow() {
            return openNow;
        }

        public void setOpenNow(Boolean openNow) {
            this.openNow = openNow;
        }

        public List<Period> getPeriods() {
            return periods;
        }

        public void setPeriods(List<Period> periods) {
            this.periods = periods;
        }

        public List<String> getWeekdayText() {
            return weekdayText;
        }

        public void setWeekdayText(List<String> weekdayText) {
            this.weekdayText = weekdayText;
        }

    }

    public static class Period {

        @SerializedName("close")
        @Expose
        private Close close;
        @SerializedName("open")
        @Expose
        private Open open;

        public Period() {}

        public Period(Close close, Open open) {}

        public Close getClose() {
            return close;
        }

        public void setClose(Close close) {
            this.close = close;
        }

        public Open getOpen() {
            return open;
        }

        public void setOpen(Open open) {
            this.open = open;
        }

    }
}