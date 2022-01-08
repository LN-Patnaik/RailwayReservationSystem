import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/models/user.model';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { RegisterDTO } from '../register-form/register-form.component';




@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  


  isLogged:boolean=false;
  userId:any;
  walletUrl="/wallet/"
  bookHistoryUrl="/bookHistory/"
  isAdmin:boolean=false

  constructor(private http:HttpClient, private authenticate:AuthenticationService, private router:Router) {
    // redirect to home if already logged in
    this.isLogged=this.authenticate.currentUserValue? true:false;
   
    
 }
  
  ngOnInit(): void {
   
    this.authenticate.currentUser.subscribe(x=>{
      if(x){
      this.userId=x.userId
      this.walletUrl=this.walletUrl+this.userId
      this.bookHistoryUrl=this.bookHistoryUrl+this.userId
      this.isAdmin=x.roles.filter(roleObj=>roleObj.role==="ADMIN").length>0
      }
    })
  }
  


  logout(){
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
    window.location.reload()
  }
}
