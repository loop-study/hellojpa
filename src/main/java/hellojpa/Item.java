package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // InheritanceType.TABLE_PER_CLASS 의 큰 문제점... 조회 시 모든 테이블 유니온해서 찾아온다...
//@DiscriminatorColumn    // 조인전략에선 DiscriminatorColumn 을 사용해주자. default dtype 으로 들어감
public class Item extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    /**
     JPA 디폴트로 단일테이블 전략
     @Inheritance 로 변경가능

     create table Item (
         DTYPE varchar(31) not null,
         id bigint not null,
         name varchar(255),
         price integer not null,
         artist varchar(255),
         author varchar(255),
         isbn varchar(255),
         actor varchar(255),
         director varchar(255),
         primary key (id)
     )

     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
