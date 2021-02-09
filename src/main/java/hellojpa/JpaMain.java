package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
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

//            Member member = new Member();
//            member.setUsername("A");
//
//            Member member2 = new Member();
//            member2.setUsername("B");
//
//            Member member3 = new Member();
//            member3.setUsername("C");
////            member.setRoleType(RoleType.ADMIN);
//
//            // @GeneratedValue(strategy = GenerationType.IDENTITY) 때문에 곧바로 insert 실행한다.
//            // DB 에서 id 값을 받아오기위해서다
//            System.out.println("==============");
//
//            // DB SEQ = 1   |   1
//            // DB SEQ = 51  |   2
//            // DB SEQ = 51  |   3
//            em.persist(member);  //1, 51
//            em.persist(member2); //MEM
//            em.persist(member3); //MEM
//
//            System.out.println("member.getId() = " + member.getId());
//            System.out.println("member2.getId() = " + member2.getId());
//            System.out.println("member3.getId() = " + member3.getId());
//
//            System.out.println("==============");


//===================
// 3. 연관관계 매핑
//===================
//
//            Team team = new Team();
//            team.setName("TeamA");
////            team.getMembers().add(member);
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
////            member.changeTeam(team);
//            em.persist(member);
//
//            // 연관관계 편의 메서드가 양쪽에 있다면? 하나만 써야한다.
//            // 값 세팅은 방향에 상관없다. 주인쪽은 mappedBy 설정이다
//            // 상황에 따라 주인관계가 다를 수 있다.
////            team.addMember(member);
//
//            // 주석처리하면 문제가 생긴다? 실행해보자,
//            // insert 만 되고 조회쿼리는 없다.
//            // 아래 포이치 member size 는 0 이다!!
//            // 아래의 코드를 Member.setTeam() 안에 넣어주자
////            team.getMembers().add(member);
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            // 무한루프... toString자, lombok, json 라이브러리를 조심하자
//            System.out.println("==============");
//            System.out.println("members = " + findMember.toString());
//            System.out.println("==============");

            // 일대다 단방향 매핑 - 비권장... 파악하기 혼란하다...
//            Member member = new Member();
//            member.setUsername("member1");
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//
//            em.persist(member);
//            em.persist(member2);
//
//            Team team = new Team();
//            team.setName("teamA");
//            // update 실행됨
//            team.getMembers().add(member);
//            team.getMembers().add(member2);
//
//            em.persist(team);

//===================
// 4. 고급 매핑
//===================
//            Movie movie = new Movie();
//            movie.setDirector("aaa");
//            movie.setActor("bbb");
//            movie.setName("JPA 와 함께 춤을");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            // InheritanceType.TABLE_PER_CLASS 의 큰 문제점... 모든 테이블 유니온해서 찾아온다...
//            Item Item = em.find(Item.class, movie.getId());
//            System.out.println("Item = " + Item);
//
//            Member member = new Member();
//            member.setUsername("kim");
//            member.setCreateBy("kim");
//            member.setCreateDate(LocalDateTime.now());
//
//            em.persist(member);

//            em.flush();
//            em.clear();
            
//===================
// 5. 프록시
//===================

//            Member member = new Member();
//            member.setUsername("hello");
//
//            Member member2 = new Member();
//            member2.setUsername("hello2");
//
//            em.persist(member);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
////            Member findMember = em.find(Member.class, member.getId());
//            // select 쿼리가 안나가네? -> 실제 사용하는 시점에 조회됨.
//            // id 는 위의 값을 넣어서 뭐지 알 수 있음. username 은 없어서 디비에 조회해옴
//            Member findMember = em.getReference(Member.class, member.getId());
//            printMember(findMember);
//
//            // 같은 영속성에 등록된 객체로 조회하면 같은 타입이 된다
//            // 만약의 위에서 프록시로 조회하고 여기도 프록시로 한다면?
//            // 사용될 떄 조회 쿼리는 한번만 나갈까?
//            Member findMember2 = em.find(Member.class, member.getId()); // em.find -> em.getReference
//            System.out.println("findMember2.getClass() = " + findMember2.getClass());
////            Member findMember2 = em.find(Member.class, member2.getId());
//            //
//            System.out.println("findMember2 == findMember = " + (findMember2.getClass() == findMember.getClass()));
//            System.out.println("findMember2 == findMember = " + (findMember instanceof Member));
////            printMemberAndTeam(member);
//
//            Member member = new Member();
//            member.setUsername("hello1");
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println("refMember.getClass() = " + refMember.getClass()); // 프록시
//            // 프록시 초기화 여부 확인 : PersistenceUnitUtil.isLoaded(refMember)
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
//            // JPA 강제초기화 기능을 제공함, 표준은 아니다
//            Hibernate.initialize(refMember);
//            Team team = new Team();
//            team.setName("team");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("hello1");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            // member 조회가 끝나고 곧바로 team 조회가 실행됨...
//            // JPQL 은 sql 로 번역되고 가져왔는데 필드 중에 team 이 존재하네? 즉시로딩이네? 곧바로 별도의 team 조회쿼리가 실행됨
//            // 멤버 건수마다 각각 team 조회 쿼리가 날라감...
////            List<Member> members = em.createQuery("select m from Member m", Member.class)
////                    .getResultList();
//
//            // 정말 필요하다면 fetch 조인을 사용한다. (team은 지연로딩 상태임)
//            // 지연 로딩 상관없이 한번에 쫘악 가져오는 쿼리
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                    .getResultList();


            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            // Parent 에서 Child @ManyToOne 속성 cascade = Cascade.ALL 로 해준다
            // 자식까지 insert 됨. 부모가 삭제되면 연관된 자식도 따라 삭제됨. (자식이 여러군데 사용되면 삭제되면 안됨.)
            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            // orphanRemoval = true 속성값으로 delete 쿼리 실행됨
            // findParent.getChildList().remove(0);

            // 부모가 삭제되면? 자식도 저절로 삭제됨.
//            em.remove(parent);

//            em.persist(child1);
//            em.persist(child2);

            tx.commit();    // 트랜잭션이 끝나는 시점에 영속성에 등록된게 실행된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

//    // 프록시에 대한 예시.
//    private static void printMember(Member member) {
//        // 멤버만 조회하고 싶다.
//        System.out.println("before member.getClass() = " + member.getClass());
//        System.out.println("member.getId() = " + member.getId());
//        System.out.println("member.getUsername() = " + member.getUsername());
//        System.out.println("after member.getClass() = " + member.getClass());
//    }
//
//    private static void printMemberAndTeam(Member member) {
//        // 멤버, 팀 모두 조회하고 싶다.
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team = " + team.getName());
//    }
}
