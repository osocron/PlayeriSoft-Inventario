package playeriSoft.modelo;

import java.util.List;

/**
 * Created by Noe on 18/04/15.
 */
public class Producto {

    private String idProducto;
    private double descuento;
    private String descripcion;
    private int existencias;
    private double precioMayoreo;
    private double precioMenudeo;
    private List<Material> materiales;


    public Producto(String idProducto, double descuento, String descripcion, int existencias, double precioMayoreo, double precioMenudeo) {
        this.idProducto = idProducto;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.precioMayoreo = precioMayoreo;
        this.precioMenudeo = precioMenudeo;
        this.materiales = null;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPrecioMayoreo() {
        return precioMayoreo;
    }

    public void setPrecioMayoreo(double precioMayoreo) {
        this.precioMayoreo = precioMayoreo;
    }

    public double getPrecioMenudeo() {
        return precioMenudeo;
    }

    public void setPrecioMenudeo(double precioMenudeo) {
        this.precioMenudeo = precioMenudeo;
    }

    public void agregarMaterial(Material material){
        materiales.add(material);
    }

    public void eliminarMaterial(int pos){
        materiales.remove(pos);
    }

    public Material getMaterial(int pos){
        return materiales.get(pos);
    }

}
