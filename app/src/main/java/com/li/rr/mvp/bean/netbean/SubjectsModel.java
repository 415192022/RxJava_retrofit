package com.li.rr.mvp.bean.netbean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/29.
 */
public class SubjectsModel {
    private String id;
    private String alt;
    private String year;
    private String title;
    private String original_title;
    private List<String> genres;
    private List<Casts> casts;
    private List<Casts> directors;
    private Avatars images;

    @Override
    public String toString() {
        return "SubjectsModel{" +
                "id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                ", year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                ", images=" + images +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public void setDirectors(List<Casts> directors) {
        this.directors = directors;
    }

    public void setImages(Avatars images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getAlt() {
        return alt;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public List<Casts> getDirectors() {
        return directors;
    }

    public Avatars getImages() {
        return images;
    }

    public class Casts{
        private String id;
        private String name;
        private String alt;
        private Avatars avatars;

        @Override
        public String toString() {
            return "Casts{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", alt='" + alt + '\'' +
                    ", avatars=" + avatars +
                    '}';
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setAvatars(Avatars avatars) {
            this.avatars = avatars;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAlt() {
            return alt;
        }

        public Avatars getAvatars() {
            return avatars;
        }
    }

    public class Avatars{
        private String samll;
        private String large;
        private String medium;

        public void setSamll(String samll) {
            this.samll = samll;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getSamll() {
            return samll;
        }

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }
    }
}
