package tlacouque.uc.admin.ref.customer;

import org.junit.Assert;
import org.junit.Test;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;

/**
 * Created by tlacouque on 19/01/2016.
 */

 public class FormatValidatorTest {

    @Test
    public void testFormatUrl() throws Exception {
        String urlWithoutHTTP = "www.url.com";
        String http           = "http://";
        Assert.assertEquals(http + urlWithoutHTTP, FormatValidator.formatUrl(urlWithoutHTTP));
        Assert.assertNotEquals(urlWithoutHTTP, FormatValidator.formatUrl(urlWithoutHTTP));
        Assert.assertEquals(http + urlWithoutHTTP, FormatValidator.formatUrl(http + urlWithoutHTTP));
    }

    @Test
    public void testisSiretSyntaxValide() throws Exception {
        final String siretNumberValid    = "26770008600056";
        final String siretNumberNotValid = "2677000860005";
        Assert.assertTrue(FormatValidator.isSiretSyntaxValide(siretNumberValid));
        Assert.assertFalse(FormatValidator.isSiretSyntaxValide(siretNumberNotValid));

    }

}
