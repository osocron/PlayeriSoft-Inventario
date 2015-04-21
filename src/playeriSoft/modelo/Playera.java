package playeriSoft.modelo;

/**
 * Created by Noe on 18/04/15.
 */
public class Playera extends Producto{

    private double talla;
    private String color;
    private String tipo;
    private  boolean isBordado;
    private boolean isSerigrafia;


    public Playera(String idProducto, double descuento, String descripcion, int existencias, double precioMayoreo, double precioMenudeo) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
    }

    public  Playera(Producto producto, double talla, String color, String tipo, boolean isBordado, boolean isSerigrafia){
        super(producto.getIdProducto(),producto.getDescuento(),producto.getDescripcion(),
                producto.getExistencias(),producto.getPrecioMayoreo(),producto.getPrecioMenudeo());
        this.talla = talla;
        this.color = color;
        this.tipo = tipo;
        this.isBordado = isBordado;
        this.isSerigrafia = isSerigrafia;
    }

    public Playera(String idProducto, double descuento, String descripcion, int existencias,
                  double precioMayoreo, double precioMenudeo,double talla, String color,
                  String tipo, boolean isBordado, boolean isSerigrafia) {
        super(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
        this.talla = talla;
        this.color = color;
        this.tipo = tipo;
        this.isBordado = isBordado;
        this.isSerigrafia = isSerigrafia;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
