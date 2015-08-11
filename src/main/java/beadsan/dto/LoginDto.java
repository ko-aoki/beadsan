package beadsan.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String mailAddress;
	private String password;
	private String message;

}
