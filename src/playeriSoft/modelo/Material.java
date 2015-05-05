package playeriSoft.modelo;

import java.text.DecimalFormat;

/**
 * Created by Noe on 18/04/15.
 */
public class Material {

    private String idMaterial;
    private String descripcionMaterial;
    private double precioMaterial;
    private String unidadMedida;
    private double cantidadDeProducto;
    private boolean isSelected;
    private double cantidadSeleccionada;

    public Material(String idMaterial, String descripcionMaterial, double precioMaterial, String unidadMedida, double cantidadDeProducto) {
        this.idMaterial = idMaterial;
        this.descripcionMaterial = descripcionMaterial;
        this.precioMaterial = precioMaterial;
        this.unidadMedida = unidadMedida;
        this.cantidadDeProducto = cantidadDeProducto;
        this.isSelected = false;
        this.cantidadSeleccionada = 0.0;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public double getPrecioMaterial() {
        return precioMaterial;
    }

    public void setPrecioMaterial(double precioMaterial) {
        this.precioMaterial = precioMaterial;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getCantidadDeProducto() {
        return cantidadDeProducto;
    }

    public void setCantidadDeProducto(double cantidadDeProducto) {
        this.cantidadDeProducto = cantidadDeProducto;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public double getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(double cantidadSeleccionada) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        this.cantidadSeleccionada = Double.valueOf(decimalFormat.format(cantidadSeleccionada));
    }
}
