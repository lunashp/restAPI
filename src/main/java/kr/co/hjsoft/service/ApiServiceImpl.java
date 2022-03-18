package kr.co.hjsoft.service;

import kr.co.hjsoft.dto.ApiDTO;
import kr.co.hjsoft.dto.PageRequestDTO;
import kr.co.hjsoft.dto.PageResultDTO;
import kr.co.hjsoft.entity.Api;
import kr.co.hjsoft.repository.ApiRepository;
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
