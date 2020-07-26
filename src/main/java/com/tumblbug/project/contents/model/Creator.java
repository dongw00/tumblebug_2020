package com.tumblbug.project.contents.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long creatorId;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Builder
    public Creator(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
