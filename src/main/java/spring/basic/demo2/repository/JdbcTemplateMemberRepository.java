package spring.basic.demo2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import spring.basic.demo2.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateMemberRepository implements MemberRepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveMember(Member m) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", m.getName());

        jdbcInsert.execute(parameters);

    }

    @Override
    public Member findById(int id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper(), id);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper());
    }

    /*
        select 햇을 때, 사용할 결과 행들 (ResultSet)
     */
    private RowMapper<Member> memberRowMapper() {
        // RowMapper를 반환해주는 메소드를 짤거에요
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                // ResultSet(Select 결과물)을 Member 객체에 저장
                // 여러 형이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 써요.
                // 그래서 우리는 한행만 넣는 척 해도 됨.
                Member m = new Member();

                // 1행을 m에 담아요.
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));

                return m;
            }
        };
    }
}
