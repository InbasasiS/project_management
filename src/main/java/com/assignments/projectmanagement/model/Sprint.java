package com.assignments.projectmanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

// The Sprint has many Tasks
// The Sprint belongs to Board

@Entity
@Data
@Table(name = "sprints")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "starts_at")
    Date startsAt;

    @Column(name = "ends_at")
    Date endsAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_id", nullable = false)
    @JsonIgnore
    Board board;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Task> tasks;
}
