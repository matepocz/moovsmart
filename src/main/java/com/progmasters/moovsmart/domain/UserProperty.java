package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.dto.CreateUserCommand;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String mail;
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<Property> properties = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles")
    @Column(name = "user_role")
    private List<RoleType> roleTypes = new ArrayList<>();


    public UserProperty() {
    }

    public UserProperty(CreateUserCommand command) {
        this.userName = command.getUserName();
        this.mail = command.getMail();
        this.isActive = false;
        this.roleTypes = makeRoles(command.getRole());
    }

    private List<RoleType> makeRoles(List<String> useRole) {
        List<RoleType> roles = new ArrayList<>();
        roles.add(RoleType.valueOf("ROLE_USER"));
//        for (String role : useRole) {
//            roles.add(RoleType.valueOf(role));
//        }
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<RoleType> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(List<RoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }
}