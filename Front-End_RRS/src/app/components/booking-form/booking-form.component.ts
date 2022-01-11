import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { environment } from 'src/environments/environment';
import { PassengerModel } from '../../models/passenger.model';
import { TicketModel } from '../../models/ticket.model';
import { TrainModel } from '../../models/train.model';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {

  trainNo:any="";
  totalFare:any=0;
  passengers: PassengerModel[]=[new PassengerModel()];
  ticketModel:TicketModel=new TicketModel();
  trainModel:TrainModel={}
  passengerModel?:PassengerModel
  ticketDTO?:TicketModel
  paramsSubscription$: Subscription = new Subscription();
  classType:any="";
  errorMessage:string="";
  successMessage="";
  isLoading:boolean=false
  userId:any=""
  ticketUrl:any=""
  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private authenticate:AuthenticationService) {
  
   }
  ngOnInit(): void {
    
    this.ticketModel=new TicketModel();
    this.ticketModel.passengerDetails = this.passengers
    this.passengerModel=new PassengerModel();
    this.trainModel= new TrainModel();
    this.paramsSubscription$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.trainNo = params.get("trainNo");
    });
    this.authenticate.currentUser.subscribe(x=>{
      if(x){
          this.userId=x.userId
      }
    
    })
    this.getTrainByNumber();
  }

  getTrainByNumber(){
 this.http
    .get(environment.apiUrl+'/train/trains/'+this.trainNo)
    .subscribe((res:any)=>{
      this.trainModel = res;
      this.totalFare+=this.trainModel.fare
  console.log("response",res)
    })
  }

  fun(){
    console.log(this.classType)
  }
  bookingSubmit()
  {
    //form the ticketDTO
    this.ticketModel.userId=this.userId
    this.ticketModel.trainNumber = this.trainModel.trainNo;
    this.ticketModel.trainName = this.trainModel.trainName;
    this.ticketModel.source = this.trainModel.source;
    this.ticketModel.destination = this.trainModel.destination;
    this.ticketModel.fare = this.trainModel.fare;
    console.log(this.ticketModel)
    this.isLoading=true
    this.http.post(environment.apiUrl+"/reservation/user/addTicket",this.ticketModel)
    .subscribe((res:any)=>{
      this.isLoading=false
      this.ticketDTO=res;
      this.successMessage="Ticket booked Successfully, You can view your ticket in the Book History Section"
      console.log(this.ticketDTO)
      this.ticketUrl=this.ticketUrl+"/ticket/"+this.ticketDTO?.pnrNumber
    },error=>{
        this.isLoading=false
        console.log(error,"bookingfailed")
        this.errorMessage="Booking Failed"
    })
  }

  

  addPassenger(){
    this.passengers.push(new PassengerModel())
    if(this.trainModel){
      this.totalFare+=this.trainModel.fare
    }
  }

  deletePassenger(index:any){
    if(this.passengers.length>1){
      this.passengers.splice(index,1)
      if(this.trainModel){
        this.totalFare-=Number(this.trainModel.fare)
      }
    
    }
  }

}
