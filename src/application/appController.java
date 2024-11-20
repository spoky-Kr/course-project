/*
    appController - контроллер для управления формой администратора.
    Используемые подпрограммы:
    on_click_zapros - процедура перехода к форме запросов по нажатию кнопки;
    downloadWindow - процедура загрузки нового окна;
    on_click_delete - процедура удаления выбранной записи;
    on_click_insert - процедура добавления записи;
    on_click_update - процедура открытия формы изменения записей;
    on_click_view - процедура обновления записей в tableView;
    initialize - процедура, выполняющаяся при инициализации формы.
    
 */
package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;




public class appController{

    @FXML
    private Button deleteButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button viewButton;
    @FXML
    private Button updateButton;
    
    @FXML
    private Tab tableCoach;
    @FXML
    private Tab tableGrup;
    @FXML
    private Tab tablePool;
    @FXML
    private Tab tableST;
    @FXML
    private Label userLable;

    @FXML
    private TableView<Coach> viewCoach;
    @FXML
    private TableColumn<Coach, Integer> id_coach;
    @FXML
    private TableColumn<Coach, String> fio_coach;
    @FXML
    private TableColumn<Coach, String> graf_coach;
    
    @FXML
    private TableView<gr> viewGr;
    @FXML
    private TableColumn<gr, Integer> id_gr;
    @FXML
    private TableColumn<gr, Integer> num_gr;
    @FXML
    private TableColumn<gr, String> cate_gr;

    @FXML
    private TableView<Bass> viewPool;
    @FXML
    private TableColumn<Bass, String> name_pool;
    @FXML
    private TableColumn<Bass, Integer> id_pool;
    @FXML
    private TableColumn<Bass, String> vPool;
    @FXML
    private TableColumn<Bass, String> adress_pool;

    @FXML
    private TableView<SeasonTicket> viewST;
    @FXML
    private TableColumn<SeasonTicket, Integer> id_ST;
    @FXML
    private TableColumn<SeasonTicket, Integer> num_ST;
    @FXML
    private TableColumn<SeasonTicket, Integer> ofWeek;
    @FXML
    private TableColumn<SeasonTicket, String> cate_ST;
    @FXML
    private TableColumn<SeasonTicket, Double> cost;
    
    @FXML
    private TableColumn<Bass, Integer> id_STFK;
    @FXML
    private TableColumn<Coach, Integer> id_grFK;
    @FXML
    private TableColumn<Bass, Integer> id_coachFK;
    @FXML
    private Tab tableUsers;
    @FXML
    private TableView<users> viewUsers;

    @FXML
    private Button zaprosButton;
    @FXML
    private TableColumn<users, String> UfamCol;
    @FXML
    private TableColumn<users, String> UloginCol;
    @FXML
    private TableColumn<users, String> UnameCol;
    @FXML
    private TableColumn<users, String> UpassCol;
    @FXML
    private TableColumn<users, String> UstatusCol;

    
    /*on_click_zapros - процедура перехода к форме запросов по нажатию кнопки.
     Локальные переменные:
     loader - объект класса FXMLLoader;
     root - объект класса Parent; 
     stage - объект класса Stage.
     */
    @FXML
    void on_click_zapros(ActionEvent event) {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/application/sqlzapros.fxml"));
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
	    stage.setScene(new Scene(root,347,410));
	    stage.show();
    }
    /*on_click_delete - процедура удаления выбранной записи.
     Запись удаляется из той таблицы, вкладка которой выбрана.
    Локальные переменные:
    select_id - переменная для выбранного id;
    select_login - переменная для выбранного логина;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    deleteString() - процедура удаления строки по id;
    getSelectionModel() - процедура получения выбранной модели;
    getSelectedItem() - процедура получения выбранного элемента; 
    getId() - процедура получения id;
    getLogin() - процедура получения логина;
    on_click_view(event) - процедура обновления таблиц.
    */
	@FXML
    void on_click_delete(ActionEvent event) throws ClassNotFoundException, SQLException {
    	dbConnect connector=new dbConnect();
		connection = connector.getConnection();
    	Integer select_id;
    	String select_login;
    	JFrame jframe=new JFrame();
    	                                      //Проверка открытой таблицы
    	try {                                 //и удаление из нее записи
    	if(tableCoach.isSelected()) {
    		 select_id=viewCoach.getSelectionModel().getSelectedItem().getId();
    		 connector.deleteString(select_id, "coach", "coach");
    	}
    	else if(tableGrup.isSelected()) {
    		 select_id=viewGr.getSelectionModel().getSelectedItem().getId();
    		 connector.deleteString(select_id, "gr", "gr");
    	}
    	else if(tablePool.isSelected()) {
    		 select_id=viewPool.getSelectionModel().getSelectedItem().getId();
    		 connector.deleteString(select_id, "bass", "pool");
    	}
    	else if(tableST.isSelected()) {
    		 select_id=viewST.getSelectionModel().getSelectedItem().getId();
    		 connector.deleteString(select_id, "season_ticket", "season_ticket");
    	}
    	else if(tableUsers.isSelected()) {
   		 select_login=viewUsers.getSelectionModel().getSelectedItem().getLogin();
   		 connector.deleteStringUser(select_login);
    	}
    	} catch(Exception e) {
    		JOptionPane.showMessageDialog(jframe, "Выберите запись для удаления!");
    	}
    	on_click_view(event);
   
    }

