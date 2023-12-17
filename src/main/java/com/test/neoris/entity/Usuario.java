package com.test.neoris.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "id", updatable = false, unique = true, length = 100)
    private String id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "email", length = 150, unique = true)
    private String email;
    
    @Column(name = "password", length = 200)
    private String password;
    
    @Column(name = "token", length = 255)
    private String token;
    
    @Column(name = "isactive", length = 200)
    private boolean isactive;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> phones;
    
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonProperty(value = "created")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonProperty(value = "modified")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
    private Date updatedAt;
    
    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(value = "lastlogin")
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
    private Date lastLogin;
}
