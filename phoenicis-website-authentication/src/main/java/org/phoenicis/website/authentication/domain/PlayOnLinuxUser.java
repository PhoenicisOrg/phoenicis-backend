package org.phoenicis.website.authentication.domain;

import javax.persistence.*;

@Entity
@Table(name = "playonlinux_users")
public class PlayOnLinuxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10)")
    private Long id;

    @Column(name = "siteweb")
    private String siteweb;

    @Column(name = "afficher_signature")
    private Integer showSignature;

    @Column(name = "afficher_email")
    private Integer showEmail;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer type; // FIXME

    @Column
    private String avatar;

    @Column(name = "prenom")
    private String firstName;

    @Column(name = "nom")
    private String lastName;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
