import { HttpClient } from '@angular/common/http';
import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TrainModel } from 'src/app/models/train.model';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-update-train',
  templateUrl: './update-train.component.html',
  styleUrls: ['./update-train.component.css']
})
export class UpdateTrainComponent implements OnInit,DoCheck {

  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute, private authenticate:AuthenticationService) {
   }

  ngDoCheck(): void {
    if(!localStorage.getItem("currentUser")){
    this.router.navigate(['/']);
    }
  }
  
  trainNo:any="";
  successMessage="";
  trainModel:TrainModel=new TrainModel();
  trainDTO?:TrainModel
  paramsSubscription$: Subscription = new Subscription();
  ngOnInit(): void {
    this.paramsSubscription$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.trainNo = params.get("trainNo");
    });
  if(this.trainNo!==null)
  {
    this.getTrainByNumber();
  }
    
  }

  getTrainByNumber(){
    this.http
       .get(environment.apiUrl+'/train/trains/'+this.trainNo)
       .subscribe((res:any)=>{
         this.trainModel = res;
         console.log("response",res)
       })
     }

    
  updateTrain()
  {
    console.log("before post",this.trainModel)
    this.http.put(environment.apiUrl+"/train/updateTrain",this.trainModel).subscribe((res:any)=>{
      this.trainDTO=res;
      console.log(res)
      this.successMessage="Train with train no."+" "+this.trainDTO?.trainNo+" "+"updated Successfully";
    })

  }

}
