package com.happiday.Happi_Day.domain.entity.artist;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.product.Sales;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@SQLDelete(sql = "UPDATE artist SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Artist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String profileUrl;

    // 팀-아티스트
    @OneToMany(mappedBy = "artist")
    private List<ArtistTeam> artistTeamList = new ArrayList<>();

    // 이벤트
    @OneToMany(mappedBy = "artist")
    private List<ArtistEvent> events = new ArrayList<>();

    // 판매글
    @ManyToMany(mappedBy = "artists")
    private List<Sales> salesList = new ArrayList<>();

    // 유저 구독
    @OneToMany(mappedBy = "artist")
    private List<ArtistSubscription> subscribers = new ArrayList<>();

    // 게시판
    @OneToMany(mappedBy = "artist")
    private List<ArtistArticle> artistArticleList = new ArrayList<>();

    public void update(Artist artistUpdate) {
        this.name = artistUpdate.getName();
        this.description = artistUpdate.getDescription();
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setTeams(List<ArtistTeam> artistTeamList) {
        if (this.artistTeamList == null) {
            this.artistTeamList = new ArrayList<>();
        } else {
            this.artistTeamList.clear();
        }

        if (artistTeamList != null) {
            this.artistTeamList.addAll(artistTeamList);
        }
    }
}
