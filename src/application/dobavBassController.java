    /*
    dobavBassController - контроллер для управления формой добавления бассейна.
    Используемые подпрограммы:
    on_click_insert - процедура добавления записи в БД по нажатию кнопки;
    on_click_back - процедура закрытия формы добавления по нажатию конпки;
    initialize - метод выполняющийся при открытии формы.
    */
package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class dobavBassController {
	@FXML
	private TextField kodField; //Текстовое поле для кода бассейна
	@FXML
	private Button insertButton; 
	@FXML
	private Button backButton;
	@FXML
	private TextField nameField; //Текстовое поле для названия бассейна
	@FXML
	private TextField adressField; //Текстовое поле для адреса бассейна
	@FXML
	private ComboBox<Integer> box_idCoach; //ComboBox для кодом тренеров
	@FXML
	private TextField varyField;   //Текстовое поле для разновидности бассейна
	@FXML
	private ComboBox<Integer> box_idAbonement;  //ComboBox для кодов абонементов
	private ObservableList<Integer> idCoach_list =FXCollections.observableArrayList();
	private ObservableList<Integer> idAb_list =FXCollections.observableArrayList();
	Connection connection;
	@FXML
	
    /*
    on_click_insert - процедура добавления записи по кнопки "Добавить".
    Локальные переменные:
    id - переменная, хранящая код бассейна, полученный из текстового поля;
    name - переменная, хранящая название бассейна, полученное из текстового поля;
    adress - переменная, хранящая адрес бассейна, полученный из текстового поля;
    vary - переменная, хранящая разновидность бассейна, полученную из текстового поля;
    idCoach - переменная, хранящая код тренера, полученный из ComboBox;
    idAbonement - переменная, хранящая код абонемента, полученный из ComboBox;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    parseInt - процедура преобразования строки в число;
    getText - процедура получения строки из текстового поля;
    isEmpty - функция, возвращающая true, если строка пустая;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    setBassDobav - добавления записи в таблицу Бассейны;
    hide - процедура скрытия текущего окна.
    */
	public void on_click_insert(ActionEvent event) throws SQLException, ClassNotFoundException {
		JFrame jframe= new JFrame();
		if((kodField.getText().isEmpty()) 
				|| (nameField.getText().isEmpty()) 
				|| (adressField.getText().isEmpty())                    //Условие заполненности полей
				|| (box_idCoach.getValue()==0)
				|| (box_idAbonement.getValue()==0)
				|| (varyField.getText().isEmpty())){
			JOptionPane.showMessageDialog(jframe, "Введите корректные данные");
		}
		else {
			Integer id=Integer.parseInt(kodField.getText());            //Получение данных из
			String name=nameField.getText();                            //полей формы
			String adress=adressField.getText();
			String vary=varyField.getText();
			Integer idCoach=box_idCoach.getValue();
			Integer idAbonement=box_idAbonement.getValue();
	    	dbConnect connector=new dbConnect();
			connection = connector.getConnection();	
			connector.setBassDobav(id, name, adress, vary, idCoach, idAbonement);
			insertButton.getScene().getWindow().hide();
		}
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

    /*initialize - метод, выполняющийся при инициализации формы.
   Для данной формы производится подключение к бд и заполнение
   списков уникальными кодами полей таблиц, в соответствии с
   названиями, путем выполнения sql-запроса.
   Локальные переменные:
   connector - объект класса dbConnect;
   preparedStatement - объект класса PreparedStatement;
   preparedStatement2 - объект класса PreparedStatement;
   resultSet - объект класса ResultSet;
   resultSet2 - объект класса ResultSet;
   idCoach_list - список, содержащий коды тренеров;
   idAb_list - список, содержащий коды абонементов;
   i - счетчик записей списков.
   Используемые подпрограммы:
   add - процедура добавления элемента в список;
   setItems - процедура добавления элементов в список.
   */
	public void initialize() throws ClassNotFoundException, SQLException {
    	Integer i=0;
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();	
		PreparedStatement preparedStatement=connection.prepareStatement("select id_coach from coach");
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {                                      //Прохождение по элементам запроса
			idCoach_list.add(i, resultSet.getInt("id_coach"));;        //из таблицы Тренеры
		i=i+1;
		}
		box_idCoach.setItems(idCoach_list);
		
	   	i=0;

		PreparedStatement preparedStatement2=connection.prepareStatement("select id_season_ticket from season_ticket");
		ResultSet resultSet2=preparedStatement2.executeQuery();
		while(resultSet2.next()) {                                      //Прохождение по элементам запроса
			idAb_list.add(i, resultSet2.getInt("id_season_ticket"));;   //из таблицы Абонементы
		i=i+1;
		}
		box_idAbonement.setItems(idAb_list);
			
			
	}
}
