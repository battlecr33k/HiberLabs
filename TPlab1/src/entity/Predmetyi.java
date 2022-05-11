/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author 19553
 */

@Entity
@Table(name="predmetyi"
    ,catalog="gr"
    , uniqueConstraints = @UniqueConstraint(columnNames="Nazvanie") 
)
public class Predmetyi {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="Id", unique=true, nullable=false)
    private long id;
    
    @Column(name="Nazvanie", unique=true, nullable=false, length=50)
    private String nazvanie;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ShifrGruppyi", nullable=false)
    private Gruppyi gruppyi;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "StatusDate", length = 10)
    private Date statusDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public Gruppyi getGruppyi() {
        return gruppyi;
    }

    public void setGruppyi(Gruppyi gruppyi) {
        this.gruppyi = gruppyi;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}
