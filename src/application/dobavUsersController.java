    /*
    dobavUsersController - контроллер для управления формой добавления Пользователя.
    Используемые подпрограммы:
    on_click_insert - процедура добавления записи в БД по нажатию кнопки;
    on_click_back - процедура закрытия формы добавления по нажатию конпки.
    */
package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

public class dobavUsersController {
	@FXML
	private TextField loginField;  //Текстовое поле для логина
	@FXML
	private Button insertButton;   
	@FXML
	private Button backButton;     
	@FXML
	private TextField passField;   //Текстовое поле для пароля 
	@FXML
	private TextField nameField;   //Текстовое поле для имени
	@FXML
	private TextField lastnameField;  //Текстовое поле для фамилии
	@FXML
	private TextField statusField;  //Текстовое поле для статуса
    private static final String COMPLEX_PASSWORD_REGEX ="^(?=.*[0-9])" 
    		+ "(?=.*[a-z])(?=.*[A-Z])"
    		+ "(?=.*[@#$%^&-+=()])"
    		+ "(?=\\S+$).{4,16}$";                                        //Строка регулярного выражения
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(COMPLEX_PASSWORD_REGEX); //Паттерн для пароля
    
	Connection connection;
	
    /*
    on_click_insert - процедура добавления записи по кнопки "Добавить".
    Локальные переменные:
    login - переменная, хранящая логин пользователя, полученный из текстового поля;
    password - переменная, хранящая пароль пользователя, полученный из текстового поля;
    name - переменная, хранящая имя пользователя, полученное из текстового поля;
    lastname - переменная, хранящая фамилию пользователя, полученную из текстового поля;
    status - переменная, хранящая статус пользователя, полученный из текстового поля;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    parseInt - процедура преобразования строки в число;
    getText - процедура получения строки из текстового поля;
    isEmpty - функция, возвращающая true, если строка пустая;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    setUsers - добавления записи в таблицу Пользователи;
    hide - процедура скрытия текущего окна.
    */
	@FXML
	public void on_click_insert(ActionEvent event) throws ClassNotFoundException, SQLException {
		JFrame jframe= new JFrame();
		if((loginField.getText().isEmpty())                            //Условие заполнености полей
				|| (passField.getText().isEmpty()) 
				|| (nameField.getText().isEmpty()) 
				|| (lastnameField.getText().isEmpty())
				|| (statusField.getText().isEmpty())){
			JOptionPane.showMessageDialog(jframe, "Введите корректные данные");
		}
		else {
			String login=loginField.getText();
			String password=passField.getText();                       //Полученние данных из
			String name=nameField.getText();                           //полей формы
			String lastname=lastnameField.getText();
			Integer status=Integer.parseInt(statusField.getText());
	    if(PASSWORD_PATTERN.matcher(password).matches()) {
			dbConnect connector=new dbConnect();
			connection = connector.getConnection();	
			connector.setUsers(login, password, name, lastname, status);
			insertButton.getScene().getWindow().hide();
		}
	    else {
			JOptionPane.showMessageDialog(jframe, "Пароль должен иметь длину от 4 до 16 символов,"
					+ "содержать буквы верхнего и нижнего регистра, "
					+ "а так же специальные знаки!");
	    }
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
}
