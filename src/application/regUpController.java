 /*
    regUpController - контроллер для управления формой регистрации.
    Используемые подпрограммы:
    on_click_createButton - процедура изменения записи в БД по нажатию кнопки;
    initialize - процедура выполняющаяся при инициализации формы.
    */
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class regUpController implements Initializable {

    @FXML
    private Button createButton;   //Кнопка создания пользователя

    @FXML
    private TextField lastnameField; //Текстовое поле для фамилии

    @FXML
    private TextField loginField;  //Текстовое поле для логина

    @FXML
    private TextField nameField;  //Текстовое поле для имени

    @FXML
    private PasswordField passwordField;  //Текстовое поле для пароля

    private static final String COMPLEX_PASSWORD_REGEX ="^(?=.*[0-9])"
    		+ "(?=.*[a-z])(?=.*[A-Z])"
    		+ "(?=.*[@#$%^&-+=()])"
    		+ "(?=\\S+$).{4,16}$";     //Строка регулярного выражения
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(COMPLEX_PASSWORD_REGEX);  //Паттерн для пароля
    
    /*on_click_createButton - процедура регистрации пользователя,
      выполняющаяся по нажатию кнопки.
   Локальные переменные:
   name_us - имя пользователя, полученное из текстового поля;
   lastname_us - фамилия пользователя, полученная из текстового поля;
   login_us - логин пользователя, полученный из текстового поля;
   password_us - пароль пользователя, полученный из текстового поля;
   jframe - объект класса JFrame;
   preparedStatement - объект класса PreparedStatement;
   loader - объект класса FXMLLoader;
   root - объект класса Parent;
   stage - объект класса Stage;
   connector - объект класса dbConnect.
   Используемые подпрограммы:
   setString - процедура получения строки;
   setScene - процедура получения сцены;
   */
    @FXML
    void on_click_createButton(ActionEvent event) {
    	String name_us = nameField.getText();                //Получение данных из полей
    	String lastname_us = lastnameField.getText();
    	String login_us = loginField.getText();
    	String password_us = passwordField.getText();
		JFrame jframe= new JFrame();
       
		if (PASSWORD_PATTERN.matcher(password_us).matches()) {    //Проверка пароля регулярным выражением
    	try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pool", "root", "0000");//Establishing connection
			System.out.println("Подключение к базе данных успешно");
			PreparedStatement preparedStatement=connection.prepareStatement("insert into users values(?,?,?,?,?)");
	
			preparedStatement.setString(1,login_us);                      //Добавление данных
	    	preparedStatement.setString(2,password_us);                   //о пользователе в БД
	    	preparedStatement.setString(3, name_us);
	    	preparedStatement.setString(4, lastname_us);
	    	preparedStatement.setBoolean(5, false);
    		preparedStatement.executeUpdate();
    		
    	   	JOptionPane.showMessageDialog(jframe, "Данные успешно записаны");
    	} catch (SQLException e) {
    	   	JOptionPane.showMessageDialog(jframe, "Ошибка подключения к базе данных");
    	}
    	
    	createButton.getScene().getWindow().hide();                  //Закрытие текущего
   		FXMLLoader loader = new FXMLLoader();                        //и открытие нового окна
		loader.setLocation(getClass().getResource("/application/Sample.fxml"));
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
        }
        else {
        	JOptionPane.showMessageDialog(jframe, "Введите корректные данные");
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

