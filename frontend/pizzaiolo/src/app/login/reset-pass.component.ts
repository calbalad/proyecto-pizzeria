import { Component, OnInit } from '@angular/core';

import { RestApiService } from '../services/api.service';

@Component({
  selector: 'app-reset-pass',
  templateUrl: './reset-pass.component.html',
  styleUrls: ['./reset-pass.component.scss']
})
export class ResetPassComponent implements OnInit {

email: any;

  constructor( public restApi: RestApiService) { }

  ngOnInit(): void {
    this.restApi.resetPassword(this.email).subscribe(
        ( data: {} ) => {
          this.email = data
          // console.log(this.ingredientes)
        }
    );


  }

}
