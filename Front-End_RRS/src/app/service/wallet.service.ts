import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { WalletModel } from '../models/wallet.model';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  constructor(private http:HttpClient) { }

  addBalance(walletId:string,amount:string){
      this.http.post(environment.apiUrl+"/reservation/user/addBalance?"+"walletId="+walletId+"&amount="+amount,{})
      .subscribe(response=>{
        return response;
      })
  }

  getBalance(walletId:string):any{
    this.http.get(environment.apiUrl+"/reservation/user/walletDetails/"+walletId)
    .subscribe(response=>{
      console.log("walletService",response)
      return response;
    })
  }

  
}
