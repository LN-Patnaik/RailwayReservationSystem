import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { RegisterDTO } from '../register-form/register-form.component';
import { PassengerModel } from '../../models/passenger.model';
import { TicketModel } from '../../models/ticket.model';
import { AuthenticationService } from '../../service/authentication.service';


@Component({
  selector: 'app-book-history',
  templateUrl: './book-history.component.html',
  styleUrls: ['./book-history.component.css']
})


export class BookHistoryComponent implements OnInit {

  constructor(private route: ActivatedRoute, private http:HttpClient,private authenticationService:AuthenticationService) {
       
   }

  userid:any;
  ticketModel:TicketModel=new TicketModel();
  passengers?:PassengerModel
  ticketList?: TicketModel[];
  ticketDTO?:TicketModel;
  successMsg=""
  userObj:RegisterDTO=new RegisterDTO();

  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(x=>{
      if(x){
        this.userid=x.userId;
        this.getTicketsByUserId()
      }
    })

  }

  getTicketsByUserId(){
    this.http.get(environment.apiUrl+"/reservation/user/ticket/"+this.userid).subscribe((res:any)=>{
     this.ticketList=res
    }
    )
  }

  cancelTicket(pnrNum:any)
  {
    this.http.get(environment.apiUrl+"/reservation/user/cancelTicket/"+pnrNum)
    .subscribe((res:any)=>{
      console.log(res)
      this.successMsg="Ticket Successfully cancelled";
      
    })
  }

}
