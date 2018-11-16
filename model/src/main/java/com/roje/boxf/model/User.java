package com.roje.boxf.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@DynamicUpdate
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1326670144034313266L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(4000)")
    private String nickname;

    private String ipConfig;

    @Column(columnDefinition = "varchar(4000)")
    private String headImg;

    private int sex = 0;

    private String unionId;

    private long roomCard = 0;

    private long gold = 0;

    private int vip = 0;

    private int agentId = 0;

    private Date regDate;

    private Date lastLoginDate;

    private int accountStatus = 0;
}
