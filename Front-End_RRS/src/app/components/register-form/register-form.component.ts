import { Component, OnInit } from '@angular/core';
import { RegisterModel } from '../../models/register.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { RoleModel } from 'src/app/models/role.model';
import { AuthenticationService } from 'src/app/service/authentication.service';

export class RegisterDTO{
  constructor(
    public userId?:string,
    public username?:string,
    public password?:string,
    public roles:RoleModel[]=[],
    public email?:string,
    public phoneNumber?:string
  ){}
}

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})

export class RegisterFormComponent   {

registerModel: RegisterModel =new RegisterModel();
isLoading:boolean=false
  constructor(private http: HttpClient,private router:Router, private authenticate:AuthenticationService) { 
    if(localStorage.getItem("currentUser"))
     this.authenticate.currentUser.subscribe(x=>{
      if(x){
     this.router.navigate(['/']);
      }
    })
  }

  register:any []=[];
  successmsg: string = "";
  registerDTO: RegisterDTO | any = new RegisterDTO();
  role:RoleModel=new RoleModel();
  isConfirm:boolean=false;
  errorMessage:string=""
  registerUser()
  {
    this.isLoading=true
    this.convertRegisterModelToDTO()
    console.log(this.registerDTO)
  
    this.http.post(environment.apiUrl+'/api/auth/register/',this.registerDTO).subscribe((response)=>{
      this.successmsg = "Registration Successful";
      this.isLoading=false
      console.log(response)
      this.router.navigate(['/login'])
    },
    error => {
      this.isLoading=false
      this.errorMessage="User Already Exists!"
  }
    
    )
  
  }

  convertRegisterModelToDTO(){
      this.registerDTO.userId=Math.floor(100000 + Math.random() * 900000)
      this.registerDTO.username=this.registerModel.username
      this.registerDTO.password=this.registerModel.password
      this.registerDTO.email=this.registerModel.email
      this.registerDTO.phoneNumber=this.registerModel.phoneNumber
      this.role.setRole("USER")
      this.role.setRoleId(String(Math.floor(100000 + Math.random() * 900000)));
      this.registerDTO.roles.push(this.role)
  }

  validateConfirmPassword(){
    this.isConfirm = this.registerModel.confirmPassword===this.registerModel.password
  }

}
