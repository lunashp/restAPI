package kr.co.dajsoft.hell0.service;

import kr.co.dajsoft.hell0.dto.ApiDTO;
import kr.co.dajsoft.hell0.dto.PageRequestDTO;
import kr.co.dajsoft.hell0.dto.PageResultDTO;
import kr.co.dajsoft.hell0.entity.Api;
import kr.co.dajsoft.hell0.repository.ApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ApiServiceImpl implements ApiService{

    private final ApiRepository apiRepository;



    @Override
    public PageResultDTO<ApiDTO, Object[]> getList(PageRequestDTO dto) {
        Page<Object []> result = apiRepository.searchPage(
                dto.getType(), dto.getKeyword(),
                dto.getPageable(Sort.by("apino").descending())
        );

        Function<Object[], ApiDTO> fn = (
                en -> entityToDTO((Api) en[0]));
        return new PageResultDTO<>(result, fn);

    }

    @Override
    public List<ApiDTO> get() {
        List<Api> list = apiRepository.findAll();
        List<ApiDTO> result = new ArrayList<>();
        for(Api api : list){
            result.add(entityToDTO(api));
        }

        return result;
    }
}
