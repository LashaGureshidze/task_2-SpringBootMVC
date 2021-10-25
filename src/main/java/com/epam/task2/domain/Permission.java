package com.epam.task2.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSIONS")
@Data
public class Permission {

    @Id
    @Column(name = "PERMISSION_ID")
    private Long permissionId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;

}
