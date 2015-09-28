
package wad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Person extends AbstractPersistable<Long> {

    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    @Column(unique = true)    
    private String username;
    @NotBlank
    private String password;

    public Person() {
    }

    public Person(String name, String username, String password) {
        this.name = name;
        this.username=username;
        this.password=password;
    }

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

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    
}