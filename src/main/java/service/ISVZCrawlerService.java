package service;

import dto.CompanyDto;

import java.io.IOException;
import java.util.List;


public interface ISVZCrawlerService {

    List<CompanyDto> findAllSubmitters() throws IOException;
}
