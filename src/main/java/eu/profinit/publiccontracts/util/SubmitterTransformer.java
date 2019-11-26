package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.dto.*;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.businesstypes.v2.SubjektObchodniJmenoType;
import eu.profinit.publiccontracts.generated.isvs.micr.schemas.corecomponenttypes.v1.CenaType;
import eu.profinit.publiccontracts.generated.isvz.mmr.schemas.vz_z_profilu_zadavatele.v100.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Transforms XML schema representation objects of submitter to DTO objects.
 */
public class SubmitterTransformer {

    public static SubmitterDto transformSubmitterToDto(ProfilStructure profilStructure) {
        final SubmitterDto submitterDto = new SubmitterDto();
        final ZadavatelStructure submitter = profilStructure.getZadavatel();
        final String ico = submitter.getIcoVlastni().getValue();
        if (ico == null || ico.trim().isEmpty()) {
            throw new RuntimeException("Submitter has no ICO");
        }
        submitterDto.setIco(ico);
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
        final KodVZNaUsvzisType kodVzNaUsvzis = contract.getVZ().getKodVzNaVvz();
        final String code1 = kodVzNaUsvzis == null ? null : kodVzNaUsvzis.getValue();
        contractDto.setCode1(code1);
        final KodVzNaProfiluType kodVzNaProfilu = contract.getVZ().getKodVzNaProfilu();
        final String code2 = kodVzNaProfilu == null ? null : kodVzNaProfilu.getValue();
        contractDto.setCode2(code2);
        final NazevVZType nazevVz = contract.getVZ().getNazevVz();
        final String name = nazevVz == null ? null : nazevVz.getValue();
        contractDto.setName(name);
        final StavVZType stavVz = contract.getVZ().getStavVz();
        final String state = stavVz == null ? null : stavVz.getValue();
        contractDto.setState(state);
        final DruhZadavacihoRizeniType druhZadavacihoRizeni = contract.getVZ().getDruhZadavacihoRizeni();
        final String kind = druhZadavacihoRizeni == null ? null : druhZadavacihoRizeni.getValue();
        contractDto.setKind(kind);

        final List<UcastnikZRStructure> candidates = contract.getUcastnik();
        final List<CandidateDto> candidateDtos = transformCandidates(candidates);
        contractDto.setCandidateDtos(candidateDtos);

        final List<DodavatelStructure> suppliers = contract.getDodavatel();
        final List<SupplierDto> supplierDtos = transformSuppliers(suppliers);
        contractDto.setSupplierDtos(supplierDtos);

        final List<DokumentVzStructure> documents = contract.getVZ().getDokument();
        final List<DocumentDto> documentDtos = transformDocuments(documents);
        contractDto.setDocumentDtos(documentDtos);

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
        return supplierDto;
    }

    private static List<CandidateDto> transformCandidates(List<UcastnikZRStructure> candidates) {
        final List<CandidateDto> candidateDtos = new ArrayList<>();
        for (UcastnikZRStructure candidate : candidates) {
            final CandidateDto candidateDto = transformCandidate(candidate);
            candidateDtos.add(candidateDto);
        }
        return candidateDtos;
    }

    private static CandidateDto transformCandidate(UcastnikZRStructure candidate) {
        final CandidateDto candidateDto = new CandidateDto();
        final String ico = candidate.getIco().getValue();
        candidateDto.setIco(ico);
        final String candidateName = candidate.getNazevUcastnika().getValue();
        candidateDto.setName(candidateName);
        final CenaType cenaSDph = candidate.getCenaSDph();
        final Double price = cenaSDph == null ? null : cenaSDph.getValue().doubleValue();
        candidateDto.setPrice(price);
        return candidateDto;
    }

    private static List<DocumentDto> transformDocuments(List<DokumentVzStructure> documents) {
        final List<DocumentDto> documentDtos = new ArrayList<>();
        for (DokumentVzStructure document : documents) {
            final DocumentDto documentDto = transformDocument(document);
            documentDtos.add(documentDto);
        }
        return documentDtos;
    }

    private static DocumentDto transformDocument(DokumentVzStructure document) {
        final DocumentDto documentDto = new DocumentDto();
        final String url = document.getUrl().getValue();
        documentDto.setUrl(url);
        final String documentType = document.getTypDokumentu().getValue();
        documentDto.setDocumentType(documentType);
        final int documentVersion = document.getCisloVerze().getValue();
        documentDto.setDocumentVersion(documentVersion);
        final Timestamp timestamp = new Timestamp(document.getCasVlozeniNaProfil().getValue().toGregorianCalendar().getTimeInMillis());
        documentDto.setDocumentUploadDate(timestamp);
        documentDto.setToProcess(true);
        documentDto.setProcessed(false);
        return documentDto;
    }
}
