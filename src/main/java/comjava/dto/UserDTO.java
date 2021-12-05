package comjava.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import comjava.entity.User;
import comjava.utils.MyRegex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Integer id;
	
	@NotBlank
	@Size(max = 50)
	@Pattern(regexp = MyRegex.USERNAME_REGEX, message = "Invalid")
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Pattern(regexp = MyRegex.PASSWORD_REGEX,  message = "Invalid")
	private String password;
	
	@NotBlank
	@Pattern(regexp = MyRegex.PHONE_REGEX , message = "Invalid")
	private String phone;
	
	@NotBlank
	private String address;

	@JsonProperty(access = Access.READ_ONLY)
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
