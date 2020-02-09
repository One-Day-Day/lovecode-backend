package cc.lovecode.service;

import cc.lovecode.domain.entity.Problem;
import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.repository.ProblemRepository;
import cc.lovecode.domain.repository.UserRepository;
import cc.lovecode.dto.SummaryProblem;
import cc.lovecode.dto.request.CreateProblemRequest;
import cc.lovecode.dto.response.PageableResponse;
import cc.lovecode.enums.ErrorCode;
import cc.lovecode.exception.ObjectNotFoundException;
import cc.lovecode.exception.UnauthorizedAccessException;
import cc.lovecode.jwt.JWTUser;
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

    @Autowired
    private UserRepository userRepository;

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

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ErrorCode.PROBLEM_NOT_FOUND));
    }

    public Problem createProblem(CreateProblemRequest request, JWTUser jwtUser) {
        Problem problem = Problem.from(request);
        User owner = userRepository.findById(jwtUser.getId())
                .orElseThrow(UnauthorizedAccessException::new);
        problem.setOwner(owner);
        return problemRepository.save(problem);
    }
}
