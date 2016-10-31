package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    private String ico;

    private String name;

    @OneToOne(mappedBy = "company")
    private Submitter submitter;

    @OneToMany(mappedBy = "company")
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "company")
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "company")
    private List<Subsupplier> subsuppliers;

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Subsupplier> getSubsuppliers() {
        return subsuppliers;
    }

    public void setSubsuppliers(List<Subsupplier> subsuppliers) {
        this.subsuppliers = subsuppliers;
    }

    public List<Candidate> getCandidates() {

        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Submitter getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }
}
