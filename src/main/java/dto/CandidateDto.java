package dto;


public class CandidateDto extends CompanyDto {

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CandidateDto{" +
                "price=" + price +
                "} " + super.toString();
    }
}
