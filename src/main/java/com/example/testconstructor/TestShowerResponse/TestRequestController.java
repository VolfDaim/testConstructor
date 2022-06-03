package com.example.testconstructor.TestShowerResponse;


import com.example.testconstructor.Question.QuestionService;
import com.example.testconstructor.Test.Test;
import com.example.testconstructor.Test.TestRepository;
import com.example.testconstructor.Test.TestService;
import com.example.testconstructor.Url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/passageTest")
public class TestRequestController {

    private final TestService testService;
    private final QuestionService questionService;
    private final UrlService urlService;
    private final TestRepository testRepository;

    @Autowired
    public TestRequestController(TestService testService,
                                 QuestionService questionService,
                                 UrlService urlService, TestRepository testRepository) {
        this.testService = testService;
        this.questionService = questionService;
        this.urlService = urlService;
        this.testRepository = testRepository;
    }

    @GetMapping("{url}")
    public TestRequest showTest(HttpServletResponse response, @PathVariable("url") String url) {
        changeCorsPolicy(response);
		Test test = testService.findTestById(urlService.parseUrl(url));
		return testService.showTest(test);
    }

	@PostMapping("{url}")
	public String finishTest(HttpServletResponse response, @PathVariable("url") String url,
							 @RequestBody TestRequest testResponse) {
        changeCorsPolicy(response);
		Test test = testService.findTestById(urlService.parseUrl(url));
		return questionService.checkTest(test, testResponse);
	}

    private void changeCorsPolicy(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }

}