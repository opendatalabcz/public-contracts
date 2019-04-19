package eu.profinit.publiccontracts.dto;


import java.util.List;

public class ContractDto {

    private String code1;
    private String code2;
    private String state;
    private String name;
    private String kind;

    private List<CandidateDto> candidateDtos;
    private List<SupplierDto> supplierDtos;
    private List<DocumentDto> documentDtos;

    public List<CandidateDto> getCandidateDtos() {
        return candidateDtos;
    }

    public void setCandidateDtos(List<CandidateDto> candidateDtos) {
        this.candidateDtos = candidateDtos;
    }

    public List<SupplierDto> getSupplierDtos() {
        return supplierDtos;
    }

    public void setSupplierDtos(List<SupplierDto> supplierDtos) {
        this.supplierDtos = supplierDtos;
    }

    public List<DocumentDto> getDocumentDtos() {
        return documentDtos;
    }

    public void setDocumentDtos(List<DocumentDto> documentDtos) {
        this.documentDtos = documentDtos;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
