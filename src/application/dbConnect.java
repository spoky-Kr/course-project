 /*
    dbConnect - класс для работы с базой данных.
    Используемые подпрограммы:
    dbConnect - процедура подключения к базе данных;
    getCoach - функция получения записей из таблицы Тренеры;
    getSeasonTicket - функция получения записей из Абонементы;
    getgr - функция получения записей из таблицы Группы;
    getBass - функция получения записей из таблицы Бассейны;
    getId - функция получения кода записи из таблицы;
    getLog - функция получения логина из таблицы пользователи;
    setGrDobav - процедура добавления записи в БД в таблицу Группы;
    setStDobav - процедура добавления записи в БД в таблицу Абонементы;
    setUsers - процедура добавления записи в БД в таблицу Пользователи;
    setCoachDobav - процедура добавления записи в БД в таблицу Тренеры;
    setBassDobav - процедура добавления записи в БД в таблицу Бассейны;
    deleteString - процедура удаления выбранной записи из БД;
    deleteStringUser - процедура удаления выбранной из таблицы Пользователи;
    updateGr - процедура изменения записи в таблице Группы;
    updateCoach - процедура изменения записи в таблице Тренеры;
    updateBass - процедура изменения записи в таблице Бассейны;
    updateSt - процедура изменения записи в таблице Абонементы;
    updateUsers - процедура изменения записи в таблице Пользователи;
    getUsers - функция получения записей из БД из таблицы Пользователи;
    getString - функция получения поля из БД из указанной таблицы;
    zapros - функция получения полей из БД для вывода 2-ого запроса.
    */
