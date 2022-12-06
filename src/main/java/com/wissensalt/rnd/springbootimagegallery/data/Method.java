package com.wissensalt.rnd.springbootimagegallery.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created on 5/19/21.
 *
 * Pritesh
 */
@Getter
@Setter
@Entity
@Table(name ="method")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_method")
    private Integer id_method;

    @Column(name = "description")
    private String description;        
}
