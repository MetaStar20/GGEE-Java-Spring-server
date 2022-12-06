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
@Table(name ="ggee")
public class Ggee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_request")
    private Integer id_request;

    @Column(name = "id_method")
    private Integer id_method;


    @Column(name = "img_path")
    private String img_path;

    @Column(name = "stopped")
    private Integer stopped;

    @Column(name = "created_time")
    private String created_time;        
}
