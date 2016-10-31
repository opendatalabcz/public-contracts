package service;


import dto.SubmitterDto;

import java.util.List;

public interface DatabaseReadService {

    List<SubmitterDto> getSubmitters(List<String> icos);
}
