package model;

import javax.persistence.*;

@Entity
public class Subsupplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subsupplier_id")
    private Long subsupplierId;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getSubsupplierId() {
        return subsupplierId;
    }

    public void setSubsupplierId(Long subsupplierId) {
        this.subsupplierId = subsupplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
