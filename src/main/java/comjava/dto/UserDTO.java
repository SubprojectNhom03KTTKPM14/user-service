package comjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import comjava.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String phone;
	private String address;
	private String roleType;

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.roleType = user.getRoleType().toString();
	}

}
