package util;

import dto.*;
import generated.isvs.micr.schemas.businesstypes.v2.SubjektObchodniJmenoType;
import generated.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.*;

import java.util.ArrayList;
import java.util.List;

public class SubmitterTransformer {

    public static SubmitterDto transformSubmitterToDto(ProfilStructure profilStructure) {
        final SubmitterDto submitterDto = new SubmitterDto();
        final ZadavatelStructure submitter = profilStructure.getZadavatel();
        submitterDto.setIco(submitter.getIcoVlastni().getValue());
        submitterDto.setName(submitter.getNazevZadavatele().getValue());

        final List<ZakazkaStructure> contracts = profilStructure.getZakazka();
        final List<ContractDto> contractDtos = transformContracts(contracts);
        submitterDto.setContractDtos(contractDtos);
        return submitterDto;
    }

    private static List<ContractDto> transformContracts(List<ZakazkaStructure> contracts) {
        final List<ContractDto> contractDtos = new ArrayList<>();
        for (ZakazkaStructure contract : contracts) {
            final ContractDto contractDto = transformContract(contract);
            contractDtos.add(contractDto);
        }
        return contractDtos;
    }

    private static ContractDto transformContract(ZakazkaStructure contract) {
        final ContractDto contractDto = new ContractDto();
        final KodVZNaUsvzisType kodVzNaUsvzis = contract.getVz().getKodVzNaUsvzis();
        final String code1 = kodVzNaUsvzis == null ? null : kodVzNaUsvzis.getValue();
        contractDto.setCode1(code1);
        final KodVzNaProfiluType kodVzNaProfilu = contract.getVz().getKodVzNaProfilu();
        final String code2 = kodVzNaProfilu == null ? null : kodVzNaProfilu.getValue();
        contractDto.setCode2(code2);
        final NazevVZType nazevVz = contract.getVz().getNazevVz();
        final String name = nazevVz == null ? null : nazevVz.getValue();
        contractDto.setName(name);
        final StavVZType stavVz = contract.getVz().getStavVz();
        final String state = stavVz == null ? null : stavVz.getValue();
        contractDto.setState(state);
        final DruhZadavacihoRizeniType druhZadavacihoRizeni = contract.getVz().getDruhZadavacihoRizeni();
        final String kind = druhZadavacihoRizeni == null ? null : druhZadavacihoRizeni.getValue();
        contractDto.setKind(kind);

        final List<UchazecStructure> candidates = contract.getUchazec();
        final List<CandidateDto> candidateDtos = transformCandidates(candidates);
        contractDto.setCandidateDtos(candidateDtos);

        final List<DodavatelStructure> suppliers = contract.getDodavatel();
        final List<SupplierDto> supplierDtos = transformSuppliers(suppliers);
        contractDto.setSupplierDtos(supplierDtos);
        return contractDto;
    }

    private static List<SupplierDto> transformSuppliers(List<DodavatelStructure> suppliers) {
        final List<SupplierDto> supplierDtos = new ArrayList<>();
        for (DodavatelStructure supplier : suppliers) {
            final SupplierDto supplierDto = transformSupplier(supplier);
            supplierDtos.add(supplierDto);
        }
        return supplierDtos;
    }

    private static SupplierDto transformSupplier(DodavatelStructure supplier) {
        final SupplierDto supplierDto = new SupplierDto();
        final IcoType ico = supplier.getIco();
        final String supplierIco = ico == null ? null : ico.getValue();
        supplierDto.setIco(supplierIco);
        final SubjektObchodniJmenoType nazevDodavatele = supplier.getNazevDodavatele();
        final String supplierName = nazevDodavatele == null ? null : nazevDodavatele.getValue();
        supplierDto.setName(supplierName);
        final CenaType cenaCelkemDleSmlouvyDPH = supplier.getCenaCelkemDleSmlouvyDPH();
        final Double price = cenaCelkemDleSmlouvyDPH == null ? null : cenaCelkemDleSmlouvyDPH.getValue().doubleValue();
        supplierDto.setPrice(price);

        final List<SubdodavatelStructure> subsuppliers = supplier.getSubdodavatel();
        final List<SubSupplierDto> subSupplierDtos = transformSubSuppliers(subsuppliers);
        supplierDto.setSubSupplierDtos(subSupplierDtos);
        return supplierDto;
    }

    private static List<SubSupplierDto> transformSubSuppliers(List<SubdodavatelStructure> subsuppliers) {
        final List<SubSupplierDto> subSupplierDtos = new ArrayList<>();
        for (SubdodavatelStructure subsupplier : subsuppliers) {
            final SubSupplierDto subSupplierDto = transformSubSupplier(subsupplier);
            subSupplierDtos.add(subSupplierDto);
        }
        return subSupplierDtos;
    }

    private static SubSupplierDto transformSubSupplier(SubdodavatelStructure subsupplier) {
        final SubSupplierDto subSupplierDto = new SubSupplierDto();
        final IcoType icoSub = subsupplier.getIcoSub();
        final String subsupplierIco = icoSub == null ? null : icoSub.getValue();
        subSupplierDto.setIco(subsupplierIco);
        final SubjektObchodniJmenoType nazevSub = subsupplier.getNazevSub();
        final String subsupplierName = nazevSub == null ? null : nazevSub.getValue();
        subSupplierDto.setName(subsupplierName);
        return subSupplierDto;
    }

    private static List<CandidateDto> transformCandidates(List<UchazecStructure> candidates) {
        final List<CandidateDto> candidateDtos = new ArrayList<>();
        for (UchazecStructure candidate : candidates) {
            final CandidateDto candidateDto = transformCandidate(candidate);
            candidateDtos.add(candidateDto);
        }
        return candidateDtos;
    }

    private static CandidateDto transformCandidate(UchazecStructure candidate) {
        final CandidateDto candidateDto = new CandidateDto();
        final String ico = candidate.getIco().getValue();
        candidateDto.setIco(ico);
        final String candidateName = candidate.getNazevUchazece().getValue();
        candidateDto.setName(candidateName);
        final CenaType cenaSDph = candidate.getCenaSDph();
        final Double price = cenaSDph == null ? null : cenaSDph.getValue().doubleValue();
        candidateDto.setPrice(price);
        return candidateDto;
    }

}
