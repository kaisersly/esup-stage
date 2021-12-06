package org.esup_portail.esup_stage.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PeriodeInterruptionStage")
public class PeriodeInterruptionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateDebutInterruption;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateFinInterruption;

    @ManyToOne
    @JoinColumn(name = "idConvention", nullable = false)
    private Convention convention;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebutInterruption() {
        return dateDebutInterruption;
    }

    public void setDateDebutInterruption(Date dateDebutInterruption) {
        this.dateDebutInterruption = dateDebutInterruption;
    }

    public Date getDateFinInterruption() {
        return dateFinInterruption;
    }

    public void setDateFinInterruption(Date dateFinInterruption) {
        this.dateFinInterruption = dateFinInterruption;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }
}
