import { HttpClient } from '@angular/common/http';
import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { WalletModel } from 'src/app/models/wallet.model';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { WalletService } from 'src/app/service/wallet.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})
export class WalletComponent implements OnInit,DoCheck {
 balance:any;
 userId:any
  walletDTO:any;
  walletModel:any
  constructor(private router:Router,private walletService:WalletService,private http:HttpClient,private route:ActivatedRoute,private authenticate:AuthenticationService) { 
    this.walletDTO=new WalletModel("","","");
    this.walletModel=new WalletModel("","","");
  }
  ngDoCheck(): void {
    this.userId=this.route.snapshot.paramMap.get('userId');
    this.getBalance(this.userId)
  }
 
  ngOnInit(): void {
    this.balance=this.getBalance(this.userId)
    this.authenticate.currentUser.subscribe(x=>{
      if(!x){
         this.router.navigate(['/'])
      }
    })

  }

  getBalance(walletId:any):any{
    this.userId ? 
    this.http.get(environment.apiUrl+"/reservation/user/walletDetails/"+walletId)
    .subscribe(response=>{
      this.walletModel= response;
    }) : console.log("need login")
  }

  addBalance()
  {
    this.walletService.addBalance(this.userId, this.walletDTO.balance);
    
  }
}

