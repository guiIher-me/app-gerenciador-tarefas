package br.com.tarefas.gerenciador.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "tasks_users", 
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"task_id","user_id"}, 
                    name = "unique_user_task"
                ), 
	            joinColumns = @JoinColumn(name = "user_id", 
                    referencedColumnName = "id", 
                    table = "users", 
                    unique = false
                ),
                inverseJoinColumns = @JoinColumn (
                    name = "task_id", 
                    referencedColumnName = "id", 
                    table = "tasks", 
                    unique = false
                )
            )    
    private List<Task> tasks;

    private UserRole role;

    public User() { }

    public User(String email, String encryptedPassword, UserRole role) {
        this.email = email;
        this.password = encryptedPassword;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN"); 
        SimpleGrantedAuthority USER = new SimpleGrantedAuthority("ROLE_USER");

        if (this.role == UserRole.ADMIN)
            return List.of(ADMIN, USER);
        
        return List.of(USER);
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getEmail() + " " + this.getPassword();
    }

}