package application;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class dbConnect {
	private Connection connection;
    
	/*dbConnect() - процедура подключения к базе данных.
 Локальные переменные:
 connection - объект класса Connection.
 Используемые подпрограммы:
 getConnection - процедура подключения к бд.
 */
	dbConnect() throws ClassNotFoundException, SQLException {
    
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pool", "root", "0000");
    }
	
/*getCoach - функция получения записей из
  таблицы Тренеры.
  Формальный параметр:
  selectRequest - срока sql-запроса.
  Локальные пременные:
  res - ObservableList класса Coach;
  fio - переменная для фамилии тренера;
  sched - переменная для графика тренера;
  id - переменная для кода тренера;
  idGr - переменная для кода группы;
  statement - объект класса Statement;
  resultSet - объект класса ResultSet.
  Используемые подпрограммы:
  add - добавление элемента в ObservableList;
  getInt - получение значения целочисленного типа;
  getString - получения значения строкового типа.
  */
	public ObservableList<Coach> getCoach(String selectRequest) throws SQLException {
	        ObservableList<Coach> res = FXCollections.observableArrayList();
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(selectRequest);
	String fio,sched;
	Integer id, idGr;
	while(resultSet.next()) {                  //Цикл получение записей
		id=resultSet.getInt("id_coach");       //из запроса
		fio=resultSet.getString("FIO");
		sched=resultSet.getString("sched");
		idGr=resultSet.getInt("id_gr");
		res.add(new Coach(id, idGr, fio, sched));    //Добавление записи в ObservableList класса;
	}
	return res;
   }
	
	/*getSeasonTicket - функция получения записей из
	  таблицы Абонементы.
	  Формальный параметр:
	  selectRequest - срока sql-запроса.
	  Локальные пременные:
	  res - ObservableList класса SeasonTicket;
	  cate - переменная для категории абонемента;
	  id - переменная для кода абонемента;
	  numb - переменная для номера абонемента;
	  sOweek - переменная для посещаемости по абонементу;
	  cost - переменная для стоимости абонемента;
	  statement - объект класса Statement;
	  resultSet - объект класса ResultSet.
	  Используемые подпрограммы:
	  add - добавление элемента в ObservableList;
	  getInt - получение значения целочисленного типа;
	  getDouble - получения значения вещественного типа;
      getString - получения значения строкового типа.
	  */
	  public ObservableList<SeasonTicket> getSeasonTicket(String selectRequest) throws SQLException {
	        ObservableList<SeasonTicket> res = FXCollections.observableArrayList();
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(selectRequest);
	String cate;
	Integer id,numb,sOweek;
	Double cost;
	while(resultSet.next()) {                              //Цикл получения записей
		id=resultSet.getInt("id_season_ticket");           //из запроса
		numb=resultSet.getInt("numb_st");
		sOweek=resultSet.getInt("once_a_week");
		cost=resultSet.getDouble("cost");
		cate=resultSet.getString("category_st");   
		res.add(new SeasonTicket(id, numb, sOweek, cost, cate)); //Добавление записи в ObservableList класса;
	}
	return res;
 }
	  
		/*getgr - функция получения записей из
	  таблицы группы.
	  Формальный параметр:
	  selectRequest - срока sql-запроса.
	  Локальные пременные:
	  res - ObservableList класса gr;
	  cate - переменная для категории группы;
	  id - переменная для кода группы;
	  numb - переменная для номера группы;
	  statement - объект класса Statement;
	  resultSet - объект класса ResultSet.
	  Используемые подпрограммы:
	  add - добавление элемента в ObservableList;
	  getInt - получение значения целочисленного типа;
      getString - получения значения строкового типа.
	  */
	  public ObservableList<gr> getgr(String selectRequest) throws SQLException {
	        ObservableList<gr> res = FXCollections.observableArrayList();
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(selectRequest);
	String cate;
	Integer id,numb;
	while(resultSet.next()) {                          //Цикл получения записей
		id=resultSet.getInt("id_gr");                  //из запроса
		numb=resultSet.getInt("numb_gr");
		cate=resultSet.getString("category_gr");   
		res.add(new gr(id, numb, cate));              //Добавление записи в ObservableList класса;
		
	}
	return res;
}
	  
		/*getBass - функция получения записей из
	  таблицы Бассейны.
	  Формальный параметр:
	  selectRequest - срока sql-запроса.
	  Локальные пременные:
	  res - ObservableList класса Bass;
	  name - переменная для названия бассейна;
	  adress - переменная для адреса бассейна;
	  variety - переменная для разновидности бассейна;
	  idCoach - переменная для кода тренера;
	  idST - переменная для кода абонемента;
	  id - переменная для кода бассейна;
	  statement - объект класса Statement;
	  resultSet - объект класса ResultSet.
	  Используемые подпрограммы:
	  add - добавление элемента в ObservableList;
	  getInt - получение значения целочисленного типа;
      getString - получения значения строкового типа.
	  */
	  public ObservableList<Bass> getBass(String selectRequest) throws SQLException {
	        ObservableList<Bass> res = FXCollections.observableArrayList();
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(selectRequest);
	String name, adress, variety;
	Integer id, idCoach, idST;
	while(resultSet.next()) {                               //Цикл получения записей
		id=resultSet.getInt("id_pool");                     //из запроса
		name=resultSet.getString("name_pool"); 
		adress=resultSet.getString("adress");   
		variety=resultSet.getString("variety");   
		idCoach=resultSet.getInt("id_coach");
		idST=resultSet.getInt("id_season_ticket");
		res.add(new Bass(id, idCoach, idST, name, adress, variety)); //Добавление записи в ObservableList класса;
	}
	return res;
}
	  
		/*getId - функция получения кода записи
		  из таблицы.
	  Формальные параметры:
	  selectRequest - срока sql-запроса;
	  nameCol - название поля в БД;
	  Локальные пременные:
	  res - ObservableList целочисленного типа;
	  id - переменная для кода записи;
	  statement - объект класса Statement;
	  resultSet - объект класса ResultSet.
	  Используемые подпрограммы:
	  add - добавление элемента в ObservableList;
	  getInt - получение значения целочисленного типа.
	  */ 
	  public ObservableList<Integer> getId(String selectRequest, String nameCol) throws SQLException {
	        ObservableList<Integer> res = FXCollections.observableArrayList();
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(selectRequest);
	Integer id;
	while(resultSet.next()) {
		id=resultSet.getInt(nameCol);

		res.add(id);
	}
	return res;
}
	  
  /*getLog - функция получения логина
  из таблицы Пользователи.
  Формальны1 параметр:
  selectRequest - срока sql-запроса.
  Локальные пременные:
  res - ObservableList целочисленного типа;
  id - переменная для кода записи;
  statement - объект класса Statement;
  resultSet - объект класса ResultSet.
  Используемые подпрограммы:
  add - добавление элемента в ObservableList;
  getString - получение значения строкового типа.
  */ 
	  public ObservableList<String> getLog(String selectRequest) throws SQLException {
	        ObservableList<String> res = FXCollections.observableArrayList();
	Statement statement=connection.createStatement();
	ResultSet resultSet=statement.executeQuery(selectRequest);
	String login;
	while(resultSet.next()) {
		login=resultSet.getString("login_user");

		res.add(login);
	}
	return res;
}
	  
	  /*getConnection - функция возврата соединения.*/	  
	  public Connection getConnection() {
		  return connection;
	  }

/*
	*setGrDobav - процедура добавления записи в БД
	*в таблицу Группы.
	*Формальные параметры:
	*id - код группы;
	*numb - номер группы;
	*cate - категория группы.
	*Локальные переменные:
	*jframe - объект класса JFrame;
	*PreparedStatement - объект класса PreparedStatement.
	*Используемые подпрограммы:
	*setInt - процедура записи целочисленной переменной;
	*setString - процедура записи строковой переменной;
	*executeUpdate - процедура обновления данных в БД.
	*/  
public void setGrDobav(Integer id, Integer numb, String cate) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("insert into gr values(?,?,?)");
	PreparedStatement.setInt(1, id);
	PreparedStatement.setInt(2, numb);
	PreparedStatement.setString(3, cate);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Новая группа успешно добавлена");
   	
}
/*
*setStDobav - процедура добавления записи в БД
*в таблицу Абонементы.
*Формальные параметры:
*id - код абонемента;
*numb - номер абонемента;
*cate - категория абонемента;
*once_a_week - посещаемость по абонементу;
*cost - стоимость абонемента.
*Локальные переменные:
*jframe - объект класса JFrame;
*PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*setDouble - процедура записи вещественной переменной;
*executeUpdate - процедура обновления данных в БД.
*/  
public void setStDobav(Integer id, Integer numb, String cate, Integer once_a_week, Double cost) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("insert into season_ticket values(?,?,?,?,?)");
	PreparedStatement.setInt(1, id);
	PreparedStatement.setInt(2, numb);
	PreparedStatement.setString(3, cate);
	PreparedStatement.setInt(4, once_a_week);
	PreparedStatement.setDouble(5, cost);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Новый абонемент успешно добавлен");
   	
}
/*
*setUsers - процедура добавления записи в БД
*в таблицу Пользователи.
*Формальные параметры:
*login - логин пользователя;
*password - пароль пользователя;
*name - имя пользователя;
*lastname - фамилия пользователя;
*status - статус пользователя.
*Локальные переменные:
*jframe - объект класса JFrame;
*PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
*/  
public void setUsers(String login, String password, String name, String lastname, Integer status) throws SQLException {
	JFrame jframe= new JFrame();
	
	PreparedStatement PreparedStatement=connection.prepareStatement("insert into users values(?,?,?,?,?)");
	PreparedStatement.setString(1, login);
	PreparedStatement.setString(2, password);
	PreparedStatement.setString(3, name);
	PreparedStatement.setString(4, lastname);
	PreparedStatement.setInt(5, status);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Новый пользователь успешно добавлен");
   	
	
}
/*
*setCoachDobav - процедура добавления записи в БД
*в таблицу Тренеры.
*Формальные параметры:
*id - код тренера;
*fio - фио тренера;
*graf - график тренера;
*idGr - код группы.
*Локальные переменные:
*jframe - объект класса JFrame;
*PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
*/  
public void setCoachDobav(Integer id, String fio, String graf, Integer idGr) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("insert into coach values(?,?,?,?)");
	PreparedStatement.setInt(1, id);
	PreparedStatement.setString(2, fio);
	PreparedStatement.setString(3, graf);
	PreparedStatement.setInt(4, idGr);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Новый тренер успешно добавлен");
   	
	
}
/*
*setBassDobav - процедура добавления записи в БД
*в таблицу Бассейны.
*Формальные параметры:
*id - код бассейна;
*name - название бассейна;
*adress - адрес бассейна;
*vary - разновидность бассейна;
*idCoach - код тренера;
*idAbonement - код абонемента.
*Локальные переменные:
*jframe - объект класса JFrame;
*PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
*/  
public void setBassDobav(Integer id, String name, String adress, String vary, Integer idCoach, Integer idAbonement) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("insert into bass values(?,?,?,?,?,?)");
	PreparedStatement.setInt(1, id);
	PreparedStatement.setString(2, name);
	PreparedStatement.setString(3, adress);
	PreparedStatement.setString(4, vary);
	PreparedStatement.setInt(5, idCoach);
	PreparedStatement.setInt(6, idAbonement);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Новый бассейн успешно добавлен");
}
/*
*deleteString - процедура удаления выбранной
*записи из БД.
*Формальные параметры:
*id - код записи;
*table - название таблицы;
*id_table - название поля кода записи.
*Локальные переменные:
*jframe - объект класса JFrame;
*PreparedStatement - объект класса PreparedStatement.
*Используемая подпрограмма:
*executeUpdate - процедура обновления данных в БД.
*/  
public void deleteString(Integer id, String table, String id_table) {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement;
	try {
		PreparedStatement = connection.prepareStatement("delete from " + table + " where id_" + id_table + "=" + id);
		PreparedStatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(jframe, "Невозможно удалить запись, она связанна с другой таблицей");
	}
   	
}
/*
*deleteStringUser - процедура удаления выбранной
*записи из БД из таблицы Пользователи.
*Формальные параметры:
*login - логин пользователя.
*Локальные переменные:
*jframe - объект класса JFrame;
*PreparedStatement - объект класса PreparedStatement.
*Используемая подпрограмма:
*executeUpdate - процедура обновления данных в БД.
*/  
public void deleteStringUser(String login) {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement;
	try {
		PreparedStatement = connection.prepareStatement("delete from users where login_user='" + login + "'");
		PreparedStatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(jframe, "Невозможно удалить запись");
	}
   	
}
/*updateGr - процедура изменения записи
 * в таблице Группы.
 *Формальные параметры:
 *id - код группы;
 *numb - номер группы;
 *cate - категория группы.
 *Локальные переменные:
 *jframe - объект класса JFrame;
 *PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
 */
