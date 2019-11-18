package Dialogs;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import accesobd.Estancia;
import conections.Mysql;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.insertDialogModel;

public class insertDialog extends Dialog<Estancia>{

	//Cargamos los elementos del FXML
	@FXML
	private TextField resi;
	
	@FXML
	private ComboBox<String> uni;
	
	@FXML
	private TextField precio;
	
	@FXML
	private CheckBox comedor;
	
	@FXML
	private Label fallo;
	
	//Creamos los elementos del modelo
	
	private ButtonType okButton, cancelButton;
	private Mysql conections = new Mysql();
	
	ObservableList<String> nomUniList = FXCollections.observableArrayList(new ArrayList<String>());
	insertDialogModel model = new insertDialogModel();
	
	public insertDialog() throws IOException {
		
		setTitle("Insertar Estancia");
		setHeaderText("Rellena los datos:");
		setContentText("Rellene todos los datos para insertar");
		
		okButton = new ButtonType("Insertar", ButtonData.OK_DONE);
		cancelButton = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		
		getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insertDialog.fxml"));
		fxmlLoader.setController(this);
		getDialogPane().setContent(fxmlLoader.load());
		
		try {
			
			//Obtener nombres de las Universidades
			PreparedStatement preparedUni = conections.conexion.prepareStatement("select nomUniversidad from universidades");
			ResultSet resultadoUni = preparedUni.executeQuery();
			
			uni.getItems().clear();
			uni.setPromptText("Residencias");
			
			while(resultadoUni.next()) {
				nomUniList.add(resultadoUni.getString(1));			
			}

			uni.setItems(nomUniList);
			
			resi.textProperty().bindBidirectional(model.nombreProperty());
                        uni.valueProperty().bindBidirectional(model.universidadProperty());
			precio.textProperty().bindBidirectional(model.precioProperty());
                        comedor.selectedProperty().bindBidirectional(model.comedorProperty());
                        
			model.precioProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(Integer.parseInt(model.getPrecio()) < 900 ){
						fallo.setText("El precio no puede ser menor a 900");
                                                getDialogPane().lookupButton(okButton).setDisable(true);
					}else {
                                                fallo.setText("");
						getDialogPane().lookupButton(okButton).setDisable(false);
					}
					
				}
			});
			
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Algo falló en la consulta");
			alert.setContentText("Consulta con Borja para más información");

			alert.showAndWait();
		}
		
		
		setResultConverter(bt -> { 
			
			if( bt.getButtonData() == ButtonData.OK_DONE ) 
			{onInsertBttn(ButtonData.OK_DONE);}
			
			else
			{ return null;
			}
			return null;
		});
		
	}
	
	
	private Estancia onInsertBttn(ButtonData data){
            
		String comedor;
            if(model.getComedor() == true){
                comedor = "Si";
            }else{
                comedor = "No";
            }
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("CONFIRMACION");
		confirmation.setHeaderText("¿Seguro que quieres insertar esta estancia?");
		confirmation.setContentText("Datos: \n Nombre: "+model.getNombre()+"\n Universidad: "+model.getUniversidad()+"\n Precio mensual: "+model.getPrecio()+"\n Comedor: "+comedor);

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK){
		       Mysql Database = new Mysql();
                    try {
                        PreparedStatement insert = Database.conexion.prepareStatement("insert into residencias (codResidencia, nomResidencia, codUniversidad, precioMensual, comedor) values ");
                    } catch (SQLException ex) {
                        Logger.getLogger(insertDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} else {
		    System.out.println("cancelado");
		}
		
		return null;
	}
	
}
