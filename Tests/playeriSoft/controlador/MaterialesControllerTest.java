package playeriSoft.controlador;

import org.junit.Assert;
import org.junit.Test;
import playeriSoft.modelo.Material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class MaterialesControllerTest {

    MaterialesController materialesController = new MaterialesController();

    @Test
    public void testValidateMaterialItemsIsValidated() {
        Material material = new Material("TINT0001","Material X",12.03,"pieza",1);
        material.setSelected(true);
        material.setCantidadSeleccionada(12);
        List<Material> list = new ArrayList<>();
        list.add(material);
        list.add(material);
        list.add(material);
        final boolean[] isValidated = {true};
        final boolean[] atLeastOneSelected = {false};
        final boolean[] allWithCantidades = {true};
        materialesController.validateMaterialItems(list,isValidated,atLeastOneSelected,allWithCantidades);
        Assert.assertEquals(true,isValidated[0]);
    }

}