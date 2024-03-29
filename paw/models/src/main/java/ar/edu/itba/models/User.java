package ar.edu.itba.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "account")
public class User implements Serializable {

	@Id
	@Column(length = 100, nullable = false, unique = true)
	private String username;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 100, nullable = false, unique = true)
	private String mail;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<ProjectUser> projects;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private Set<Task> tasks;
	
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
	private Set<Project> adminsProjects;
	
	private User() {
		// Just for hibernate
	}
	
	private User(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mail = mail;
	}

	public String username() {
		return username;
	}

	public String password() {
		return password;
	}

	public String mail() {
		return mail;
	}

	public boolean equals(Object another) {
		if (this == another) return true;
		return another instanceof User
				&& equalTo((User) another);
	}

	private boolean equalTo(User another) {
		return username.equals(another.username)
				&& password.equals(another.password)
				&& mail.equals(another.mail);
	}

	public int hashCode() {
		int h = 31;
		h = h * 17 + username.hashCode();
		h = h * 17 + password.hashCode();
		h = h * 17 + mail.hashCode();
		return h;
	}

	public String toString() {
		return "User{"
				+ "username=" + username
				+ ", password=" + password
				+ ", mail=" + mail
				+ "}";
	}

	public static User.Builder builder() {
		return new User.Builder();
	}

	public static final class Builder {
		private static final long INIT_BIT_USERNAME = 0x1L;
		private static final long INIT_BIT_PASSWORD = 0x2L;
		private static final long INIT_BIT_MAIL = 0x4L;
		private long initBits = 0x7;

		private String username;
		private String password;
		private String mail;

		private Builder() {
		}

		public final Builder from(User instance) {
			Objects.requireNonNull(instance, "instance");
			username(instance.username());
			password(instance.password());
			mail(instance.mail());
			return this;
		}

		public final Builder username(String username) {
			this.username = Objects.requireNonNull(username, "username");
			initBits &= ~INIT_BIT_USERNAME;
			return this;
		}

		public final Builder password(String password) {
			this.password = Objects.requireNonNull(password, "password");
			initBits &= ~INIT_BIT_PASSWORD;
			return this;
		}

		public final Builder mail(String mail) {
			this.mail = Objects.requireNonNull(mail, "mail");
			initBits &= ~INIT_BIT_MAIL;
			return this;
		}

		public User build() {
			if (initBits != 0) {
				throw new IllegalStateException(formatRequiredAttributesMessage());
			}
			return new User(username, password, mail);
		}

		private String formatRequiredAttributesMessage() {
			List<String> attributes = new ArrayList<String>();
			if ((initBits & INIT_BIT_USERNAME) != 0) attributes.add("username");
			if ((initBits & INIT_BIT_PASSWORD) != 0) attributes.add("password");
			if ((initBits & INIT_BIT_MAIL) != 0) attributes.add("mail");
			return "Cannot build User, some of required attributes are not set " + attributes;
		}
	}

}
