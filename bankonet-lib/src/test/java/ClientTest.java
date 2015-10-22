import org.junit.Assert;
import org.junit.Test;

import lib.Client;
import lib.CompteCourant;

public class ClientTest {
	@Test
	public void testsynthese() {
		Client client = new Client("Toto", "toto", "toto", "pwd");
		if (client.synthese(CompteCourant.class) instanceof String) {
			//OK
		} else {
			Assert.fail("Return failure");
		}
	}
}
