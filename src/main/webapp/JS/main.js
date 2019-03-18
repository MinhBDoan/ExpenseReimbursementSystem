

function Login(){

 
   
   let loginData = {
			email: document.getElementById("email").value,
			password: document.getElementById("pw").value
		}
   console.log(loginData)
   let payload = JSON.stringify(loginData);
   
   let xhr = new XMLHttpRequest();
   
   xhr.onload =()=>{
	   
	   console.log(xhr.responseText + "Login ajax")
	   let info = JSON.parse(xhr.responseText);
	   console.log(info + " infoooo" )
	   console.log("onload id is " + info );
	   if(info.position === "Employee"){
		   
		   EmployeeHomePage(info);
	   }else if(info.position ==="Manager"){
		ManagerHomePage(info);   
	   }
		   
	   }
   
   xhr.onError = ()=>{
	   console.log('Error')
   }
   xhr.open("POST","http://localhost:8080/org.revature.ers/LoginServlet");
   console.log(payload);
   xhr.send(payload);
    
}

function ManagerHomePage(info){
	let infoData = {
			id: info.id,
			position: info.position,
			fullname: info.fullname,
			email: info.email,
			password: info.password
		}
		
		document.getElementById("container").innerHTML = 
	`<div class="container-fluid">
	    <div class="row content">
	      <div class="col-sm-3 sidenav">
	          <h4></h4>
	            <ul class="nav nav-pills nav-stacked">
	              <li class="active"><a href="#section1"id="logout" style="text-align: center "onClick="redirectLogin()">Log Out</a></li>
	              
	            </ul><br>
	          <div class="input-group">
	               <input type="text" id ="searchInput"class="form-control" onkeyup=searchBar() placeholder="Search Reimbursements">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
	          </div>
	      </div>
	              
	      <div class="col-sm-9">
	        <h4><small></small></h4>
	        
	          <h2>Employee Basic Info</h2>      
	          <span id="alertUpdate"></span>
	          <table class="table table-hover">
	            <thead>
	              <tr>
	                <th>Id</th>
	                <th>Position</th>
	                <th>Full Name</th>
	                <th>Email</th>
	                <th>Password</th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	                <td>${infoData.id}</td>
	                <td>${infoData.position}</td>
	                <td id ="tdfullname">${infoData.fullname}</td>
	                <td id="tdemail">${infoData.email}</td>
	                <td id ="tdpassword">${infoData.password}</td>
	              </tr>
	            </tbody>
	          </table>  
	            
	        <h4><small></small></h4>
	          <hr> 
	          <h2>All Reimbursement Requests</h2>   
	            <button type="button" class="btn btn-success" id="showRequests" onClick="AllEmployeeReimbursement()">Refresh Reimbursement Requests</button>
	              <div id="AllReimbursementRequests">
	                      
	              </div>
	                             
	                  
	                                         
	        </div>
	      </div>        
	    </div>
	  </div>
	              
	              <footer class="container-fluid">
	                <p>Footer Text</p>
	              </footer>`
			
			AllEmployeeReimbursement = () => {		
				let xhr = new XMLHttpRequest();
				xhr.onload = () => {
		            var reimbursementList = ``;
		            var receipt = JSON.parse(xhr.responseText);
		           
		            for (var i=0; i<receipt.length; i++) {                
		            	console.log(receipt[i])
		            	console.log(infoData.email)
		            	console.log(infoData.password)
		            	
		                var receiptInfo = "";
		            	receiptInfo = JSON.stringify({
		                    id : receipt[i].id,
		                    status : receipt[i].ticketStatus,
		                    ManagerId : infoData.id,
		                    manageremail: infoData.email,
		                    managerpassword:infoData.password
		                    
		                });        
		            	
		                reimbursementList += `
		                <tr>
		<td>`+receipt[i].status+`      <button type="button" class="btn btn-success" onclick='acceptReimbursement(`+receiptInfo+`)'>Accept</button><button type="button" class="btn btn-danger" onclick='declineReimbursement(`+receiptInfo+`)'>Decline</button></td>
		<td>$`+receipt[i].amount+`</td>
		                    <td><img height="42" width="42"
		                        onclick=modalImage("`+receipt[i].image+`")
		                        src="`+receipt[i].image+`" /></td>
		                    <td>`+receipt[i].employeeId+`</td>
		                    <td>`+receipt[i].employeeName+`</td>
		                    <td>`+receipt[i].email+`</td>
		                    <td>`+receipt[i].password+`</td>
		                    <td>`+receipt[i].managerId+`</td>
		                    <td>`+receipt[i].managerEmail+`</td>
		                </tr>`;
		                 
		            } 
		            document.getElementById('AllReimbursementRequests').innerHTML = 
		            `
		            <table id="allReimbursementTable"  class="table table-hover table-dark">
		                <thead>
		                    <th>Status</th>
		                    <th>Amount</th>
		                    <th>Image</th>
		                    <th>Employee Id</th>
		                    <th>Employee Name</th>
		                    <th>Email</th>
		                    <th>Password</th>
		                    <th>Manager Id</th>
		                    <th>Manager Email</th>
		                </thead>
		                <tbody>`
		                +reimbursementList+
		                `</tbody>
		            </table>
		            <br>
		            <br><br>
		            `; 
			    };

				
				xhr.open("GET", "http://localhost:8080/org.revature.ers/ManagerReimbursementServlet");
			    xhr.onerror = function() {
			        console.log('Error');
			    }
			    xhr.send();   
			}	
			acceptReimbursement = (receipt) => {
		        var receiptInfo = "";
		        receiptInfo = JSON.stringify({
		            id : receipt.id,
		            status : "Accepted",
		            managerId : receipt.ManagerId,
		            manageremail: receipt.manageremail,
		            managerpassword:receipt.managerpassword
		            
		        });
		        
		        let xhr = new XMLHttpRequest();
		        xhr.onload = () => {
		        	AllEmployeeReimbursement();
			    };
			    xhr.onerror = function() {
			        console.log('Error');
			    }
			    
		        xhr.open('POST', "http://localhost:8080/org.revature.ers/StatusChangeServlet");
		        xhr.send(receiptInfo);
		    }
			
			declineReimbursement = (receipt) => {
		        var receiptInfo = "";
		        receiptInfo = JSON.stringify({
		            id : receipt.id,
		            status : "Declined",
		            managerId : receipt.ManagerId,
		            manageremail: receipt.manageremail,
		            managerpassword:receipt.managerpassword
		            
		        });
		        
		        let xhr = new XMLHttpRequest();
		        xhr.onload = () => {
		        	AllEmployeeReimbursement();
			    };
			    xhr.onerror = function() {
			        console.log('Error');
			    }
			    
		        xhr.open('POST', "http://localhost:8080/org.revature.ers/StatusChangeServlet");
		        xhr.send(receiptInfo);
		    }
}
let searchBar = () => {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("searchInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("allReimbursementTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}
function EmployeeHomePage(info){
	let infoData = {
		id: info.id,
		position: info.position,
		fullname: info.fullname,
		email: info.email,
		password: info.password
	}
	
	document.getElementById("container").innerHTML = 
`<div class="container-fluid">
    <div class="row content">
      <div class="col-sm-3 sidenav">
          <h4></h4>
            <ul class="nav nav-pills nav-stacked">
              <li class="active"><a href="#section1"id="logout" style="text-align: center "onClick="redirectLogin()">Log Out</a></li>
              
            </ul><br>
          <div class="input-group">
         
          </div>
      </div>
              
      <div class="col-sm-9">
        <h4><small></small></h4>
        
          <h2>Employee Basic Info</h2>      
          <span id="alertUpdate"></span>
          <button class="btn btn-success" id="updateInfo" onClick="updateInfo('${infoData.id}','${infoData.position}')">Save Update</button>
          <table class="table table-hover">
            <thead>
              <tr>
                <th>Id</th>
                <th>Position</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Password</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>${infoData.id}</td>
                <td>${infoData.position}</td>
                <td contenteditable id ="tdfullname">${infoData.fullname}</td>
                <td contenteditable id="tdemail">${infoData.email}</td>
                <td contenteditable id ="tdpassword">${infoData.password}</td>
              </tr>
            </tbody>
          </table>  
            
        <h4><small></small></h4>
          <hr> 
          <h2>All Reimbursement Requests</h2>  
           <button type="button" class="btn btn-success" id="showRequests" onClick="getEmployeeReimbursement('${infoData.email}')">Refresh Reimbursement Requests</button>
            
           
              <div id="AllReimbursementRequests">
                      
              </div>
                      
  
                    <h2>Submit Reimbursement Request</h2>
                    <br><br>
                    <form id="createRequest">
                <div class="form-group">
                    <label>Amount:</label>
                    <input type="text" class="form-control" id="amount" placeholder="$ 00.00">
                </div>
                  <div class="form-group">
                      <label>Upload Receipt Image:</label>
                    <div id="requestMessage"></div>
                      <input type="file" onchange="Preview()" class="form-control-file" id="image">
                      <img id=preview src="">
                  </div>
                      <button type="button" class="btn btn-success" id="" onclick="submitRequest('${infoData.id}','${infoData.fullname}','${infoData.email}','${infoData.password}')">Submit Ticket</button>
                      </form> 
                                         
        </div>
      </div>        
    </div>
  </div>
              
              <footer class="container-fluid">
                <p>Footer Text</p>
              </footer>`
		
		 updateInfo=()=>{
			 
			 infoData.fullname = document.getElementById("tdfullname").innerText;
			 infoData.email = document.getElementById("tdemail").innerText;
			 infoData.password = document.getElementById("tdpassword").innerText;
		
		document.getElementById("alertUpdate").innerHTML = `<div class="alert alert-success" role="alert">
			  <strong>Save Success!</strong> 
			</div>`
			setTimeout(function(){ document.getElementById("alertUpdate").innerHTML = ``; }, 2500);
	
		let payload = JSON.stringify(infoData)
		let xhr = new XMLHttpRequest();
		
		xhr.onload = () => {// console.log(JSON.parse(xhr.responseText).name)
			
	    };
	    xhr.onerror = function() {
	        console.log('Error');
	    }
	    console.log(payload)
	    xhr.open("POST", "http://localhost:8080/org.revature.ers/UpdateEmployeeServlet");
	    xhr.send(payload);
	}
}


var base64value = "";
function Preview() {
	
	 base64value = "";
	    var fileSelected = document.getElementById('createRequest').elements.item(1).files;
	        if (fileSelected.length > 0) {
	            var fileToLoad = fileSelected[0];
	            var fileReader = new FileReader();
	            //this fucntion converts image to base64 string
	            fileReader.onload = function(fileLoadedEvent) {
	                base64value = "";
	                base64value = fileLoadedEvent.target.result;
	                output=document.getElementById("preview");
	                output.src=base64value;
	                console.log(base64value);
	                
	                document.getElementById('requestMessage').innerHTML = `<p style="color:green">Image Ready!</p>`;
	            };
	            //put incoming file image into the function to convert to base64
	            fileReader.readAsDataURL(fileToLoad);

	}
}

function submitRequest(id,fullname,email,password){

	var amount = document.getElementById('createRequest').elements.item(0).value;
	
	 let json="";
	 let status = "pending"
		 let ManagerId=0;
	 let managerEmail="N/A";
	 let managerPassword="N/A";
	  json= JSON.stringify({
		  "status": status,
		  "image": base64value,
		  "amount": amount,
		  "employeeId":id,
		  "employeeName":fullname,
		  "email":email,
		  "password":password,
		  "managerId":ManagerId,
		  "managerEmail":managerEmail,
		  "managerPassword":managerPassword
	  });
	 
	 console.log(json.employeeName)
	 
	 console.log(id);
	 console.log(fullname);
	 console.log(email);
	 console.log(password);
	 console.log(amount);
	 console.log(ManagerId);
	 console.log(managerEmail);
	 console.log(managerPassword);
	 base64value = ""; //reset base64value
	        let xhr = new XMLHttpRequest();
	    
	        xhr.onload =()=>{
	            
	                    console.log("Upload successful!");
	                    
	                }    
	        xhr.open("POST","http://localhost:8080/org.revature.ers/EmployeeERSServlet");
   
	        xhr.send(json);
}

function getEmployeeReimbursement(email){
	
	
		
	console.log("my email"+email);
	let payload = "";
	payload= JSON.stringify({
		  "email":email
	  });
	let xhr = new XMLHttpRequest();
	xhr.onload = () => {// console.log(JSON.parse(xhr.responseText).name)
		// console.log(JSON.parse(xhr.responseText).name)
        var ticketlist = ``;
        var tickets = JSON.parse(xhr.responseText);
       
        for (var i=0; i<tickets.length; i++) {
 
        	
        	console.log("id "+ tickets[i].id)
        	console.log("status "+ tickets[i].status)
        	console.log("amount "+ tickets[i].amount)
            console.log("emp id  "+ tickets[i].employeeId)
            console.log("employeename"+ tickets[i].employeeName)
            console.log("email"+ tickets[i].email)
            console.log("password "+ tickets[i].password)
            console.log("managerid "+ tickets[i].managerId)
            console.log("manager email "+ tickets[i].managerEmail)
            console.log("managerpassword "+ tickets[i].managerPassword)
            
            
            ticketlist += `
            <tr>
                <td>`+tickets[i].status+`</td>
                <td>$`+tickets[i].amount+`</td>
                <td><img height="42" width="42"
                    id="enlarge"onclick=modalImage("`+tickets[i].image+`")
                    src="`+tickets[i].image+`" /></td>
            </tr>`;
             // console.log(tickets[i]);
             
        } 
        document.getElementById('AllReimbursementRequests').innerHTML = 
        `<h2>Your Reimbursements Requests:</h2>
        <table id=""width="100%" class="table table-hover table-dark">
            <thead>
                <th>Status</th>
                <th>Amount</th>
                <th>Image</th>
            </thead>
            <tbody>`
            +ticketlist+
            `</tbody>
        </table>
        <br>
        <br><br>
        `;
    };
    xhr.onerror = function() {
        console.log('Error');
    }
	xhr.open("POST", "http://localhost:8080/org.revature.ers/ViewServlet");
    xhr.send(payload)
}
function modalImage(image){
	let eyeliner1=document.getElementById("enlarge");
		  eyeliner1.src = image;
		  eyeliner1.height="500"; //Your desired Height
		  eyeliner1.width="219"; //Your desired Width
		
}

function Register(){
	
	document.getElementById("container").innerHTML= `<form class="col-md-10" style="position: relative; left: 15%;" id="registerForm" name="registerForm">
                <div class="panel-primary">
                    <h2 class="LogInCenter panel-heading">Register</h2>
                </div>
                <div class="form-group">
                    <label for="position"style="text-align: left">Position</label>
                    <input type="text" id="position" name="position" class="form-control" placeholder="Enter Employee or Manager" />
                </div>
                <div class="form-group">
                    <label for="fullname"style="text-align: left">Full Name</label>
                    <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Enter Full Name" />
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text"style="text-align: left" id="email" name="email" class="form-control" placeholder="Enter Email" />
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password"style="text-align: left" id="pw" name="pw" class="form-control" placeholder="Enter Password" />
                </div>
              	
                <button id="register" type="button" class="btn btn-primary col-md-12" onClick=registering()>Register</button>
                <a# onClick=redirectLogin();>Already Registered?</a>
            </form>`;
}
function redirectLogin(){
	document.getElementById("container").innerHTML=` <form class="col-md-5" style="position: relative; left: 15%;" id="loginForm" name="loginForm">
                <div class="panel-primary"style="text-align:center;">
                    <h2 class="LogInCenter panel-heading ">Login</h2>
                </div>
                <div class="form-group">
                    <label for="email" style="text-align: left">Email</label>
                    <input type="text" id="email" name="email" class="form-control" placeholder="Enter Email" />
                </div>
                <div class="form-group">
                    <label for="password" style="text-align:left;">Password</label>
                    <input type="password" id="pw" name="pw" class="form-control" placeholder="Enter Password" />
                </div>
                
                <button id="login" type="button" class="btn btn-primary col-md-12" onClick=Login()>Log In</button>
                <button id="goToRegister" type="button" class="btn btn-primary col-md-6" onClick=Register()>Haven't Registered?</a>
            </form>`;
}
function registering(){
	 
	 		let fullname = document.getElementById("fullname").value;
			let email =document.getElementById("email").value;
			let password = document.getElementById("pw").value;
			let position= document.getElementById("position").value;
			
			let registerData = JSON.stringify({"fullname":fullname,"email":email,"password":password,"position":position});
	let xhr = new XMLHttpRequest();	    
		    
	 xhr.open("POST", "http://localhost:8080/org.revature.ers/RegisterServlet");
	    xhr.onload = () => {//console.log(JSON.parse(xhr.responseText).name)
	        console.log(xhr.responseText + "hi");
	    };

	     xhr.onerror = function() {
	         console.log('Error');
	     }
	    xhr.send(registerData);
    };