    /*on_click_insert - процедура добавления записи.
    Запись добавляется в ту таблицу, вкладка которой выбрана.
   Используемая подпрограмма:
   downloadWindow - процедура загрузки нового окна.
   */
	@FXML
    void on_click_insert(ActionEvent event) throws ClassNotFoundException, SQLException {
    	if(tableGrup.isSelected()) {  
		    downloadWindow("/application/dobavGr.fxml");  //Проверка открытой таблицы 
    	}                                                 //и открытие формы для добавления
    	else if(tableCoach.isSelected()){
    		downloadWindow("/application/dobavCoach.fxml");
    	}
        else if(tablePool.isSelected()){
        	downloadWindow("/application/dobavBass.fxml");
    	}
        else if(tableST.isSelected()){
        	downloadWindow("/application/dobavST.fxml");
    	}
        else if(tableUsers.isSelected()) {
        	downloadWindow("/application/dobavUsers.fxml");
        }
    }
    /*on_click_update - процедура открытия формы изменения записей.
   Используемая подпрограмма:
   downloadWindow - процедура загрузки нового окна.
   */
    @FXML
    void on_click_update(ActionEvent event) {
    	downloadWindow("/application/updateTable.fxml");
    	
    }
    Connection connection;
    /*on_click_view - процедура обновления записей в tableView.
   Записи обновляются в той таблице, вкладка которой выбрана.
   Локальные переменные:
   connector - объект класса dbConnect.
   Используемые подпрограммы:
   setItems - процедура добавления элементов списка в tableView.
   getCoach - процедура получения списка записей из таблицы coach;
   getBass - процедура получения списка записей из таблицы bass;
   getgr - процедура получения списка записей из таблицы gr;
   getSeasonTicket - процедура получения списка записей из таблицы season_ticket;
   getUsers - процедура получения списка записей из таблицы users.
   */
    @FXML
    void on_click_view(ActionEvent event) throws ClassNotFoundException, SQLException {

    	dbConnect connector=new dbConnect();
		connection = connector.getConnection();               //Проверка открытой таблицы и ее обновление
    	if(tableCoach.isSelected()) {                                                
    		viewCoach.setItems(connector.getCoach("select * from coach"));           
    	}
    	else if(tablePool.isSelected()) {
    		viewPool.setItems(connector.getBass("select * from bass"));
    	}
    	else if(tableGrup.isSelected()) {	
    		viewGr.setItems(connector.getgr("select * from gr"));
    	}
    	else if(tableST.isSelected()) {
    		viewST.setItems(connector.getSeasonTicket("select * from season_ticket"));
    	}
    	else if(tableUsers.isSelected()) {
    		viewUsers.setItems(connector.getUsers("select * from users"));
    	}
    }

    /*initialize - процедура, выполняющаяся при инициализации формы.
   Для данной формы производится обозначение типов столбцов таблиц
   и заполнение таблиц данным из базы данных.
   Локальные переменные:
   connector - объект класса dbConnect.
   Используемые подпрограммы:
   setItems - процедура добавления элементов списка в tableView.
   getCoach - процедура получения списка записей из таблицы coach;
   getBass - процедура получения списка записей из таблицы bass;
   getgr - процедура получения списка записей из таблицы gr;
   getSeasonTicket - процедура получения списка записей из таблицы season_ticket;
   getUsers - процедура получения списка записей из таблицы users.
   */
	public void initialize() throws ClassNotFoundException, SQLException{
		
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();
    
    		id_coach.setCellValueFactory(new PropertyValueFactory<>("id"));                    //Обозначение типов для столбцов 
    		id_grFK.setCellValueFactory(new PropertyValueFactory<>("idGr"));                   //таблицы "Тренеры"
    		fio_coach.setCellValueFactory(new PropertyValueFactory<>("fio"));
    		graf_coach.setCellValueFactory(new PropertyValueFactory<>("sched"));
    		viewCoach.setItems(connector.getCoach("select * from coach"));
    	
    		id_pool.setCellValueFactory(new PropertyValueFactory<>("id"));                  //Обозначение типов для столбцов 
    		name_pool.setCellValueFactory(new PropertyValueFactory<>("name"));              //таблицы "Бассейны"
    		vPool.setCellValueFactory(new PropertyValueFactory<>("variety"));
    		adress_pool.setCellValueFactory(new PropertyValueFactory<>("adress"));
    		id_coachFK.setCellValueFactory(new PropertyValueFactory<>("idCoach"));
    		id_STFK.setCellValueFactory(new PropertyValueFactory<>("idST"));
    		viewPool.setItems(connector.getBass("select * from bass"));
    	
    		id_gr.setCellValueFactory(new PropertyValueFactory<>("id"));                    //Обозначение типов для столбцов 
    		num_gr.setCellValueFactory(new PropertyValueFactory<>("numb"));                 //таблицы "Группы"
    		cate_gr.setCellValueFactory(new PropertyValueFactory<>("cate"));
    		viewGr.setItems(connector.getgr("select * from gr"));
    	
    		id_ST.setCellValueFactory(new PropertyValueFactory<>("id"));                    //Обозначение типов для столбцов 
    		num_ST.setCellValueFactory(new PropertyValueFactory<>("numb"));                 //таблицы "Абонементы"
    		ofWeek.setCellValueFactory(new PropertyValueFactory<>("onAweek"));
    		cate_ST.setCellValueFactory(new PropertyValueFactory<>("cate"));
    		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    		viewST.setItems(connector.getSeasonTicket("select * from season_ticket"));

    		UloginCol.setCellValueFactory(new PropertyValueFactory<>("login"));            //Обозначение типов для столбцов 
    		UpassCol.setCellValueFactory(new PropertyValueFactory<>("password"));          //таблицы "Пользователи"
    		UnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    		UfamCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    		UstatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    		viewUsers.setItems(connector.getUsers("select * from users"));	
 }
}
