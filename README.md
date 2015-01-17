# iobjects-crm

This project is a fully functional CRM application developed on iObjects/Java/HTML/CSS/JavaScript. Its functional requirements were obtained from **Salesforce's** free trial version and it should cover all the basics, which are:

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

# About
This CRM application was originally developed as a module for the Softgroup iManager (www.imanager.com.br). Softgroup is a brazilian company founded by me in 2008 and is still in operation. iManager is an ERP/CRM/BI solution used by hundreds of small retail companies in Brazil.

The UI and the source code comments were published as is: in brazilian portuguese. I apologize for any inconvenience, but I'm afraid I'll not have spare time enough to work on any kind of translation.

# Technical requisites
- iObjects framework
- Java 1.6 or higher
- Netbeans 8.0 or higher (Eclipse and other IDEs should be fine, but will require some manual configuration).

# Compatibility
All you need to take full advantage of the application is a modern web browser. My team and I have tested with Safari 7+, Chrome 30+ and Internet Explorer 9+.

# Database structure
There is a .sql file on the root of this project which contains all the needed table structure, plus a few sample records.

# Users and passwords
The following users will be automatically created on the first run. Additionally, you'll be able to insert all users and roles that you need by using the iObjects Security Service module.
- Username: @Super Usuário, Password: superusuario
- Username: Administrador, Password: administrador
- Username: Convidado, Password: convidado
