package com.example.testconstructor.constructors;

import com.example.testconstructor.Answer.AnswerService;
import com.example.testconstructor.Question.QuestionService;
import com.example.testconstructor.Statisctics.StatisticService;
import com.example.testconstructor.Test.Test;
import com.example.testconstructor.Test.TestService;
import com.example.testconstructor.Url.Url;
import com.example.testconstructor.Url.UrlService;
import com.example.testconstructor.Users.User;
import com.example.testconstructor.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @org.junit.jupiter.api.Test
    void CRUDServiceTest() {
        Url url1 = new Url(1L, 1L, 1L);
        Url url2 = new Url(2L, 1L, 2L);

        //createUrl test
        assertThat(urlService.createUrl(url1)).isEqualTo(url1);

        //findUrlById test
        assertThat(urlService.findUrlById(1L)).isEqualTo(url1);

        //findAllUrls test
        urlService.createUrl(url2);
        assertThat(urlService.findAllUrls().size()).isEqualTo(2);

        //deleteUrlById test
        urlService.deleteUrlById(1L);
        assertThat(urlService.findAllUrls().size()).isEqualTo(1);

        //updateUrl test
        urlService.updateUrl(2L, 2L, 2L);
        assertThat(urlService.findUrlById(2l).getTestId()).isEqualTo(2L);


    }
}