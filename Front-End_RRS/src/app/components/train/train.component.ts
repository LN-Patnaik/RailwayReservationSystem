import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrainModel } from '../../models/train.model';
import { Router } from '@angular/router';

// interface Train{
//   trainNo: string;
//   trainName: string;
//   source: string;
//   destination: string;
//   seats_Available: number;
//   fare: number;
// }



@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})


export class TrainComponent  implements OnInit{

  
  public trainModel:TrainModel = {};
  trainDTO:TrainModel=new TrainModel();

  ngOnInit(): void {
    this.trainModel =new TrainModel();
  }

  constructor(private http: HttpClient,private router:Router) { }

  loadTrainsByName(){
      this.http
      .get('http://localhost:9000/train/train/'+this.trainModel.trainName)
      .subscribe((res:any)=>{
        console.log("response",res)
        this.trainDTO = res;
      })
    }  
    
    loadBookTickets(trainNo:any)
    {
      if(localStorage.getItem("currentUser")){
        this.router.navigate(['/book', trainNo]);
        }else{
          this.router.navigate(['/login']);
        }
    }
 
    

  }
  


  

