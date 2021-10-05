package io.github.zhyshko.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Rooms")
public class Room{

	@Id
	private long id;

	private int airPollution;

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Room_Access",
        joinColumns = { @JoinColumn(name = "room_id") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
	private Set<User> allowedUsers;

	private LocalDateTime lastUpdatedTime;

}
