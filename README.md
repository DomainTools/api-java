# DomainToolsAPI Java Wrapper #

## Presentation ##

The DomainToolsAPI Java Wrapper is a simple connector to access all webservices of [domaintools.com](http://domaintools.com "domaintools.com").

## Getting started ##

To use the DomainTools API, you need to have a DomainTools Account. [Create and account](https://secure.domaintools.com/join/).

### Installation ###

1A- [Download the jar](http://domain.com/api-java/domaintools.api.client-1.0.0.jar) and add it in your Library.
 
1B- If you use Maven, add this to your pom xml :

MAVEN

### Usage ###

1- Visit [domaintools.com](http://www.domaintools.com/api/docs/products/) to know the list of available services.

Beware, certains services (like *reverse-whois*) don't need domains to work, but require parameters to work. (Example *terms=Seattle*). 

Please read attentively how to use each service.

2- When you need to use a DomainTools service :

1. Add import com.domaintoolsapi.*; 

1. Create a DomainTools object 

```java
DomainTools domainTools = new DomainTools("your_username", "your_key");
```

1. Set a **product** with the method **use**
To set a product, call the method **use** with a **String** representation of the the product name 
```java domainTools.use("whois")```

1. Set a **domain** with the method **on**
To set a domain, call the method **on** with a **String** representation of the domain name
```java domainTools.on("domaintools.com")```

1. Set a **format**
DomainTools can return responses in diffents formats :
* JSON with **toJSON()**
* XML with **toXML()**
* A Java Object **toObject()** 

1. Set **parameters**
Some products need parameters to be executed. To set parameters, call the method **where**.
**where** can use String or Map argument.

```java domainTools.use("reverse-ip").on("domaintools.com").where("limit=10").signed(true).toXML() ```

In this example, we limit to 10 the number of domain names's responses.
We can also use a Map to declare some parameters

```java 
Map<String, String> parameters = new HashMap<String, String>();
parameters.put("limit", "10");
domainTools.use("reverse-ip").on("domaintools.com").where(parameters).signed(true).toXML();
```

If you want to reuse a request with different parameters, you can use **resetParameters()** to erase current parameters.

```java 
domainTools.resetParameters();
```

If you also want to erase product, domain and format, use **clear()**

```java 
domainTools.clear();
```


### Signed Authentication ###

The DomainToolsAPI Java Wrapper allows to use the HMAC (or hashed message authentication code) to protect the API Key.

To use HMAC, add **signed(true)** on your request.

Example :

```java 
domainTools.use("domain-suggestions").where("query=domain%20tools").signed(true).toXML()
```

We recommande to use this authentication scheme to protecting your API key.


### Method chaining ###

The domaintoolsAPI Java Wrapper is a fluent API implemented by using method chaining.
You can combine methods to specify return type, options, etc.:

Example :

```java 
domainTools.use("reverse-whois").where("terms=DomainTools%20LLC|Seattle").where("mode=purchase").signed(true).toXML();
```
This request use the reverse-whois services and add terms and mode parameters in signed mode and require a XML format.

## Changelog ##

See the CHANGELOG.md file for details.

## License ##

Copyright (C) 2011 by domaintools.com, DomaintoolsAPI PHP Wrapper is released under the MIT license.

See the LICENSE.md file for details.


