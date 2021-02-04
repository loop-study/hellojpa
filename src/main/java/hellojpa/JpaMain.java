package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // persistence.xml 에서 설정한 이름을 넘겨준다
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        // 데이터가 변경되는 모든 작업에는 트랜잭션이 걸려있어야 한다, 조회는 없어도 됨
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 중간에 에러나면 멈추니깐 try catch 로 감싸준다
        try {


//===================
// 1. 영속성 관리
//===================

            // insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

            // select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // JPQL 을 이용한 조회, 멤버객체를 대상으로 쿼리를 작성한다고 보면 됨
            // 1. 페이징 어떻게 해요?  setFirstResult 시작 지점 setMaxResults 조회 건수 설정 가능
//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .setFirstResult(4)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member mb : members) {
//                System.out.println("mb.getName() = " + mb.getName());
//            }

            // delete
            // em.remove(findMember);

            // update - 와... 신기함...
//            findMember.setName("박현");

            //em.persist(member);

            // 비영속 - new 생성된 상태. 연결안되어있음
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("helloJPA");

//            System.out.println("===========BEFORE===========");
//            em.persist(member); // 왜 실행시점이 다름?
            //em.detach(member); // 등록된 영속성 제거하기
//            System.out.println("===========AFTER ===========");

            // select sql 이 없네?, 영속성에 등록된 캐시를 가져옴
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            // 조회가 한번만 됨.
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("member1 == member2, " + (findMember1 == findMember2));

            // 영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            // 곧바로 쿼리 실행은 아님. 트랜잭션 끝나는 시점임.
//            // 버퍼링을 사용하면 중간에 실행 가능함, persistence.xml 에서 세팅함
//            em.persist(member1);
//            em.persist(member2);

            // 엔티티 수정, 변경감지(영어로 더티체킹)
//            Member member = em.find(Member.class, 150L);
//            member.setName("zzzzzz");
            // 영속성에 등록된 객체의 값이 변경되면 자동적으로 update 실행해줌

            // flush, 영속성 컨텍스트의 내용을 곧바로 DB에 반영함. 1차 캐시는 변함없음
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            System.out.println("================");
//            em.flush();
//            System.out.println("================");

            // JPQL 실행하면 곧바로 플러시가 자동 실행됨, JPQL 사용 시 insert 가 있는지 조심하자

            // 준영속 상태 - 영속 컨텍스트에서 분리된다
//            em.detach(member); // 특정 엔티티를 제거함

//            Member member = em.find(Member.class, 150L);
//            em.clear(); // 영속성 컨텍스트 전체 초기화
//            Member member2 = em.find(Member.class, 150L);
//
//            System.out.println("member == member2 : " + (member == member2));

//===================
// 2. 엔티티 매핑
//===================
            // @Table(name = "MBR") 으로 명시된 테이블 조
//            Member member = em.find(Member.class, 150L);

            Member member = new Member();
            member.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");
//            member.setRoleType(RoleType.ADMIN);

            // @GeneratedValue(strategy = GenerationType.IDENTITY) 때문에 곧바로 insert 실행한다.
            // DB 에서 id 값을 받아오기위해서다
            System.out.println("==============");

            // DB SEQ = 1   |   1
            // DB SEQ = 51  |   2
            // DB SEQ = 51  |   3
            em.persist(member);  //1, 51
            em.persist(member2); //MEM
            em.persist(member3); //MEM

            System.out.println("member.getId() = " + member.getId());
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("member3.getId() = " + member3.getId());

            System.out.println("==============");

            tx.commit();    // 트랜잭션이 끝나는 시점에 영속성에 등록된게 실행된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
