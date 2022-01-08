import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TrainModel } from 'src/app/models/train.model';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-add-train',
  templateUrl: './add-train.component.html',
  styleUrls: ['./add-train.component.css']
})
export class AddTrainComponent implements OnInit {

  trainModel:TrainModel=new TrainModel()
  trainDTO?:TrainModel
  successMesssge=""
  failedMessage=""
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  addTrain()
  {
    this.http.post(environment.apiUrl+"/train/addTrain",this.trainModel).subscribe((res:any)=>{
      this.trainDTO=res;
      if(res!==null)
      {
        this.successMesssge="Train added Successfully"
      }
      else{
        this.failedMessage="Train addition Unsuccessful"
      }
      
    })
  }
}
