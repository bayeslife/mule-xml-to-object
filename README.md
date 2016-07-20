# mule-xml-to-object-test
This maven project demonstrates how to convert from XML to Object

The mule context contains a jaxb context which points to a specifc java package.
The jaxb-xml-to-object-transformer reference the context and declares the java class expected to be returned.
```
	<mulexml:jaxb-context name="myJaxb" packageNames="bayeslife"/>

	<flow name="entry">
		<mulexml:jaxb-xml-to-object-transformer name="XmlToObject" jaxbContext-ref="myJaxb" returnClass="bayeslife.Result"/>
	</flow>
```

Note the java object is annotated with jaxb annotations
```
/**
 * A Result object
 */
@XmlRootElement(name = "Result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result
{
    private String URI;

    public String getURI()
    {
        return URI;
    }

    public void setURI(String uri)
    {
        this.URI = uri;
    }
}
```

There is a jaxb.index file in the resources folder which lists all classes with jaxb annotations (This replaces the ObjectFactory).

The test case has positive and negative scenarios so that we verify the functionality works and the test itself works.

The test load the mule context by virtue of being a functional test case and declaring a getConfigResources method pointing to the flow.

The xml is prepared into a Mule Message and send to the flow.
The payload from the flow is expected to be the java object with the appropriate content.
```
String uri = "the uri";
String xml = "<Result><URI>"+uri+"</URI></Result>";

Flow f = (Flow)muleContext.getRegistry().lookupFlowConstruct("entry");
DefaultMuleMessage m = new DefaultMuleMessage(xml, muleContext);
MuleEvent me = new DefaultMuleEvent(m, MessageExchangePattern.REQUEST_RESPONSE,null, f);

MuleEvent res = f.process(me);

Result rest = (Result)res.getMessage().getPayload();

Assert.assertEquals(uri,rest.getURI());
```
