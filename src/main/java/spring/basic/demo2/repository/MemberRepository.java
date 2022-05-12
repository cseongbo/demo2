package spring.basic.demo2.repository;

import org.springframework.stereotype.Repository;
import spring.basic.demo2.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberRepository implements MemberRepositoryInterface {

    public Map<Integer, Member> dbMap = new HashMap<>();
    public int index = 0;

    @Override
    public void saveMember(Member m) {
        m.setId(index++);
        dbMap.put(m.getId(), m);
    }

    @Override
    public Member findById(int id) {

        return dbMap.get(id);
    }

    @Override
    public List<Member> findAll() {

        return null;

    }
}
