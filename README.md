# iObjects CRM

This project is a fully functional CRM web application developed on iObjects/Java/HTML/CSS/JavaScript. Its functional requirements were based on [Salesforce](http://salesforce.com)'s free trial version which should cover some pretty exciting features, like:

- Multi-company and multi-department support.
- Contacts: customers, supplyers, employees.
- Activities: calls, e-mails and any kind of interaction with contacts.
- Tasks: contacts related requests and pendencies, due dates, forwarding history and more.
- Leads: follow up, status, history, success/failure and more.
- Call center screen: all contact related informations on sight, frequent activities and more.
- Calendar with holidays support and weekly visualization.
- Direct mail with route tracing support on Google Maps.
- 25 cards and reports with several different analysis about ROI, ad campaigns, salesman performance, etc.
- Tons of minor features.

![](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/1.png)
![](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/2.png)
![](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/3.png)
![](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/4.png)

## About
This CRM application was originally developed as a module for the [Softgroup iManager](http://imanager.com.br). Softgroup is a brazilian company founded by me in 2008 and is still in operation. iManager is an ERP/CRM/BI solution used by hundreds of small retail companies in Brazil.

Although the source code is really well documented, all the comments (and the user interface as well) were published as is: in brazilian portuguese. I apologize for any inconvenience, but I'm afraid I'll not have spare time enough to work on any kind of translation.

## Technical requisites
- [iObjects](https://github.com/kleber-maia/iobjects) framework
- [iObjects Security Service](https://github.com/kleber-maia/iobjects-security-service)
- Java 1.6 or higher
- Netbeans 8.0 or higher.

If you want to use Eclipse or another IDE:
- Add **iobjects/build/web/WEB-INF/classes** and **securityservice/build/web/WEB-INF/classes** to the project's compile libraries;
- Make sure to build the project using ".iobjects" extension instead of ".war", ex: crm.iobjects;
- Distribute the builded project's archive to iobjects/web.work/extensions directory.

## Database structure
There is a .sql file on the root of this project which contains all the needed table structure, plus a few sample records. Although the script was generated from a PostgreSQL database, it should be easy to migrate it to another RDMS of your choice.

## Compatibility
All you need to take full advantage of the application is a modern web browser. My team and I have tested with Safari 7+, Chrome 30+ and Internet Explorer 9+.
