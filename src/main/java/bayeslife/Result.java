/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package bayeslife;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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
