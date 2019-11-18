package models;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class insertDialogModel {

	//modelos de los property con los que trabajaremos en el diálogo
	
        StringProperty nombre = new SimpleStringProperty();
	StringProperty universidad = new SimpleStringProperty();
        StringProperty precio = new SimpleStringProperty();
        BooleanProperty comedor = new SimpleBooleanProperty();
                
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final StringProperty precioProperty() {
		return this.precio;
	}
	

	public final String getPrecio() {
		return this.precioProperty().get();
	}
	

	public final void setPrecio(final String precio) {
		this.precioProperty().set(precio);
	}
	
        public final BooleanProperty comedorProperty() {
		return this.comedor;
	}
	

	public final Boolean getComedor() {
		return this.comedorProperty().get();
	}
    
        public final void setComedor (final Boolean comedor) {
		this.comedorProperty().set(comedor);
	}
	
        public final StringProperty universidadProperty() {
		return this.universidad;
	}
	

	public final String getUniversidad() {
		return this.universidadProperty().get();
	}
    
        public final void setUniversidad (final String universidad) {
		this.universidadProperty().set(universidad);
	}
    
    
	
}
