package eu.profinit.publiccontracts.service;

import eu.profinit.publiccontracts.dto.SourceInfoDto;

import java.io.IOException;
import java.util.List;


/**
 * Interface for submitters web crawling.
 */
public interface ISVZCrawlerService {

    List<SourceInfoDto> findAllSubmitters() throws IOException;

    List<SourceInfoDto> findSubmitters() throws IOException;
}
