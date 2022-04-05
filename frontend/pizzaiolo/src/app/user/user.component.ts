import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { User } from '../model/authService/user';
import { UserResponse } from '../model/authService/userResponse';
import { RestApiService } from '../services/api.service';
import {UserModule} from '../user';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  constructor(private http: HttpClient) { }
  upload(url: string, file: File): Observable<any> {
    const formData = new FormData();
    var auth_token = localStorage.getItem('access_token') || '';
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + auth_token });
    let options = { headers: headers };
    formData.append("file", file, file.name);
    return this.http.post(url, formData, options)
  }
}


@Component({
  selector: 'app-user-edit',
  templateUrl: './tmpl-edit.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserEditComponent implements OnInit {
  test: string = "";
  user: any = [];
  file: File | null = null;
  fileURL: string = "";
  httpOptions = {}

  constructor(private http: HttpClient, private sanitizer: DomSanitizer, public restApi: RestApiService, private fileUploadService: FileUploadService) {
    this.user = JSON.parse(localStorage.getItem('data') || '{}');
    this.user = this.user.data
    console.log(this.user)

  }
  

  ngOnInit(): void {

  }

  saveUser() {
    console.log(this.test)
  }

  onChange(event: any) {
    this.file = event.target.files[0];
    this.sendFormData()
  }

   sendFormData() { 
    var user = JSON.parse(localStorage.getItem('data') || '[]');
    if (!this.file) return;
    console.log(this.file);
    this.fileUploadService.upload('http://127.0.0.1:8004/api/v1/users/'+user.data.id+'/picture?action=u', this.file ).subscribe({
      next: (data: any) => {
        if (data[0]?.url) {
          this.fileURL = data[0].url;
        }
      }
    }
    ); 
  }

/*   sendFormData(e: any) {
    console.log(e)
    var auth_token = localStorage.getItem('access_token') || '';
    var user = JSON.parse(localStorage.getItem('data') || '[]');

    const formData = new FormData();
    if (!this.file) return;
    formData.append('file', this.file);
    //formData.append('Authorization', `Bearer ${auth_token}`)
    this.restApi.uploadFile(formData, this.user.data.id).subscribe((data: {}) => {
      console.log(data)
    }); */

    





    /* let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'multipart/form-data');
    headers = headers.append('enctype', 'multipart/form-data');
    headers = headers.append('Authoritation', `Bearer ${auth_token}`); */
    /* let header = new Headers({ 'Authorization': `Bearer ${auth_token}` });
    this.http.post<any>('http://127.0.0.1:8004/api/v1/users/'+user.data.id+'/picture?action=u', formData, headers: new Headers({
      Authorization: `Bearer ${authtoken}`
    })).subscribe(
      (res) => console.log(res),
      (err) => console.log(err)
    ); */
   /*  this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Bearer ${auth_token}`
    })};
    this.http.post<any>(
        'http://127.0.0.1:8004/api/v1/users/'+user.data.id+'/picture?action=u',
        formData,
        this.httpOptions
      ) */
 /*  } */

}

@Component({
  selector: 'app-user-reset',
  templateUrl: './tmpl-reset.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserResetComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
