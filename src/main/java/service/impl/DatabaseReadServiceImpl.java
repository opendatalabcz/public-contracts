package service.impl;

import dto.*;
import hibernate.HibernateCriteriaCreator;
import model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import service.DatabaseReadService;

import java.util.ArrayList;
import java.util.List;


public class DatabaseReadServiceImpl implements DatabaseReadService {

    @Autowired
    private HibernateCriteriaCreator hibernateCriteriaCreator;


    @Override
    @Transactional
    public List<SubmitterDto> getSubmitters(List<String> icos) {
        final Criteria criteria = hibernateCriteriaCreator.createCriteria(Submitter.class, "submitter");
        criteria.createAlias("submitter.company", "company");
        criteria.add(Restrictions.in("company.ico", icos));
        final List<Submitter> submitters = criteria.list();

        final List<SubmitterDto> submitterDtos = new ArrayList<>();
        for (Submitter submitter : submitters) {
            final SubmitterDto submitterDto = new SubmitterDto();
            submitterDto.setIco(submitter.getCompany().getIco());
            submitterDto.setName(submitter.getCompany().getName());
            final List<ContractDto> contractDtos = new ArrayList<>();
            for (Contract contract : submitter.getContracts()) {
                final ContractDto contractDto = new ContractDto();
                contractDto.setCode1(contract.getCode1());
                contractDto.setCode2(contract.getCode2());
                contractDto.setKind(contract.getKind());
                contractDto.setName(contract.getName());
                contractDto.setState(contract.getState());

                final List<CandidateDto> candidateDtos = new ArrayList<>();
                for (Candidate candidate : contract.getCandidates()) {
                    final CandidateDto candidateDto = new CandidateDto();
                    candidateDto.setPrice(candidate.getPrice());
                    candidateDto.setIco(candidate.getCompany().getIco());
                    candidateDto.setName(candidate.getCompany().getName());
                    candidateDtos.add(candidateDto);
                }
                contractDto.setCandidateDtos(candidateDtos);

                final List<SupplierDto> supplierDtos = new ArrayList<>();
                for (Supplier supplier : contract.getSuppliers()) {
                    final SupplierDto supplierDto = new SupplierDto();
                    supplierDto.setPrice(supplier.getPrice());
                    supplierDto.setIco(supplier.getCompany().getIco());
                    supplierDto.setName(supplier.getCompany().getName());
                    final List<SubSupplierDto> subSupplierDtos = new ArrayList<>();
                    for (Subsupplier subsupplier : supplier.getSubsuppliers()) {
                        final SubSupplierDto subSupplierDto = new SubSupplierDto();
                        subSupplierDto.setIco(subsupplier.getCompany().getIco());
                        subSupplierDto.setName(subsupplier.getCompany().getName());
                        subSupplierDtos.add(subSupplierDto);
                    }
                    supplierDto.setSubSupplierDtos(subSupplierDtos);
                    supplierDtos.add(supplierDto);
                }
                contractDto.setSupplierDtos(supplierDtos);


                contractDtos.add(contractDto);
            }
            submitterDto.setContractDtos(contractDtos);
            submitterDtos.add(submitterDto);
        }
        return submitterDtos;
    }
}
