# WebMart Database Project
Project can be downloaded from  
https://github.com/jcwijaya/Java-Database-Project.git  
# Contributers
Jessica Wijaya  
Hannah Williams  
Jovi Chittaphong  
Pratap Karki  
Sanith Baddam  

# Software tools
This project was made using Eclipse, SceneBuilder, MySql server 8, and MySql Workbench 8.  

# After cloning project
1. Run script called 'Tables-WebMart.sql' to create the database with tables and a default user. 
2. Run Main.java. It should show the login page. Click on the button that says 'Database Setup' to make sure that your database information is correct. If you decide to use a different database username or password instead of the default user given in the script, you can enter it in and save it on this page.
3. Test connection. If it is successful you can go to login page to create a new account and start using WebMart.
4. After setting up the database connection, you can fill up the inventory table with some sample data by running InitializeInventory.java in the WebMart package. With this you can add items to the shopping cart by clicking on a row in the inventory table view and pressing 'Add to Cart' under the Shopping Cart menu.
# Overview
WebMart is a program that allows you to manage a local SQL database with grocery store information. The database contains three tables: customers, employees, and inventory. This program lets you insert, delete, and update the information on these tables. It also has a feature to create a shopping cart that will update the amount in stock and show the total price.
# Bugs
-After adding an item to the shopping cart you may have to press 'refresh table' to see the changes of the amount on the GUI.
-The program will run normally but the stack trace may be printed in the console if you try to press delete or add to cart without anything selected.
