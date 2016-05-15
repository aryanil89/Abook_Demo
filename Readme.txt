For setting up the project following steps need to be followed,
1. Check out the code from github.
2. Import the project in eclipse/ide as maven project.
3. build with 'mvn clean install' for war file or 'mvn eclipse:eclipse' for eclipse build.
4. Go to Application.java and start the application as 'Java Application'.
5. Server will start in 8085 port as it is been mentioned application.properties . It can be modified

Here are the rest web services with url for local,

a. To get all employees, method type : GET
http://host:8085/adsbook/employee/getAllEmployees

b. To get one single employee with empId, method type : GET
http://host:8085/adsbook/employee/getEmployeeById/{empId}

c. To add a new employee, method type : POST
http://host:8085/adsbook/employee/addEmployee
 input format in json,
 {
	"empId": "123",
	"empFName": "Xyz",
	"empLName": " ",
	"empAddress": " ",
	"empPhone": " "
}

d. To update an employee with employee id, method type : PUT
http://localhost:8085/adsbook/employee/updateEmployee/{empId}

input format in json,
 {
	"empId": "empId",
	"empFName": "Xyz",
	"empLName": " ",
	"empAddress": "updated address",
	"empPhone": " "
}

e. To remove an employee with employee id, method type : DELETE
http://localhost:8085/adsbook/employee/removeEmployee/{empId}

Note: The database  has  been used is HyperSQL database for development. after starting the server it is needed to add employee first before doing any other operation. As every time server stop will clear the data from database

Frameworks/libraries has been used to develop this project
----------------------------------------------------------
1. Spring boot 1.3.5
2. hsqldb 

Application health check url
----------------------------
http://host:8085/health





