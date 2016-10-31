package service.impl;


import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import service.ARESService;
import service.CreateRDFSubmitters;
import service.DatabaseReadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CreateRDFSubmittersImpl implements CreateRDFSubmitters {

    @Autowired
    private DatabaseReadService databaseReadService;

    @Autowired
    private ARESService aresService;

    @Override
    public void createRDF(List<String> icos) {
        final List<SubmitterDto> submitterDtos = databaseReadService.getSubmitters(icos);

        final OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM);

        final String myTemp = "http://localhost:8080/swe/";
        final String baseUri = "http://localhost:8080/swe/datasets/submitters/";


        ontModel.setNsPrefix("myTemp", myTemp);


        final OntClass submitter = ontModel.createClass(myTemp + "Submitter");
        final OntClass company = ontModel.createClass(myTemp + "Company");
        final OntClass contract = ontModel.createClass(myTemp + "Contract");
        final OntClass candidate = ontModel.createClass(myTemp + "Candidate");
        final OntClass supplier = ontModel.createClass(myTemp + "Supplier");
        final OntClass subSupplier = ontModel.createClass(myTemp + "SubSupplier");

        company.addSubClass(submitter);
        company.addSubClass(supplier);
        company.addSubClass(subSupplier);
        company.addSubClass(candidate);

        final ObjectProperty companyInfo = ontModel.createObjectProperty(myTemp + "companyInfo");
        final ObjectProperty contractInfo = ontModel.createObjectProperty(myTemp + "contractInfo");
        final ObjectProperty candidateInfo = ontModel.createObjectProperty(myTemp + "candidateInfo");
        final ObjectProperty supplierInfo = ontModel.createObjectProperty(myTemp + "supplierInfo");
        final ObjectProperty subSupplierInfo = ontModel.createObjectProperty(myTemp + "subSupplierInfo");

        final DatatypeProperty icoDatatype = ontModel.createDatatypeProperty(myTemp + "ico");
        final DatatypeProperty nameDatatype = ontModel.createDatatypeProperty(myTemp + "name");
        final DatatypeProperty dicDatatype = ontModel.createDatatypeProperty(myTemp + "dic");
        final DatatypeProperty codeDatatype = ontModel.createDatatypeProperty(myTemp + "code");
        final DatatypeProperty stateDatatype = ontModel.createDatatypeProperty(myTemp + "state");
        final DatatypeProperty kindDatatype = ontModel.createDatatypeProperty(myTemp + "kind");
        final DatatypeProperty priceDatatype = ontModel.createDatatypeProperty(myTemp + "price");


        for (SubmitterDto dto : submitterDtos) {
            System.out.println(dto.getIco());

            final Individual submitterIndividual = submitter.createIndividual(baseUri + dto.getIco());
            final Individual submitterCompanyIndividual = company.createIndividual();
            final Literal submitterIco = ontModel.createTypedLiteral(dto.getIco(), XSDDatatype.XSDstring);
            final Literal submitterName = ontModel.createTypedLiteral(dto.getName(), XSDDatatype.XSDstring);

            submitterCompanyIndividual.addProperty(icoDatatype, submitterIco);
            submitterCompanyIndividual.addProperty(nameDatatype, submitterName);

            final CompanyInfoDto submitterCompanyInfoDto = aresService.findCompany(dto.getIco());
            if (submitterCompanyInfoDto != null) {
                final Literal submitterDic = ontModel.createTypedLiteral(submitterCompanyInfoDto.getDic(), XSDDatatype.XSDstring);
                submitterCompanyIndividual.addProperty(dicDatatype, submitterDic);
            }

            submitterIndividual.addProperty(companyInfo, submitterCompanyIndividual);
            for (ContractDto contractDto : dto.getContractDtos()) {
                final Individual contractIndividual = contract.createIndividual();
                final Literal contractCode = ontModel.createTypedLiteral(contractDto.getCode2(), XSDDatatype.XSDstring);
                final Literal contractName = ontModel.createTypedLiteral(contractDto.getName(), XSDDatatype.XSDstring);
                final Literal contractKind = ontModel.createTypedLiteral(contractDto.getKind(), XSDDatatype.XSDstring);
                final Literal contractState = ontModel.createTypedLiteral(contractDto.getState(), XSDDatatype.XSDstring);
                contractIndividual.addProperty(nameDatatype, contractName);
                contractIndividual.addProperty(codeDatatype, contractCode);
                contractIndividual.addProperty(kindDatatype, contractKind);
                contractIndividual.addProperty(stateDatatype, contractState);

                for (CandidateDto candidateDto : contractDto.getCandidateDtos()) {
                    final Individual candidateIndividual = candidate.createIndividual();
                    final Individual candidateCompanyIndividual = company.createIndividual();
                    final Literal candidateIco = ontModel.createTypedLiteral(candidateDto.getIco(), XSDDatatype.XSDstring);
                    final Literal candidateName = ontModel.createTypedLiteral(candidateDto.getName(), XSDDatatype.XSDstring);

                    candidateCompanyIndividual.addProperty(icoDatatype, candidateIco);
                    candidateCompanyIndividual.addProperty(nameDatatype, candidateName);


                    final CompanyInfoDto candidateCompanyInfoDto = aresService.findCompany(candidateDto.getIco());
                    if (candidateCompanyInfoDto != null) {
                        final Literal candidateDic = ontModel.createTypedLiteral(candidateCompanyInfoDto.getDic(), XSDDatatype.XSDstring);
                        candidateCompanyIndividual.addProperty(dicDatatype, candidateDic);
                    }
                    candidateIndividual.addProperty(companyInfo, candidateCompanyIndividual);
                    final Literal candidatePrice = ontModel.createTypedLiteral(candidateDto.getPrice(), XSDDatatype.XSDdouble);
                    candidateIndividual.addProperty(priceDatatype, candidatePrice);
                    contractIndividual.addProperty(candidateInfo, candidateIndividual);

                }

                for (SupplierDto supplierDto : contractDto.getSupplierDtos()) {
                    final Individual supplierIndividual = supplier.createIndividual();
                    final Individual supplierCompanyIndividual = company.createIndividual();
                    final Literal supplierIco = ontModel.createTypedLiteral(supplierDto.getIco(), XSDDatatype.XSDstring);
                    final Literal supplierName = ontModel.createTypedLiteral(supplierDto.getName(), XSDDatatype.XSDstring);

                    supplierCompanyIndividual.addProperty(icoDatatype, supplierIco);
                    supplierCompanyIndividual.addProperty(nameDatatype, supplierName);

                    final CompanyInfoDto supplierCompanyInfoDto = aresService.findCompany(supplierDto.getIco());
                    if (supplierCompanyInfoDto != null) {
                        final Literal supplierDic = ontModel.createTypedLiteral(supplierCompanyInfoDto.getDic(), XSDDatatype.XSDstring);
                        supplierCompanyIndividual.addProperty(dicDatatype, supplierDic);
                    }
                    supplierIndividual.addProperty(companyInfo, supplierCompanyIndividual);
                    final Literal supplierPrice = ontModel.createTypedLiteral(supplierDto.getPrice(), XSDDatatype.XSDdouble);
                    supplierIndividual.addProperty(priceDatatype, supplierPrice);


                    for (SubSupplierDto subSupplierDto : supplierDto.getSubSupplierDtos()) {
                        final Individual subSupplierIndividual = subSupplier.createIndividual();
                        final Individual subSupplierCompanyIndividual = company.createIndividual();
                        final Literal subSupplierIco = ontModel.createTypedLiteral(subSupplierDto.getIco(), XSDDatatype.XSDstring);
                        final Literal subSupplierName = ontModel.createTypedLiteral(subSupplierDto.getName(), XSDDatatype.XSDstring);

                        subSupplierCompanyIndividual.addProperty(icoDatatype, subSupplierIco);
                        subSupplierCompanyIndividual.addProperty(nameDatatype, subSupplierName);

                        final CompanyInfoDto subSupplierCompanyInfoDto = aresService.findCompany(subSupplierDto.getIco());
                        if (subSupplierCompanyInfoDto != null) {
                            final Literal subSupplierDic = ontModel.createTypedLiteral(subSupplierCompanyInfoDto.getDic(), XSDDatatype.XSDstring);
                            subSupplierCompanyIndividual.addProperty(dicDatatype, subSupplierDic);
                        }
                        subSupplierIndividual.addProperty(companyInfo, subSupplierCompanyIndividual);
                        supplierIndividual.addProperty(subSupplierInfo, subSupplierIndividual);

                    }
                    contractIndividual.addProperty(supplierInfo, supplierIndividual);
                }


                submitterIndividual.addProperty(contractInfo, contractIndividual);
            }


            ontModel.add(company.getModel());

        }

        CreateRdfFile(ontModel, "Turtle");


    }

    public void CreateRdfFile(OntModel ontModel, String type) {
        try {

            final String filePath = "submitters.ttl";

            final File file = new File(filePath);


            if (!file.exists()) {
                file.delete();
            }

            final OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"
            );
            System.out.println(writer.getEncoding());
            ontModel.write(writer, type);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
