    /*
    dobavCoachController - контроллер для управления формой добавления тренера.
    Используемые подпрограммы:
    on_click_insert - процедура добавления записи в БД по нажатию кнопки;
    on_click_back - процедура закрытия формы добавления по нажатию конпки.
    */
package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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



public class dobavCoachController{
	@FXML
	private TextField kodField; //Текстовое поле для кода тренера
	@FXML
	private Button insertButton; //Кнопка добавления тренера
	@FXML
	private Button backButton;  //Кнопка закрытия текущей формы
	@FXML
	private TextField fioField; //Текстовое поле для ФИО тренера
	@FXML
	private TextField grafField; //Текстовое поле для графика тренера
	@FXML
    private ComboBox<Integer> box_id; //ComboBox для выбора кода группы
	
	private ObservableList<Integer> idGr_list =FXCollections.observableArrayList();

    Connection connection;
    /*
    on_click_insert - процедура добавления записи по кнопки "Добавить".
    Локальные переменные:
    id - переменная, хранящая код тренера, полученный из текстового поля;
    fio - переменная, хранящая фио тренера, полученное из текстового поля;
    graf - переменная, хранящая график тренера, полученный из текстового поля;
    id_gr - переменная, хранящая код группы, полученный из ComboBox;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    parseInt - процедура преобразования строки в число;
    getText - процедура получения строки из текстового поля;
    isEmpty - функция, возвращающая true, если строка пустая;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    setCoachDobav - добавления записи в таблицу Тренеры;
    hide - процедура скрытия текущего окна.
    */
	@FXML
	public void on_click_insert(ActionEvent event) throws ClassNotFoundException, SQLException {
		JFrame jframe= new JFrame();
		if((kodField.getText().isEmpty())                        //Условие заполненности полей
				|| (fioField.getText().isEmpty()) 
				|| (grafField.getText().isEmpty())
				|| (box_id.getValue()==0)){
			JOptionPane.showMessageDialog(jframe, "Введите корректные данные");
		}
		else {
			Integer id=Integer.parseInt(kodField.getText());      //Получение данных из
			String fio=fioField.getText();                        //полей формы
			String graf=grafField.getText();
			Integer id_gr=box_id.getValue();
	    	dbConnect connector=new dbConnect();
			connection = connector.getConnection();	
			connector.setCoachDobav(id, fio, graf, id_gr);
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
	
    /*initialize - процедура, выполняющаяся при инициализации формы.
   Для данной формы производится подключение к бд и заполнение
   списка кодами групп.
   Локальные переменные:
   i - переменная счетчик списка;
   connector - объект класса dbConnect;
   preparedStatement - объект класса PreparedStatement;
   resultSet - объект класса ResultSet.
   Используемые подпрограммы:
   getInt - процедура преобразования значения в тип Int;
   setItems - процедура добавления элементов в список.
   */
	public void initialize() throws ClassNotFoundException, SQLException {
    	Integer i=0;
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();	
		PreparedStatement preparedStatement=connection.prepareStatement("select id_gr from gr");
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {                                      //Прохождение по элементам запроса
			idGr_list.add(i, resultSet.getInt("id_gr"));;              //из таблицы Группы
		i=i+1;
		}
			box_id.setItems(idGr_list);
	}
	
}
