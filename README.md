# iobjects-crm

This project is a fully functional CRM web application developed on iObjects/Java/HTML/CSS/JavaScript. Its functional requirements were based on [Salesforce](http://salesforce.com)'s free trial version which should cover some pretty exciting features, like:

- Multi-company and multi-department support.
- Contacts: customers, supplyers, employees.
- Activities: calls, e-mails and any kind of interaction with contacts.
- Tasks: contacts related requests and pendencies, due dates, forwarding history and more.
- Opportunities: leads, follow up, status, successes/failures and more.
- Call center screen: all contact related informations on sight, frequent activities and more.
- Calendar with holidays support and weekly visualization.
- Direct mail with route tracing support on Google Maps.
- 25 cards and reports with several different analysis about ROI, ad campaigns, salesman performance, etc.
- Tons of minor features.

![Login screen](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/1.png)
![Customizable desktop](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/2.png)
![Contact form](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/3.png)
![Leads by ad campaign report](https://raw.github.com/kleber-maia/iobjects-crm/master/README.img/4.png)

## About
This CRM application was originally developed as a module for the [Softgroup iManager](http://imanager.com.br). Softgroup is a brazilian company founded by me in 2008 and is still in operation. iManager is an ERP/CRM/BI solution used by hundreds of small retail companies in Brazil.

The UI and the source code comments were published as is: in brazilian portuguese. I apologize for any inconvenience, but I'm afraid I'll not have spare time enough to work on any kind of translation.

## Technical requisites
- [iObjects framework](https://github.com/kleber-maia/iobjects)
- Java 1.6 or higher
- Netbeans 8.0 or higher (Eclipse and other IDEs should be fine, but will require some manual configuration).

## Compatibility
All you need to take full advantage of the application is a modern web browser. My team and I have tested with Safari 7+, Chrome 30+ and Internet Explorer 9+.

## Database structure
There is a .sql file on the root of this project which contains all the needed table structure, plus a few sample records.

## Users and passwords
The following users will be automatically created on the first run. Additionally, you'll be able to insert all users and roles that you need by using the iObjects Security Service module.
- Username: @Super Usuário, Password: superusuario
- Username: Administrador, Password: administrador
- Username: Convidado, Password: convidado
