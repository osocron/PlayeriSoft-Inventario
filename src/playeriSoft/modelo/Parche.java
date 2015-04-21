package playeriSoft.modelo;

/**
 * Created by Noe on 18/04/15.
 */
public class Parche extends Producto {

    private double alto;
    private double ancho;


    public Parche(String idProducto, double descuento, String descripcion, int existencias, double precioMayoreo, double precioMenudeo) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
    }

    public  Parche(Producto producto, double alto, double ancho){
        super(producto.getIdProducto(),producto.getDescuento(),producto.getDescripcion(),
                producto.getExistencias(),producto.getPrecioMayoreo(),producto.getPrecioMenudeo());
        this.alto = alto;
        this.ancho = ancho;
    }

    public Parche(String idProducto, double descuento, String descripcion, int existencias,
                 double precioMayoreo, double precioMenudeo,Producto producto, double alto, double ancho) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
        this.alto = alto;
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

}
