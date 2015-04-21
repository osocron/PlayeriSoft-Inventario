package playeriSoft.modelo;

/**
 * Created by Noe on 18/04/15.
 */
public class Material {

    private String idMaterial;
    private String descripcionMaterial;
    private double precioMaterial;
    private String unidadMedida;
    private double cantidadDeProducto;

    public Material(String idMaterial, String descripcionMaterial, double precioMaterial, String unidadMedida, double cantidadDeProducto) {
        this.idMaterial = idMaterial;
        this.descripcionMaterial = descripcionMaterial;
        this.precioMaterial = precioMaterial;
        this.unidadMedida = unidadMedida;
        this.cantidadDeProducto = cantidadDeProducto;
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

}
