import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FileudService {

  private baseURL ="http://localhost:8080"
  constructor(private http:HttpClient) { }

  downloadTemplate(): Observable<Blob> {
    return this.http.get(`${this.baseURL}/user/download`, { responseType: 'blob' });
  }
  uploadTemplate(file: File): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    const headers = new HttpHeaders();
    return this.http.post(`${this.baseURL}/user/upload`, formData,{headers,responseType:"text"});
  }

  getAllUserFile():Observable<string>
  {
    return this.http.get(`${this.baseURL}/users`,{responseType:"text"})
  }
}
