import { HttpClient } from '@angular/common/http';
import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-delete-train',
  templateUrl: './delete-train.component.html',
  styleUrls: ['./delete-train.component.css']
})
export class DeleteTrainComponent implements OnInit,DoCheck{

  successMessage="";
  failedMessage="";
  trainNo:any="";
  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute, private authenticate:AuthenticationService) {
   

   }
   
  ngDoCheck(): void {
    
      if(!localStorage.getItem("currentUser")){
      this.router.navigate(['/']);
      }
    
  }

  ngOnInit(): void {
    //get route param from url
   this.trainNo = this.route.snapshot.paramMap.get('trainNo');
  }

  deleteTrain()
  {
    this.http.delete(environment.apiUrl+"/train/delete/"+this.trainNo).subscribe((res:any)=>
    {
      console.log(res.status)
     if(res.status==="200")
     {
       console.log(res)
     
     } 
    },(error)=>{
      if(error==="OK"){
      this.successMessage="Train with train no."+" "+this.trainNo+"deleted Successfully";
      }else{
      this.failedMessage = "Deletion Failed!"
      }
    })
  
  }


}
