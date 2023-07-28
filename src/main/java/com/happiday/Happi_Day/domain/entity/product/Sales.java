package com.happiday.Happi_Day.domain.entity.product;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "sales")
public class Sales extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 판매글 카테고리 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = false)
    private SalesCategory salesCategory;

    // 판매자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus;

    // 상품
    @OneToMany(mappedBy = "sales")
    private List<Product> products = new ArrayList<>();

    // 주문 매핑
    @OneToOne(mappedBy = "sales")
    private Order order;

    // 판매글 찜하기
    @ManyToMany(mappedBy = "sales")
    private List<User> users = new ArrayList<>();

    // 아티스트-판매글 매핑
    @ManyToMany(mappedBy ="sales" )
    private List<Artist> artists = new ArrayList<>();

    // 팀-판매글 매핑
    @ManyToMany(mappedBy = "sales")
    private List<Team> teams = new ArrayList<>();
}
