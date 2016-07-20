
import bayeslife.Result;
import org.junit.Assert;
import org.junit.Test;
import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleEvent;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;


public class TestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "xmltoobject.xml";
    }

    @Test
    public void postiveTest() throws Exception
    {
        String uri = "the uri";
        String xml = "<Result><URI>"+uri+"</URI></Result>";

        Flow f = (Flow)muleContext.getRegistry().lookupFlowConstruct("entry");
        DefaultMuleMessage m = new DefaultMuleMessage(xml, muleContext);
        MuleEvent me = new DefaultMuleEvent(m, MessageExchangePattern.REQUEST_RESPONSE,null, f);

        MuleEvent res = f.process(me);

        Result rest = (Result)res.getMessage().getPayload();

        Assert.assertEquals(uri,rest.getURI());
    }

    @Test
    public void negativeTest() throws Exception
    {
        String uri = "the uri";
        String xml = "<Result><URI>"+uri+"differenct"+"</URI></Result>";

        Flow f = (Flow)muleContext.getRegistry().lookupFlowConstruct("entry");
        DefaultMuleMessage m = new DefaultMuleMessage(xml, muleContext);
        MuleEvent me = new DefaultMuleEvent(m, MessageExchangePattern.REQUEST_RESPONSE,null, f);

        MuleEvent res = f.process(me);

        Result rest = (Result)res.getMessage().getPayload();

        Assert.assertNotEquals(uri,rest.getURI());
    }

}
