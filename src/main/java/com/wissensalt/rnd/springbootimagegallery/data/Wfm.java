package com.wissensalt.rnd.springbootimagegallery.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created on 5/17/21.
 *
 * Pritesh
 */
@Getter
@Setter
@Entity
@Table(name ="wfm")
public class Wfm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_method")
    private Integer id_method;

    @Column(name = "resolution")
    private Integer resolution;


    @Column(name = "intervals")
    private Integer intervals;

    @Column(name = "extent")
    private Integer extent;

    @Column(name = "created_time")
    private String created_time;
}
