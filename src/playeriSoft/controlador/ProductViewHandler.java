package playeriSoft.controlador;

import playeriSoft.modelo.Producto;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewHandler {

    private Producto curProd;
    private MysqlConnector myMysqlConnector;

    public ProductViewHandler(Producto curProd){
        this.curProd = curProd;
    }

    public void setCurProd(Producto curProd){
        this.curProd = curProd;
    }

    public Producto getCurProd(){
        return curProd;
    }




}
