import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router,ActivatedRoute, ParamMap } from '@angular/router';
import { TrainModel } from 'src/app/models/train.model';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-trainlist',
  templateUrl: './trainlist.component.html',
  styleUrls: ['./trainlist.component.css']
})
export class TrainlistComponent implements OnInit {

  trainList: TrainModel[] = [];
  trainModel:TrainModel={};
  source:any;
  destination:any;
  doj:any;
  trainNo:any;
  isLoading:boolean=false
  constructor(private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private authenticate:AuthenticationService) { }
   
  ngOnInit(): void {
    this.trainModel=new TrainModel();
    this.source=this.route.snapshot.paramMap.get('source');
    this.destination=this.route.snapshot.paramMap.get('destination');
    this.doj=this.route.snapshot.paramMap.get('doj');
    this.getTrainsBySourceAndDestination()
  }

  getTrainsBySourceAndDestination(){
    this.isLoading=true
    this.http
    .get(environment.apiUrl+'/train/getTrainsBySourceAndDestination?source='+this.source+'&destination='+this.destination)
    .subscribe((res:any)=>{
      this.isLoading=false
      console.log(res)
     this.trainList = res;
    }) 
  }


  redirectToBookForm(trainNo:any)
  {
    console.log(trainNo)
    console.log("localStorage.getItem",localStorage.getItem("currentUser"))
    if(localStorage.getItem("currentUser")){
    this.router.navigate(['/book', trainNo]);
    }else{
      this.router.navigate(['/login']);
    }
  }


  

}


