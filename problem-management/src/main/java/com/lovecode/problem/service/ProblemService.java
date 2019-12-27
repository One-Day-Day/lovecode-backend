package com.lovecode.problem.service;

import com.lovecode.problem.domain.Problem;
import com.lovecode.problem.dto.PageableResponse;
import com.lovecode.problem.dto.SummaryProblem;
import com.lovecode.problem.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public PageableResponse<SummaryProblem> getAllProblems(int currentPage, int pageSize) {
        Assert.isTrue(currentPage >= 0, "current page must be equals or greater than zero");
        Assert.isTrue(pageSize > 0, "page size must be greater than zero");

        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<Problem> page = problemRepository.findAll(pageRequest);
        List<SummaryProblem> summaryProblemList = page.getContent().stream()
                .map(SummaryProblem::from)
                .collect(Collectors.toList());
        return PageableResponse.<SummaryProblem>builder()
                .data(summaryProblemList)
                .totalNumbers((int) page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
