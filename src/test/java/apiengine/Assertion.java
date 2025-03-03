package apiengine;

import org.testng.Assert;

import com.tugasqa.model.ResponseObject;
import com.tugasqa.model.request.RequestItem;

public class Assertion {
    public void assertAddProduct(ResponseObject ResponseObject, RequestItem requestItem ){
        Assert.assertEquals(ResponseObject.name, requestItem.name);
        Assert.assertEquals(ResponseObject.data.year, requestItem.data.year);
        Assert.assertEquals(ResponseObject.data.price, requestItem.data.price);
        Assert.assertEquals(ResponseObject.data.cpuModel, requestItem.data.cpuModel);
    }

    public void the_item_is_available(ResponseObject ResponseObject, RequestItem requestItem){
        Assert.assertEquals(ResponseObject.name, requestItem.name);
        Assert.assertEquals(ResponseObject.data.year, requestItem.data.year);
        Assert.assertEquals(ResponseObject.data.price, requestItem.data.price);
        Assert.assertEquals(ResponseObject.data.cpuModel, requestItem.data.cpuModel);
    }

}
