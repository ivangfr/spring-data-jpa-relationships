package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "teamDetail")
@EqualsAndHashCode(exclude = "teamDetail")
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TeamDetail teamDetail;

    @Column(nullable = false)
    private String name;

    public void addTeamDetail(TeamDetail teamDetail) {
        this.teamDetail = teamDetail;
        teamDetail.setTeam(this);
    }

    public void removeTeamDetail() {
        this.teamDetail.setTeam(null);
        this.teamDetail = null;
    }
}
