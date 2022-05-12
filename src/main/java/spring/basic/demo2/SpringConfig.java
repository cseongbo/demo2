package spring.basic.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.basic.demo2.repository.JdbcMemberRepository;
import spring.basic.demo2.repository.JdbcTemplateMemberRepository;
import spring.basic.demo2.repository.MemberRepository;
import spring.basic.demo2.repository.MemberRepositoryInterface;
import spring.basic.demo2.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;      // 원래 있던 스프링 빈

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*

    Controller는 이 페이지에 모으지 않는다
        -> 왜냐하면, 원래부터 스프링 관할임

     */

    // @Service @Autowired
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepositoryInterface());
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInterface() {
        return new JdbcTemplateMemberRepository(dataSource);
        //return new JdbcMemberRepository(dataSource);
    }

}