public void updateGr(Integer id, Integer numb, String cate) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("update gr set numb_gr=?, category_gr=? where id_gr=?");
	PreparedStatement.setInt(1, numb);
	PreparedStatement.setString(2, cate);
	PreparedStatement.setInt(3, id);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Запись успешно изменена!");
   	
	
}
/*updateCoach - процедура изменения записи
 * в таблице Тренеры.
 *Формальные параметры:
 *id - код тренера;
 *fio - фио тренера;
 *graf - график тренера;
 *idGr - код группы.
 *Локальные переменные:
 *jframe - объект класса JFrame;
 *PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
 */
public void updateCoach(Integer id, String fio, String graf, Integer idGr) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("update coach set FIO=?, sched=?, id_gr=? where id_coach=?");
	PreparedStatement.setString(1, fio);
	PreparedStatement.setString(2, graf);
	PreparedStatement.setInt(3, idGr);
	PreparedStatement.setInt(4, id);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Запись успешно изменена!");
   	
	
}
/*updateBass - процедура изменения записи
 * в таблице Бассейны.
 *Формальные параметры:
 *id - код бассена;
 *name - название бассейна;
 *adress - адрес бассейна;
 *vary - разновиднось бассейна;
 *idCoach - код тренера;
 *idAbonement - код абонемента.
 *Локальные переменные:
 *jframe - объект класса JFrame;
 *PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
 */
