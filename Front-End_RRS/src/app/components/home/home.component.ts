import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TrainModel } from '../../models/train.model';
import { Subscription } from 'rxjs';
import { Router,ActivatedRoute, ParamMap } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http:HttpClient,private router: Router,private authentication:AuthenticationService) { }

  public trainModel:TrainModel = {};
  public trainDTO?:TrainModel;
  username:any
  source:any;
  destination:any;
  doj:any;
  trainlistUrl:any;
  ngOnInit(): void {
    this.trainModel =new TrainModel();
    this.authentication.currentUser.subscribe(x=>{
      if(x){
        this.username=x.username
      }
    })

  }
  
  redirectToTrainList(){
    console.log("param:",this.trainModel.source,this.trainModel.destination,"22/10/2021")
    this.router.navigate(['/trainlist', this.trainModel.source,this.trainModel.destination,"22/10/2021"]);
  }
}
