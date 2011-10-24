# DomainAPI Java Wrapper #

## Presentation ##

To help you implement the DomainAPI webservices into your portals, web, desktop or mobile applications, 
we have published several API wrappers for different languages (PHP, Java, …) under the MIT License. 

This is the implementation of the Java wrapper.

List of available DomainAPI services:

* availability
* whois
* historic_whois,
* info
* ip_location
* marketplace
* tld_knowlegdebase
* search_engine
* thumbnail


## Requirements ##

* java5
* maven2

## Installation ##

To use the domainAPI wrapper, you should build the project:

	mvn clean install

You can now test the connection to the API with the command line:

	java -jar domainapi.client-1.0.0.jar your_login your_password example.com availability regions=gen
	
See the help to know the others uses of command line application.

	java -jar domain domainapi.client-1.0.0.jar -help


To use the wrapper in a java project, you can add a dependency to this library and call the service:

	Authenticator authentificator = new Authenticator(your_login,you_password);
	AvailabilityService service = new AvailabilityService(authentificator, "example.com");
	service.setReturnType(DomainApiReturn.JSON);
	service.addParameter("regions", "gen");
	service.retrieve();
	
	if(service.getStatus() == 200){
		System.out.println(service.getResponse());
	}
	


## Changelog ##

See the CHANGELOG.md file for details.

## License ##

See the LICENSE.md file for details.

