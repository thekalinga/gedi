package info.sargis.gedi.parser;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.*;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 3, 2010
 */
public class EDIXPath implements XPath {

    private XPathVariableResolver variableResolver;
    private XPathFunctionResolver functionResolver;
    private NamespaceContext nsContext;

    private final XPath xpath;

    public EDIXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    @Override
    public void reset() {
    }

    @Override
    public void setXPathVariableResolver(XPathVariableResolver resolver) {
        this.variableResolver = resolver;
    }

    @Override
    public XPathVariableResolver getXPathVariableResolver() {
        return variableResolver;
    }

    @Override
    public void setXPathFunctionResolver(XPathFunctionResolver resolver) {
        functionResolver = resolver;
    }

    @Override
    public XPathFunctionResolver getXPathFunctionResolver() {
        return functionResolver;
    }

    @Override
    public void setNamespaceContext(NamespaceContext nsContext) {
        this.nsContext = nsContext;
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return nsContext;
    }

    @Override
    public XPathExpression compile(String expression) throws XPathExpressionException {
        return xpath.compile(expression);
    }

    @Override
    public Object evaluate(String expression, Object item, QName returnType) throws XPathExpressionException {
        return xpath.evaluate(expression, item, returnType);
    }

    @Override
    public String evaluate(String expression, Object item) throws XPathExpressionException {
        return xpath.evaluate(expression, item);
    }

    @Override
    public String evaluate(String expression, InputSource source) throws XPathExpressionException {
        try {
            Document document = getDocument(source);
            return evaluate(expression, document);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    @Override
    public Object evaluate(String expression, InputSource source, QName returnType) throws XPathExpressionException {
        try {
            Document document = getDocument(source);
            return evaluate(expression, document, returnType);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    private Document getDocument(InputSource inputSource) throws TransformerException {
        EDIReader ediReader = new EDIReader();
        return SAX2DOM.transform(ediReader, inputSource);
    }

}
