package wad.domain;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Person extends AbstractPersistable<Long> {
 
    @Column(unique = true)
    @NotNull
    @NotBlank
    @JsonProperty
    private String name;
 
    @Column(unique = true)
    @NotNull
    @NotBlank
    @JsonProperty
    private String username;
 
    @NotNull
    @NotBlank
    @JsonProperty
    private String password;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    @JsonIgnore
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
