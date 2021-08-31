package com.infosat.appRecruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "postule_infos")
@Data
public class PostuleInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String cv;
    private String email;
    private String linkdenUrl;
    private String githubUrl;
    private String societe;
    private String domaineDetude;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User condidatPostule;


   /* public long getId() {
        return id;
    }

    public String getCv() {
        return cv;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkdenUrl() {
        return linkdenUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getSociete() {
        return societe;
    }

    public String getDomaineDetude() {
        return domaineDetude;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLinkdenUrl(String linkdenUrl) {
        this.linkdenUrl = linkdenUrl;
    }

    public void setGithubUrl(String githunUrl) {
        this.githubUrl = githunUrl;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public void setDomaineDetude(String domaineDetude) {
        this.domaineDetude = domaineDetude;
    }

    public User getCondidatPostulé() {
        return condidatPostulé;
    }

    public void setCondidatPostule(Optional<User> condidatPostule) {
        this.condidatPostule = condidatPostule;
    }*/

    public PostuleInfos() {
    }

    public PostuleInfos(String cv, String email, String linkdenUrl, String githubUrl, String societe, String domaineDetude, User condidatPostule) {

        this.cv = cv;
        this.email = email;
        this.linkdenUrl = linkdenUrl;
        this.githubUrl = githubUrl;
        this.societe = societe;
        this.domaineDetude = domaineDetude;
        this.condidatPostule = condidatPostule;

    }

    @Override
    public String toString() {
        return "PostuleInfos{" +
                "id=" + id +
                ", cv='" + cv + '\'' +
                ", email='" + email + '\'' +
                ", linkdenUrl='" + linkdenUrl + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", societe='" + societe + '\'' +
                ", domaineDetude='" + domaineDetude + '\'' +
                ", condidatPostule=" + condidatPostule +
                '}';
    }
}
