package io.github.zhyshko.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
public class User {

	@Id
	private String id;
	private String username;

	@ManyToOne
	@JoinColumn(name="room_id", nullable=true)
	private Room currentRoom;

}
