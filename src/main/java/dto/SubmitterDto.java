package dto;


import java.util.List;

public class SubmitterDto extends CompanyDto {

    private List<ContractDto> contractDtos;

    public List<ContractDto> getContractDtos() {
        return contractDtos;
    }

    public void setContractDtos(List<ContractDto> contractDtos) {
        this.contractDtos = contractDtos;
    }
}
