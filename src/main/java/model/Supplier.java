package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    private Double price;


    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;


    @OneToMany(mappedBy = "supplier")
    private List<Subsupplier> subsuppliers;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Subsupplier> getSubsuppliers() {
        return subsuppliers;
    }

    public void setSubsuppliers(List<Subsupplier> subsuppliers) {
        this.subsuppliers = subsuppliers;
    }
}
