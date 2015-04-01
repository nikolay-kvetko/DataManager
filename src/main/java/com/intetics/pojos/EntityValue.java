package com.intetics.pojos;

import com.intetics.pojos.values.DateValues;
import com.intetics.pojos.values.NumberValues;
import com.intetics.pojos.values.StringValues;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Кузнец on 31.03.2015.
 */

@Entity
public class EntityValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EntityValueId;

    @Column
    @Type(type = "date")
    private Date createDate;

    @Column
    @Type(type = "date")
    private Date lastModificationDate;

    @ManyToOne
    @JoinColumn(name = "entityNameId")
    private EntityName entityName;

    //Values of fields
    @OneToMany(mappedBy = "entityValue", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<StringValues> stringValues = new HashSet<StringValues>();

    @OneToMany(mappedBy = "entityValue", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<NumberValues> numberValues = new HashSet<NumberValues>();

    @OneToMany(mappedBy = "entityValue", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<DateValues> dateValues = new HashSet<DateValues>();

    public Long getEntityValueId() {
        return EntityValueId;
    }

    public void setEntityValueId(Long entityValueId) {
        EntityValueId = entityValueId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public EntityName getEntityName() {
        return entityName;
    }

    public void setEntityName(EntityName entityName) {
        this.entityName = entityName;
    }

    public Set<StringValues> getStringValues() {
        return stringValues;
    }

    public void setStringValues(Set<StringValues> stringValues) {
        this.stringValues = stringValues;
    }

    public Set<NumberValues> getNumberValues() {
        return numberValues;
    }

    public void setNumberValues(Set<NumberValues> numberValues) {
        this.numberValues = numberValues;
    }

    public Set<DateValues> getDateValues() {
        return dateValues;
    }

    public void setDateValues(Set<DateValues> dateValues) {
        this.dateValues = dateValues;
    }
}
