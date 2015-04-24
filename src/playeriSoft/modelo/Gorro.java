package playeriSoft.modelo;

/**
 * Created by Noe on 18/04/15.
 */
public class Gorro extends Producto {

    private double talla;
    private String color;

    public Gorro(String idProducto, double descuento, String descripcion, int existencias, double precioMayoreo, double precioMenudeo) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
    }

    public  Gorro(Producto producto, double talla, String color){
        super(producto.getIdProducto(),producto.getDescuento(),producto.getDescripcion(),
                producto.getExistencias(),producto.getPrecioMayoreo(),producto.getPrecioMenudeo());
        this.talla = talla;
        this.color = color;
    }

    public Gorro(String idProducto, double descuento, String descripcion, int existencias,
                 double precioMayoreo, double precioMenudeo, double talla, String color) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
        this.talla = talla;
        this.color = color;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
