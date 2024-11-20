    /*
    updateTableController - контроллер для управления формой изменения таблиц.
    Используемые подпрограммы:
    on_click_insert - процедура изменения записи в БД по нажатию кнопки;
    on_click_back - процедура закрытия формы добавления по нажатию конпки.
    */
package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.Connection;

import java.sql.SQLException;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;

public class updateTableController{
	
    @FXML
    private Tab tabUsers;
    @FXML
    private TextField UpassField; //Поле для ввода пароля пользователя
    @FXML
    private TextField UstatusField; //Поле для ввода статуса пользователя
    @FXML
    private ComboBox<String> box_login;
	@FXML
	private Button updateButton; 
	@FXML
	private Button backButton;
	@FXML
	private Tab tabAbonement;
	@FXML
	private TextField numAbonem; //Поле для номера абонемента
	@FXML
	private TextField cateAbonem; //Поле для категории абонемента
	@FXML
	private TextField onceAbonem; //Поле для количества посещений
	@FXML
	private TextField costAbonem; //Поле для стоимости абонемента
	@FXML
	private ComboBox<Integer> box_idAbonement;
	@FXML
	private Tab tabPool;
	@FXML
	private TextField nameFieldPool; 
	@FXML
	private TextField adressFieldPool;
	@FXML
	private TextField varyFieldPool; //Поле для разновидности бассейна
	@FXML
	private ComboBox<Integer> box_idCoach_Pool;
	@FXML
	private ComboBox<Integer> box_idAbonementPool;
	@FXML
	private ComboBox<Integer> box_idPool;
	@FXML
	private Tab tabCoach;
	@FXML
	private ComboBox<Integer> box_idGrCoach;
	@FXML
	private TextField fioCoach; //Поле для ФИО тренера
	@FXML
	private TextField grCoach; //Поле для графика тренера
	@FXML
	private ComboBox<Integer> box_idCoach;
	@FXML
	private Tab tabGr;
	@FXML
	private TextField numGr; //Поле для номера группы
	@FXML
	private TextField cateGr; //Поле для категории группы
	@FXML
	private ComboBox<Integer> box_idGr; 
	
    /*
    on_click_insert - процедура изменения записи по кнопки "Изменить".
    Запись изменяется в той таблице, вкладка которой открыта.
    Локальные переменные:
    id - код выбранной записи;
    numb - номер выбранной записи;
    cate - категория выбранной записи;
    fio - ФИО тренера;
    graf - график тренера;
    id_gr - код группы;
    idCoach - код тренера;
    idAbonem - код абонемента;
    name - название бассейна;
    adress - адрес бассейна;
    vary - разновидность бассейна;
    once - поле пасещений абонемента;
    cost - стоимость абонемента;
    password - пароль пользователя;
    login - логин пользователя;
    status - Статус пользователя;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    parseInt - процедура преобразования строки в число;
    getText - процедура получения строки из текстового поля;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    hide - процедура скрытия текущего окна;
    updateGr - процедура изменения записи в таблице Группы;
    updateCoach - процедура изменения записи в таблице Тренеры;
    updateBass - процедура изменения записи в таблице Бассейны;
    updateSt - процедура изменения записи в таблице Абонементы;
    updateUsers - процедура изменения записи в таблице Пользователи.
    */
	@FXML
	public void on_click_insert(ActionEvent event) throws SQLException, ClassNotFoundException {
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();	
		
		Integer id;
    if(tabGr.isSelected()) {
        id=box_idGr.getSelectionModel().getSelectedItem();
        Integer numb=Integer.parseInt(numGr.getText());
        String cate=cateGr.getText();
    	connector.updateGr(id, numb, cate);
   	}
  	else if(tabCoach.isSelected()) {
   		 id=box_idCoach.getSelectionModel().getSelectedItem();
   		 String fio=fioCoach.getText();
   		 String graf=grCoach.getText();
   		 Integer id_gr=box_idGrCoach.getSelectionModel().getSelectedItem();
   		 connector.updateCoach(id, fio, graf, id_gr);
   	}
  	else if(tabPool.isSelected()) {
   	     id=box_idPool.getSelectionModel().getSelectedItem();
   		 Integer idCoach=box_idCoach_Pool.getSelectionModel().getSelectedItem();
   		 Integer idAbonem=box_idAbonementPool.getSelectionModel().getSelectedItem();
   	     String name=nameFieldPool.getText();
   		 String adress=adressFieldPool.getText();
   		 String vary=varyFieldPool.getText();
   	     connector.updateBass(id, name, adress, vary, idCoach, idAbonem);
   	}
  	else if(tabAbonement.isSelected()) {
   		 id=box_idAbonement.getSelectionModel().getSelectedItem();
   		 Integer numb=Integer.parseInt(numAbonem.getText());
   		 String cate=cateAbonem.getText();
   		 Integer once=Integer.parseInt(onceAbonem.getText());
   		 Double cost=Double.parseDouble(costAbonem.getText());
   		 connector.updateSt(id, numb, cate, once, cost);
   	}
  	else if(tabUsers.isSelected()) {
  		Integer status=Integer.parseInt(UstatusField.getText());
  		String password=UpassField.getText();
  		String login=box_login.getSelectionModel().getSelectedItem();
  		connector.updateUsers(login, password, status);
  	}
    	updateButton.getScene().getWindow().hide();
	}

	/*
    on_click_back - процедура закрытия формы добавления по нажатию кнопки "Назад".
    Используемые подпрограммы:
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    hide - процедура скрытия текущего окна.
    */
	@FXML
	public void on_click_back(ActionEvent event) {
		backButton.getScene().getWindow().hide();
	} 

    Connection connection;
    /*initialize - процедура, выполняющаяся при инициализации формы.
   Для данной формы производится подключение к бд и заполнение
   списков уникальными кодами полей таблиц, в соответствии с
   названиями, путем выполнения sql-запроса.
   Локальные переменные:
   connector - объект класса dbConnect;
   Используемые подпрограммы:
   getId - процедура получения кода выбранной записи;
   getLog - процедура получения логина выбранного пользователя;
   setItems - процедура добавления элементов в список.
   */
	public void initialize() throws ClassNotFoundException, SQLException {
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();	
		box_idCoach.setItems(connector.getId("select id_coach from coach","id_coach"));
		box_idAbonement.setItems(connector.getId("select id_season_ticket from season_ticket","id_season_ticket"));
		box_idAbonementPool.setItems(connector.getId("select id_season_ticket from season_ticket","id_season_ticket"));
		box_idGr.setItems(connector.getId("select id_gr from gr","id_gr"));
		box_idGrCoach.setItems(connector.getId("select id_gr from gr","id_gr"));
		box_idPool.setItems(connector.getId("select id_pool from bass","id_pool"));
		box_idCoach_Pool.setItems(connector.getId("select id_pool from bass","id_pool"));
		box_login.setItems(connector.getLog("select login_user from users"));
	}
}
