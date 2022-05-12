package spring.basic.demo2.repository;

import spring.basic.demo2.domain.Member;

import java.util.List;


public interface MemberRepositoryInterface {

    void saveMember(Member m);
    Member findById(int id);
    List<Member> findAll();

}
