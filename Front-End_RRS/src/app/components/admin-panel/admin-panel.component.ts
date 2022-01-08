import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { environment } from 'src/environments/environment';
import { TrainModel } from '../../models/train.model';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  constructor(private http:HttpClient, private authenticate:AuthenticationService, private router:Router) { }
  trainList: TrainModel[] = [];
  isLoading:boolean=false

  ngOnInit(): void {
  
    this.authenticate.currentUser.subscribe(x=>{
      if(x){
          if(!(x.roles.filter(roleObj=>roleObj.role==="ADMIN").length>0)){
               this.router.navigate(['/']) 
          }
    }else{
      this.router.navigate(['/']) 
    }
  })
    this.isLoading=true
    this.http.get(environment.apiUrl+"/train/trains").subscribe(((res:any)=>{
         console.log(res)
         this.isLoading=false
         this.trainList = res;
    }))
  }

  
}