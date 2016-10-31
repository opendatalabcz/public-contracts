package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Submitter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submitter_id")
    private Long submitterId;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "submitter")
    private List<Contract> contracts;

    public Long getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Long submitterId) {
        this.submitterId = submitterId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
