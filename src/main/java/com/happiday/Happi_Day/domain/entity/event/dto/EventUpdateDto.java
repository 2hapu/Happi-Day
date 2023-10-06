package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.team.Team;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class EventUpdateDto {

    public EventUpdateDto() {
    }
    public EventUpdateDto(String title, LocalDateTime startTime, LocalDateTime endTime, String description, String location, String thumbnailUrl, String imageUrl, List<String> artists, List<String> teams) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.location = location;
        this.thumbnailUrl = thumbnailUrl;
        this.imageUrl = imageUrl;
        this.artists = artists;
        this.teams = teams;
    }

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private String location;

    private String thumbnailUrl;

    private String imageUrl;

    private List<String> artists;

    private List<String> teams;

}
