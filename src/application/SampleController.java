 /*
    SampleController - контроллер для управления формой авторизации.
    Используемые подпрограммы:
    on_click_enry - процедура входа на главную форму приложения по нажатию кнопки;
    on_click_reg - процедура перехода на форму регистрации по нажатию кнопки.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


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


public class SampleController implements Initializable{

    @FXML
    private Button enry_button; //Кнопка входа в приложение

    @FXML
    private TextField loginField; //Текстовое поле для логина

    @FXML
    private PasswordField passwordField; //Текстовое поле для пароля

    @FXML
    private Button reg_Button; //Кнопка перехода к форме регистрации

/*downloadWindow - процедура загрузки нового окна.
 Формальный параметр:
 form - название формы, которую нужно открыть.
 Локальные переменные:
 loader - объект класса FXMLLoader;
 root - объект класса Parent; 
 stage - объект класса Stage.
 */
     void downloadWindow(String form) {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource(form));
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
   
    /*
    on_click_enry - процедура перехода на главную форму по нпжатию на кнопку "Вход".
    Локальные переменные:
    login_us - переменная, хранящая логин, полученный из loginField;
    password_us - переменная, хранящая пароль, полученный из passwordField;
    f - переменная-флаг;
    resultSet - объект класса ResultSet;
    preparedStatement - объект класса PreparedStatement;
    connection - объект класса Connection;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    getString - процедура получения строки из resultSet;
    equals - процедура сравнения строк;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    */
    @FXML
    void on_click_enry(ActionEvent event) {

    	JFrame jframe= new JFrame();
    	String login_us=loginField.getText();              //Получение данных из полей
    	String password_us=passwordField.getText();
    	Integer f=0;
    	Boolean status=false;
    	
    	 
    	try {
			
    		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pool", "root", "0000");//Establishing connection
			System.out.println("Подключение к базе данных успешно");
			PreparedStatement preparedStatement=connection.prepareStatement("select login_user, password_user, status_user from users");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {                                              //Проверка соответствия
				if(resultSet.getString("login_user").equals(login_us) &&           //логина и пароля
	            		 resultSet.getString("password_user").equals(password_us)) {
				   f=1;
		             if (resultSet.getBoolean("status_user")) {                    //Проверка статуса пользователя
		            	 status=true;
		             }
	             }

			}
			
    	} catch (SQLException e) {
    	   	JOptionPane.showMessageDialog(jframe, "Ошибка подключения к базе данных");
    	}
    	
    	if(f!=0) {
    		enry_button.getScene().getWindow().hide();
    		if (status) {
            downloadWindow("/application/app.fxml");                   //Переход к главной форме администратора
    		}
    		else {
    		downloadWindow("/application/userApp.fxml");            //Переход к главной форме пользователя
    		}
    	}
    	else {
        	JOptionPane.showMessageDialog(jframe, "Пользователь не найден");
        }
    }
    /*
    on_click_reg - процедура перехода на главную форму по нпжатию на кнопку "Вход".
    Используемые подпрограммы:
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    downloadWindow - процедура-загрузчик нового окна.
    */
    @FXML
    void on_click_reg(ActionEvent event) {                            //Переход к форме регистрации
    	reg_Button.getScene().getWindow().hide();   
    	downloadWindow("/application/regUp.fxml");
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}