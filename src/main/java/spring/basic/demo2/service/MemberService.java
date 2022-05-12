package spring.basic.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basic.demo2.domain.Member;
import spring.basic.demo2.repository.MemberRepository;
import spring.basic.demo2.repository.MemberRepositoryInterface;

import java.util.List;


public class MemberService {

    private MemberRepositoryInterface repository;

    @Autowired
    public MemberService(MemberRepositoryInterface repository) {
        this.repository = repository;
    }

    public void join(Member m) {
        repository.saveMember(m);
    }

    public Member findMemberById(int id) {
        return repository.findById(id);
    }

    public List<Member> findAllMember() {
        return repository.findAll();
    }

}