public void updateBass(Integer id, String name, String adress, String vary, Integer idCoach, Integer idAbonement) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("update bass set name_pool=?, adress=?, variety=?, id_coach=?, id_season_ticket=? where id_pool=?");
	PreparedStatement.setString(1, name);
	PreparedStatement.setString(2, adress);
	PreparedStatement.setString(3, vary);
	PreparedStatement.setInt(4, idCoach);
	PreparedStatement.setInt(5, idAbonement);
	PreparedStatement.setInt(6, id);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Запись успешно изменена!");
   	
	
}
/*updateSt - процедура изменения записи
 * в таблице Абонементы.
 *Формальные параметры:
 *id - код абонемента;
 *numb - номер абонемента;
 *cate - категория абонемента;
 *once_a_week - посещаемость по абонементу;
 *cost - стоимость абонемента.
 *Локальные переменные:
 *jframe - объект класса JFrame;
 *PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
 */
public void updateSt(Integer id, Integer numb, String cate, Integer once_a_week, Double cost) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("update season_ticket set numb_st=?, category_st=?, once_a_week=?, cost=? where id_season_ticket=?");
	PreparedStatement.setInt(1, numb);
	PreparedStatement.setString(2, cate);
	PreparedStatement.setInt(3, once_a_week);
	PreparedStatement.setDouble(4, cost);
	PreparedStatement.setInt(5, id);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Запись успешно изменена!");
}
/*updateUsers - процедура изменения записи
 * в таблице Пользователи.
 *Формальные параметры:
 *login - логин пользователя;
 *password - пароль пользователя;
 *status - статус пользователя.
 *Локальные переменные:
 *jframe - объект класса JFrame;
 *PreparedStatement - объект класса PreparedStatement.
*Используемые подпрограммы:
*setInt - процедура записи целочисленной переменной;
*setString - процедура записи строковой переменной;
*executeUpdate - процедура обновления данных в БД.
 */
