package eu.profinit.publiccontracts.dto;


import java.util.List;

public class SupplierDto extends CompanyDto {

    private Double price;
    private List<SubSupplierDto> subSupplierDtos;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<SubSupplierDto> getSubSupplierDtos() {
        return subSupplierDtos;
    }

    public void setSubSupplierDtos(List<SubSupplierDto> subSupplierDtos) {
        this.subSupplierDtos = subSupplierDtos;
    }
}
