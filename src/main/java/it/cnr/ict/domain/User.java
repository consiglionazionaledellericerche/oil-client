package it.cnr.ict.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String familyName;
	private String password;
	private Long profile;
	private String login;
	private String email;
	private String struttura;
	private String mailStop;

    public User() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Long getProfile() {
		return profile;
	}

	public void setProfile(Long profile) {
		this.profile = profile;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStruttura() {
		return struttura;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}

	public String getMailStop() {
		return mailStop;
	}

	public void setMailStop(String mailStop) {
		this.mailStop = mailStop;
	}
}
