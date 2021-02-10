package hellojpa;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Member { // extends BaseEntity

    @Id
    @GeneratedValue
//    @Column(name = "MEMBER_ID")
    private Long id;

    //    @Column(name = "USERNAME")
    private String username;

    // 객체간 관계에 괴리감 생김
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 일대다 양방향 매핑... 야매 방식
    // insertable = false, updatable = false 을 넣어준다
    // -> insert, update 를 막아 읽기전용으로 만들어버람
//    @ManyToOne(fetch = FetchType.LAZY) // team 객체를 프록시로 만듬. 실제 디비에선 멤버만 조회함, 디폴트 FetchType.EAGER (즉시 로딩)
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
//    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

//    @OneToMany(mappedBy = "member")
////    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

    // 기간 Period
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//    @Embedded // 생략해도 되지만 가독성을 위해 넣어줌
//    private Period workPeriod;

    //    // 주소 Address
//    private String city;
//    private String street;
//    private String zipcode;
    @Embedded // 생략해도 되지만 가독성을 위해 넣어줌
    private Address homeAddress;

    // 직장 주소까지 있으면? 중복되는데?
    // @AttributeOverrides 로 컬럼명을 바꿔준다
    // 하나면 @AttributeOverride 로 바꿔준다
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="city",
//                    column=@Column(name="WORK_CITY")),
//            @AttributeOverride(name="street",
//                    column=@Column(name="WORK_STREET")),
//            @AttributeOverride(name="zipcode",
//                    column=@Column(name="WORK_ZIPCODE"))
//    })
//    private Address workAddress;

    // 별도의 테이블로 생성해서 넣어준다
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME") // 값이 하나니깐 예외적으로 가능
    private Set<String> favoriteFoods = new HashSet<>();

    // 사용이 복잡함. 그냥 엔티티로 빼는게 좋다
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name = "MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
