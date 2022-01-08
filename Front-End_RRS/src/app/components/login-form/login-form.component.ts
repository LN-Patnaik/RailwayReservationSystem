import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';

export class LoginForm{
  constructor(
  public username:string="",
  public password:string=""
  ){}
}



@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit  {

  loginModel:LoginForm = new LoginForm();
  isLoading:boolean=false;
  errorMessage:string="";

  ngOnInit(): void {

    this.loginModel = new LoginForm();
     this.authenticate.logout();
    
  }
  
  constructor(private http:HttpClient, private authenticate:AuthenticationService, private router:Router) {
     // redirect to home if already logged in
     if(localStorage.getItem("currentUser"))
     this.authenticate.currentUser.subscribe(x=>{
      if(x){
      this.router.navigate(['/']);
      }
    })
      
}

  login(){
    this.isLoading=true

    this.authenticate.login(this.loginModel.username,this.loginModel.password).subscribe((res:any | Error)=>{
      console.log("inside call",res)
      this.isLoading=false
      this.router.navigate(['/'])
      window.location.reload()
    },error => {
      this.isLoading=false
      this.errorMessage="Either Username or Password is incorrect!"
  });

}
  }



