import okhttp3.RequestBody;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApiTests extends Helper {






    @Test
    public void TestGetListUser()  {


        Helper.MakeGetRequest("https://reqres.in/api/users?page=2",200);



    }
    @Test
    public void TestPostRegisterSuccessful() throws IOException {

       String body =getKeyFromProp("registersuccess");

        Helper.MakePostRequest("https://reqres.in/api/register",200,body);



    }

    @Test
    public void TestputRequest() throws IOException {
        String putpody=getKeyFromProp("putbody");
        Helper.MakePostRequest("https://reqres.in/api/users/2",201,putpody);


    }
    @Test
    public void TestDelRequest() {
        Helper.MakeDeleteRequest("https://reqres.in/api/users/2",204);


    }
}


