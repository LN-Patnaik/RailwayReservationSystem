import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TicketModel } from '../../models/ticket.model';
import { Router,ActivatedRoute, ParamMap } from '@angular/router';
import { Subscription } from 'rxjs';
import { environment } from 'src/environments/environment';



@Component({
  selector: 'app-pnrstatus',
  templateUrl: './pnrstatus.component.html',
  styleUrls: ['./pnrstatus.component.css']
})
export class PnrstatusComponent implements OnInit {

  
  pnrStatus: TicketModel= new TicketModel();
  constructor(private http:HttpClient,private router: Router,private route: ActivatedRoute) { }

  ticketModel: TicketModel = new TicketModel();
  

  id?: string | null ="";

  ngOnInit(): void {
   
  }

  loadPNRStatus(){
    try{
     this.http.get(environment.apiUrl+"/reservation/pnr/ticket/"+this.ticketModel.pnrNumber).subscribe((res:any)=>{
       this.pnrStatus = res;
    })
     }catch(e:any){
          console.log(e)
   }
  }


}
