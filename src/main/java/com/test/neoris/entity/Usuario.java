package com.test.neoris.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
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
    
    @Column(name = "token", length = 200)
    private String token;
    
    @Column(name = "isactive", length = 200)
    private boolean isactive;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> phones;
    
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
    
    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
}
