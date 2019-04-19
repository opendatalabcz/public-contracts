package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.dto.SourceInfoDto;

import java.io.IOException;
import java.util.List;


public interface ISVZCrawlerService {

    List<SourceInfoDto> findAllSubmitters() throws IOException;
}
