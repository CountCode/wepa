package wad.domain;

import java.util.Objects;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Apartment extends AbstractPersistable<Long> { //implements Comparable<Apartment> {

    private String name;

    public Apartment() {
    }

    public Apartment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }
    */
/*
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Apartment other = (Apartment) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    */
/*
    @Override
    public int compareTo(Apartment o) {
        return this.name.compareTo(o.name);
    }
*/
    
}