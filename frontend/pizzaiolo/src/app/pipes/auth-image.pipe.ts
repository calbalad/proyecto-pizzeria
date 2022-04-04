import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'authImage'
})
export class AuthImagePipe implements PipeTransform {

  constructor(
    private http: HttpClient,
  ) {}

  async transform(src: string): Promise<any> {
    const token = localStorage.getItem('access_token');
    const headers = new HttpHeaders({'Authorization': `Bearer ${token}`});
    headers.set('Content-Type', 'image/png')
    const imageBlob = await this.http.get(src, {headers, responseType: 'blob'}).toPromise();
  }

}
