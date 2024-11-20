package application;
/*users - класс для таблицы Пользователи.
 * Поля:
 * login - логин пользователя;
 * password - пароль пользователя;
 * name - имя пользователя;
 * lastname - фамилия пользователя;
 * status - статус пользователя.
 */
public class users {
	String login, password, name, lastname;
	Integer status;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public users(String login, String password, String name, String lastname, Integer status) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.status = status;
	}
	
	
	

}
