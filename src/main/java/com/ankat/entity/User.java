package com.ankat.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "USER")
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USR_ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usrId;

    @Column(name = "USR_FULLNAME", nullable = false)
    private String usrFullName;

    @Column(name = "usrAge")
    private Integer usrAge = 0;

    @Column(name = "USR_USERNAME", unique = true, nullable = false)
    private String usrUsername;

    @Column(name = "USR_PASSWORD", nullable = false)
    private String usrPassword;

    @Column(name = "USR_ROLE", nullable = false)
    private String usrRole;

    @Column(name = "USR_ACTIVE", nullable = false)
    private Boolean usrActive = Boolean.TRUE;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
