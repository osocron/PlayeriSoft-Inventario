package playeriSoft.modelo;

/**
 * Created by Noe on 18/04/15.
 */
public class Parche extends Producto {

    private double largo;
    private double ancho;
    private  boolean isBordado;
    private boolean isSerigrafia;


    public Parche(String idProducto, double descuento, String descripcion, int existencias, double precioMayoreo, double precioMenudeo) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
    }

    public  Parche(Producto producto, double largo, double ancho, boolean isBordado, boolean isSerigrafia){
        super(producto.getIdProducto(),producto.getDescuento(),producto.getDescripcion(),
                producto.getExistencias(),producto.getPrecioMayoreo(),producto.getPrecioMenudeo());
        this.largo = largo;
        this.ancho = ancho;
        this.isBordado = isBordado;
        this.isSerigrafia = isSerigrafia;
    }

    public Parche(String idProducto, double descuento, String descripcion, int existencias,
                 double precioMayoreo, double precioMenudeo, double largo,
                 double ancho, boolean isBordado, boolean isSerigrafia) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
        this.largo = largo;
        this.ancho = ancho;
        this.isBordado = isBordado;
        this.isSerigrafia = isSerigrafia;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public boolean isBordado() {
        return isBordado;
    }

    public void setBordado(boolean isBordado) {
        this.isBordado = isBordado;
    }

    public boolean isSerigrafia() {
        return isSerigrafia;
    }

    public void setSerigrafia(boolean isSerigrafia) {
        this.isSerigrafia = isSerigrafia;
    }

}
