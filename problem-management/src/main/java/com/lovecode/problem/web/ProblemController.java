package com.lovecode.problem.web;

import com.lovecode.problem.domain.Problem;
import com.lovecode.problem.dto.CreateProblemRequest;
import com.lovecode.problem.dto.PageableResponse;
import com.lovecode.problem.dto.SummaryProblem;
import com.lovecode.problem.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/problems")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping
    @ResponseBody
    public PageableResponse<SummaryProblem> getAllProblems(@RequestParam int page, @RequestParam int size) {
        return problemService.getAllProblems(page, size);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Problem getOneProblem(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Problem createProblem(@RequestBody CreateProblemRequest request) {
        return problemService.createProblem(request);
    }
}
