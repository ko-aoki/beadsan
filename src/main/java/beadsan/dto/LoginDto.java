package beadsan.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginDto {

	private int userId;
	private String mailAddress;
	private String passwowd;
	private String nickName;
	private String message;
	private boolean logined;
	
}
