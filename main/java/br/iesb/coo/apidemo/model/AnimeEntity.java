package br.iesb.coo.apidemo.model;

import java.util.Date;
import java.util.List;

public class AnimeEntity {
     private long animeId;
     private String name;
     private List<String> genres;
     private int episodes;
     private boolean stats;
     private String author;
     private String studio;
     private String season;
     private String classification;
     private int startDay;
     private int startMonth;
     private int startYear;
     private Date creationDate;
     private Date updateDate;

     public long getAnimeId() {
          return animeId;
     }

     public void setAnimeId(long animeId) {
          this.animeId = animeId;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public List<String> getGenres() {
          return genres;
     }

     public void setGenres(List<String> genres) {
          this.genres = genres;
     }

     public int getEpisodes() {
          return episodes;
     }

     public void setEpisodes(int episodes) {
          this.episodes = episodes;
     }

     public boolean isStats() {
          return stats;
     }

     public void setStats(boolean stats) {
          this.stats = stats;
     }

     public String getAuthor() {
          return author;
     }

     public void setAuthor(String author) {
          this.author = author;
     }

     public String getStudio() {
          return studio;
     }

     public void setStudio(String studio) {
          this.studio = studio;
     }

     public String getSeason() {
          return season;
     }

     public void setSeason(String season) {
          this.season = season;
     }

     public String getClassification() {
          return classification;
     }

     public void setClassification(String classification) {
          this.classification = classification;
     }

     public int getStartDay() {
          return startDay;
     }

     public void setStartDay(int startDay) {
          this.startDay = startDay;
     }

     public int getStartMonth() {
          return startMonth;
     }

     public void setStartMonth(int startMonth) {
          this.startMonth = startMonth;
     }

     public int getStartYear() {
          return startYear;
     }

     public void setStartYear(int startYear) {
          this.startYear = startYear;
     }

     public Date getCreationDate() {
          return creationDate;
     }

     public void setCreationDate(Date creationDate) {
          this.creationDate = creationDate;
     }

     public Date getUpdateDate() {
          return updateDate;
     }

     public void setUpdateDate(Date updateDate) {
          this.updateDate = updateDate;
     }
}