public void updateUsers(String login, String password, Integer status) throws SQLException {
	JFrame jframe= new JFrame();
	PreparedStatement PreparedStatement=connection.prepareStatement("update users set password_user=?, status_user=? where login_user=?");
	PreparedStatement.setString(1, password);
	PreparedStatement.setInt(2, status);
	PreparedStatement.setString(3, login);
	PreparedStatement.executeUpdate();
   	JOptionPane.showMessageDialog(jframe, "Запись успешно изменена!");
}
/*
 * getUsers - функция получения записей из БД
 * из таблицы пользователи.
 * Формальный параметр:
 * selectRequest - строка запроса.
 * Локальные переменные:
 * statement - объект класса Statement;
 * resultSet - объект класса ResultSet;
 * login - логин пользователя;
 * password - пароль пользователя;
 * name - имя пользователя;
 * lastname - фамилия пользователя;
 * res - ObservableList класса users;
 * status - статус пользователя.
 * Используемые подпрограммы:
 * add - процедура добавления записи в ObservableList;
 * getString - получение данных строкового типа;
 * getInt - получение данных целочисленного типа.
 */
public ObservableList<users> getUsers(String selectRequest) throws SQLException {
    ObservableList<users> res = FXCollections.observableArrayList();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery(selectRequest);
String login, password, name, lastname;
Integer status;
while(resultSet.next()) {
login=resultSet.getString("login_user");
password=resultSet.getString("password_user");
name=resultSet.getString("name_user");
lastname=resultSet.getString("lastname_user");
status=resultSet.getInt("status_user");   
res.add(new users(login, password, name, lastname, status));
}
return res;
}
/*
 * getString - функция получения поля из БД
 * из указанной таблицы.
 * Формальные параметры:
 * selectRequest - строка запроса;
 * nameCol - названия поля.
 * Локальные переменные:
 * res - ObservableList строкового типа;
 * field - переменная из поля.
 * Используемые подпрограммы:
 * add - процедура добавления записи в ObservableList;
 * getString - получение данных строкового типа.
 */
public ObservableList<String> getString(String selectRequest, String nameCol) throws SQLException {
    ObservableList<String> res = FXCollections.observableArrayList();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery(selectRequest);
String field;
while(resultSet.next()) {
field=resultSet.getString(nameCol);

res.add(field);
}
return res;
}

/*
 * zapros - функция получения полей из БД
 * для вывода второго запроса.
 * Формальный параметр:
 * selectRequest - строка запроса.
 * Локальные переменные:
 * res - ObservableList класса zapros;
 * fio - фио тренера;
 * name - название бассейна;
 * zp - доход тренера;
 * field - переменная из поля.
 * Используемые подпрограммы:
 * add - процедура добавления записи в ObservableList;
 * getString - получение данных строкового типа.
 */
public ObservableList<zapros> zapros(String selectRequest) throws SQLException {
    ObservableList<zapros> res = FXCollections.observableArrayList();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery(selectRequest);
String fio, name;
Double zp;
while(resultSet.next()) {
fio=resultSet.getString(1);
zp=resultSet.getDouble(2);
name=resultSet.getString(3);   
res.add(new zapros(zp, name, fio));

}
return res;
}
}
